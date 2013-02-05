package com.jin.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonMaker {
	private static Map<Object, Long> _objsVisited = new IdentityHashMap<Object, Long>();
	private static long _identity = 0;


	public static String serialize(Object o) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		
		//System.out.println("visiting object " + o.toString());
		if(_objsVisited.containsKey(o)) {
			////System.err.println("visitObject is returning REF becuase " + o.toString() + " has already been visited");
			return "REF";
		} else {
			////System.err.println("visitObject put  " + o + " in the visited list");
			_objsVisited.put(o, ++_identity);
			json.append("\"@objectID\": " +  _objsVisited.get(o) + ", ");
			
		}
		Field[] fields = o.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
			if(fields[i].getName().startsWith("this$")) {
				continue;
			}
			try {
				fields[i].setAccessible(true);
				if(_objsVisited.containsKey(fields[i].get(o))) {
					json.append("\"" + fields[i].getName() + "\" : \"@object" + _objsVisited.get(fields[i].get(o)) + "\"");
					if(i+1 < fields.length) {
						json.append(", ");
					}	
					continue;
				} else {
					json.append("\"" + fields[i].getName() + "\": " + getValue(fields[i].get(o)));
					
				}
				if(i+1 < fields.length) {
					json.append(", ");
				}
			} catch (Exception e) {
				
			}
		}
		json.append("}");
		
		//System.out.println("visitObject end");
		return json.toString();
		
	}
	
	public static String getValue(Object o) {
		System.out.println("getting the value of " + o.getClass().getName() + " whose toString returns " + o.toString());
		if(_objsVisited.containsKey(o)) {
			//System.out.println("abort, because it has already been visited");
			return "REF2";
		} 
		
		if(o instanceof String || o instanceof Enum) {
			//System.out.println("done. returning " + "\"" + o + "\"");
			return "\"" + escapeString(o.toString()) + "\"";
		} else if(o instanceof Number) {
			return escapeString(o.toString());
		}
		
		else if(o instanceof Collection){
			//System.out.println("is a collection, so i'll pass to the collection method");
			return getCollectionValues((Collection<?>)o);
		} else if(o instanceof Map) {
			return getMap(o);
		}
		
		else if(o instanceof Object[]) {
			//array of objects
			return getObjectArray(o);
		} else if(o.getClass().isArray()) {
			//array of primitives
			return getArrayOfPrimitives(o);
		}
		//System.err.println("getV put " + o + " in the visited list");
		// último else
		String returnString = escapeString(serialize(o));
		//System.out.println("getV is returning " + returnString);
		return returnString;
	}
	
	public static String getObjectArray(Object o) {
		StringBuilder json = new StringBuilder();
		Object[] objs = (Object[])o;
		
		json.append("[");
		for(int i = 0; i < objs.length; i++) {
			json.append(getValue(objs[i]));
			if(i+1 < objs.length) {
				json.append(", ");
			}
		}
		json.append("]");
		
		return json.toString();
	}
	
	
	public static String getMap(Object o) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		Iterator<?> it = ((Map<?, ?>) o).entrySet().iterator();
		while (it.hasNext()) {
			Entry<?, ?> key = ((Entry<?, ?>) it.next());
			json.append(getValue(key.getKey()) + ": "
					+ getValue(key.getValue()));
			if (it.hasNext()) {
				json.append(",");
			}
		}
		json.append("}");
		return json.toString();
	}
	
	public static String getArrayOfPrimitives(Object o) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		for (int i = 0; i < Array.getLength(o); i++) {
			if (o instanceof char[]) {
				json.append("\"" + Array.get(o, i) + "\"");
			} else {
				json.append(Array.get(o, i));
			}
			if (i + 1 < Array.getLength(o)) {
				json.append(", ");
			}
		}
		json.append("]");
		return json.toString();
	}
	
	public static String escapeString(String s) {
		return s.replaceAll("\"", "\\\\\"").replaceAll("\r\n", "\\\\n").replaceAll("\n", "\\\\n");
	}
	
	public static String getCollectionValues(Collection<?> o) {
		
		
		//System.out.println("parsing collection " + o.getClass().getName());
		StringBuilder json = new StringBuilder();
		Iterator<?> it = o.iterator();
		json.append("[");
		while(it.hasNext()) {
			Object e = it.next();
			if(_objsVisited.containsKey(e)) {
				/*json.append("\"@object" +  _objsVisited.get(e) + "\"");
				if(it.hasNext()) {
					json.append(", ");
				}*/
				continue;
			} else {
				json.append(getValue(e));
				if(it.hasNext()) {
					json.append(", ");
				}
				_objsVisited.put(e, ++_identity);
			}
			//System.out.println("Passing '" + e + "' which is a " + e.getClass().getName() + " to getV");
			
		}
		json.append("]");
		_objsVisited.put(o, ++_identity);
		return json.toString();
	}
	

	public class Disco {
		String titulo;
		Artista artista;
		

		public Disco(String titulo, Artista artista) {
			this.titulo = titulo;
			this.artista = artista;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public Artista getArtista() {
			return artista;
		}

		public void setArtista(Artista artista) {
			this.artista = artista;
		}
		
		public String toString() {
			return titulo;
		}

	}
	
	enum Nacionalidade {
		AMERICANO, BRASILEIRO
	};

	public class Artista {
		String nome;
		Set<Disco> discos;
		int[] numeros = {1, 1, 2, 3, 5, 8};
		char[] chars = { 'j', 'i', 'n' };
		int idade = 22;
		Nacionalidade nacionalidade = Nacionalidade.AMERICANO;
		Map<Object, Object> mapa;
		
		public Map<Object, Object> getMapa() {
			return mapa;
		}

		public void setMapa(Map<Object, Object> mapa) {
			this.mapa = mapa;
		}

		public Artista(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Set<Disco> getDiscos() {
			return discos;
		}

		public void setDiscos(Set<Disco> discos) {
			this.discos = discos;
		}
		
		@Override
		public String toString() {
			return nome;
		}

	}
}
