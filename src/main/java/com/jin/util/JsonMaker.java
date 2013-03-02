package com.jin.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An utility with simple static methods to serialize Java objects into JSON-formatted
 * ones.
 * 
 * @author Pablo Westphalen
 */
public final class JsonMaker {
	private final Map<Object, Long> _objsVisited = new IdentityHashMap<Object, Long>();
	private long _identity = 1;
	private JsonMaker() {
		
	}
	
	

	/**
	 * Returns a JSON-formatted array containing the elements
	 * of the specified Collection.
	 * 
	 * @param c the Collection to be evaluated.
	 * @return A String containing a JSON-formatted array based on the contents
	 *         of the specified Collection;
	 */
	public static String serialize(Collection<?> c) {
		return new JsonMaker().getCollectionValues(c);
	}
	
	/**
	 * Returns a String containing a JSON-formatted object based on the contents
	 * of the specified Map.
	 * 
	 * @param m
	 *            the Map to be evaluated.
	 * @return A String containing a JSON-formatted object based on the contents
	 *         of the specified Map.
	 */
	public static String serialize(Map<?, ?> m) {
		return new JsonMaker().getMapValues(m);
	}
	
	/**
	 * Serializes an object into its JSON-formatted equivalent.
	 * 
	 * @param o
	 *            the object to be serialized.
	 * @return A String containing a JSON-formatted object based on the
	 *         attributes of the specified object.
	 */
	public static String serialize(Object o) {
		return new JsonMaker().doSerialize(o);
	}
	
	private boolean isTransient(AccessibleObject f) {
		for(Annotation a : f.getAnnotations()) {
			if(a.annotationType().toString().endsWith("Transient")) {
				return true;
			}
		}
		return false;
	}
	
	private String doSerialize(Object o) {
		_objsVisited.put(o, _identity++);
		StringBuilder json = new StringBuilder();
		json.append("{");
		
		json.append("\"@objectID\": " + _objsVisited.get(o) + ", ");
		Field[] fields = o.getClass().getDeclaredFields();
		
		
		
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				if(isTransient(fields[i])) {
					continue; // skip to next field
				} else { 
					json.append(getFieldValue(fields[i].getName(), fields[i].get(o)));
				}
			} catch (Exception e) {
				System.err.println("Could not process field "
						+ fields[i].getName() + " of class "
						+ o.getClass().getName());
				continue;
			}
			if (i + 1 < fields.length) {
				if(!isTransient(fields[i+1])) {
					json.append(", "); 
				}
			}
		}
		json.append("}");
		return json.toString();
	}
	
	private String getFieldValue(String field, Object value) {
		if (value == null) {
			return "\"" + field + "\": null";
		}
		if (field.startsWith("this$")) {
			return "";
		}
		if (_objsVisited.containsKey(value)) {
			return "\"" + field + "\" : \"@object_" + _objsVisited.get(value)
					+ "\"";
		} else {
			return "\"" + field + "\": " + getValue(value);
		}
	}
	
	/**
	 * Converts an Object's meaningful value to its JSON-formatted equivalent.
	 * 
	 * @param o
	 *            the Object to be evaluated.
	 * @return a JSON-formatted String equivalent to the specified object's
	 *         value.
	 */
	private String getValue(Object o) {
		if(o == null) {
			return "null";
		} else if (o instanceof String || o instanceof Enum) {
			return "\"" + escapeString(o.toString()) + "\"";
		} else if (o instanceof Number) {
			return o.toString();
		} else if (o instanceof Timestamp || o instanceof Date) {
			return "\"" + o.toString() + "\"";
		} else if (o instanceof Collection) {
			return getCollectionValues((Collection<?>) o);
		} else if (o instanceof Map) {
			return getMapValues(o);
		} else if (o instanceof Object[]) {
			return getObjectArray(o);
		} else if (o.getClass().isArray()) {
			return getArrayOfPrimitives(o);
		}
		return doSerialize(o);
	}
	
	private String getObjectArray(Object o) {
		StringBuilder json = new StringBuilder();
		Object[] objs = (Object[]) o;
		json.append("[");
		for (int i = 0; i < objs.length; i++) {
			json.append(getValue(objs[i]));
			if (i + 1 < objs.length) {
				json.append(", ");
			}
		}
		json.append("]");
		return json.toString();
	}
	
	private String getMapValues(Object o) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		Iterator<?> it = ((Map<?, ?>) o).entrySet().iterator();
		while (it.hasNext()) {
			Entry<?, ?> key = ((Entry<?, ?>) it.next());
			json.append("\"" + key.getKey().toString() + "\": "
					+ getValue(key.getValue()));
			if (it.hasNext()) {
				json.append(",");
			}
		}
		json.append("}");
		return json.toString();
	}
	
	private String getArrayOfPrimitives(Object o) {
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
	
	private String getCollectionValues(Collection<?> o) {
		StringBuilder json = new StringBuilder();
		Iterator<?> it = o.iterator();
		json.append("[");
		while (it.hasNext()) {
			Object e = it.next();
			if (_objsVisited.containsKey(e)) {
				continue;
			} else {
				json.append(getValue(e));
				if (it.hasNext()) {
					json.append(", ");
				}
				_objsVisited.put(e, _identity++);
			}
		}
		json.append("]");
		_objsVisited.put(o, _identity++);
		return json.toString();
	}
	
	private String escapeString(String s) {
		return s.replaceAll("\"", "\\\\" + "\"").replaceAll("\r\n", "\\\\n")
				.replaceAll("\n", "\\\\n").replaceAll("\t", " ");
	}
}
