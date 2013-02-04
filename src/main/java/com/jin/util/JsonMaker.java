package com.jin.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class JsonMaker {
	int depth = 0;
	
	public static String from(Map<?, ?> m) {
		return toJson(m, 0);
	}

	public static String from(Collection<?> l) {
		return toJson(l, 0);
	}
	
	public static String serialize(Object o) {
		return serialize(o, 0);
	}

	private static String getter2field(String name) {
		if (name.startsWith("get")) {
			return String.valueOf(name.charAt(3)).toLowerCase()
					+ name.substring(4, name.length());
		}
		if (name.startsWith("is")) {
			return String.valueOf(name.charAt(2)).toLowerCase()
					+ name.substring(3, name.length());

		}
		return null;
	}

	public static String serialize(Object o, int depth) {
		System.out.println("Current level of recursion is " + depth++);
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		System.out.println("Dealing with a " + o.getClass().getName());
		for (Method m : o.getClass().getMethods()) {
			if (m.getDeclaringClass().equals(o.getClass())) {
				if (m.getName().startsWith("get")
						|| m.getName().startsWith("is")) {
					for (Field f : o.getClass().getDeclaredFields()) {
						String fieldName = getter2field(m.getName());
						if (f.getName().equals(fieldName)) {
							try {
								f.setAccessible(true);
								Object val = f.get(o);
								System.out.println("About to call toJson for the field \"" + fieldName + "\" of class \"" + o.getClass().getName() + "\"");
								//System.out.println("Val is" + toJson(val));
								map.put(fieldName, val);
								System.out.println("Map atm is " + map);
								
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		if (map.size() > 0) {
			System.out.println("Done dealing with \"" + o.getClass().getName() + "\"");
			return toJson(map, depth);
		} else {
			return "\"" + o.toString() + "\"";
		}
	}

	private static String toJson(Object o, int depth) {
		StringBuilder json = new StringBuilder();
		if(o != null) {
			if (o instanceof Map) {
				json.append("{");
				Iterator<?> it = ((Map<?, ?>) o).entrySet().iterator();
				while (it.hasNext()) {
					Entry<?, ?> key = ((Entry<?, ?>) it.next());
					json.append(toJson(key.getKey(), depth) + ": "
							+ toJson(key.getValue(), depth));
					if (it.hasNext()) {
						json.append(",");
					}
				}
				json.append("}");
			} else if (o instanceof Number) {
				json.append(o);
			} else if (o instanceof String) {
				json.append("\"" + o + "\"");
			} else if (o instanceof Collection) {
				json.append("[");
				Iterator<?> it = ((Collection<?>) o).iterator();
				while (it.hasNext()) {
					json.append(toJson(it.next(), depth));
					if (it.hasNext()) {
						json.append(",");
					}
				}
				json.append("]");
			} else if(o instanceof Enum) {
				json.append("\"" + o + "\"");
			}
			
			
			else if (o instanceof Object[]) {
				if (o.getClass().isArray()) {
					json.append("[");
				}
				// deal with array of objects
				for (int i = 0; i < ((Object[]) o).length; i++) {

					json.append("" + toJson(((Object[]) o)[i], depth) + "");
					if (i + 1 < ((Object[]) o).length) {
						json.append(",");
					}
				}
				if (o.getClass().isArray()) {
					json.append("]");
				}
			} else if (o.getClass().isArray()) {
				
				// deal with array of primitives
				json.append("[");
				for (int i = 0; i < Array.getLength(o); i++) {
					if (o instanceof char[]) {
						json.append("\"" + Array.get(o, i) + "\"");
					} else {
						json.append(Array.get(o, i));
					}
					if (i + 1 < Array.getLength(o)) {
						json.append(",");
					}
				}
				json.append("]");
			} else {
				if(depth < 2) {
					System.out.println("About to serialize " + o.getClass().getName());
					json.append(serialize(o, depth));
				} else {
					json.append("\"" + o.toString() + "\"");
				}
			}
			return json.toString();
		} else {
			return "\"\"";
		}
	}

}
