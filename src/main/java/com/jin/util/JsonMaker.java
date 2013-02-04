package com.jin.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonMaker {
	public static String from(Map<?, ?> m) {
		return toJson(m);
	}

	public static String from(List<?> l) {
		return toJson(l);
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

	public static String serialize(Object o) {
		HashMap<Object, Object> map = new HashMap<>();
		//System.out.println("Dealing with a " + o.getClass().getName());
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
								// System.out.println("Val is" + toJson(val));
								map.put(fieldName, val);
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
			return toJson(map);
		} else {
			return null;
		}
	}

	// FIXME faltou SET

	private static String toJson(Object o) {
		StringBuilder json = new StringBuilder();
		if (o instanceof Map) {
			json.append("{");
			Iterator<?> it = ((Map<?, ?>) o).entrySet().iterator();
			while (it.hasNext()) {
				Entry<?, ?> key = ((Entry<?, ?>) it.next());
				json.append(toJson(key.getKey()) + ": "
						+ toJson(key.getValue()));
				if (it.hasNext()) {
					json.append(",");
				}
			}
			json.append("}");
		} else if (o instanceof Number) {
			json.append(o);
		} else if (o instanceof String) {
			json.append("\"" + o + "\"");
		} else if (o instanceof List) {
			json.append("[");
			Iterator<?> it = ((List<?>) o).iterator();
			while (it.hasNext()) {
				json.append(toJson(it.next()));
				if (it.hasNext()) {
					json.append(",");
				}
			}
			json.append("]");
		} else if (o instanceof Object[]) {
			if (o.getClass().isArray()) {
				json.append("[");
			}
			// deal with array of objects
			for (int i = 0; i < ((Object[]) o).length; i++) {

				json.append("" + toJson(((Object[]) o)[i]) + "");
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
			json.append(serialize(o));
		}
		return json.toString();
	}

}
