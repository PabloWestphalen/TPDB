package com.jin.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class JsonMaker {
	private static final Map<Object, Long> _objsVisited = new IdentityHashMap<Object, Long>();
	private static long _identity = 0;

	public static synchronized String serialize(Object o) {
		_objsVisited.clear();
		_identity = 0;
		return doSerialize(o);
	}

	private static String doSerialize(Object o) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		_objsVisited.put(o, ++_identity);
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				json.append(getFieldValue(fields[i].getName(), fields[i].get(o)));
			} catch (Exception e) {	}
			if (i + 1 < fields.length) {
				json.append(", ");
			}
		}
		json.append("}");
		return json.toString();
	}

	private static String getFieldValue(String field, Object value) {
		if(_objsVisited.containsKey(value)) {
			return "\"" + field + "\" : \"@object" + _objsVisited.get(value) + "\"";
		}
		if (field.startsWith("this$")) {
			return "";
		}
		if (value == null) {
			return "\"" + field + "\": null";
		}
		if (_objsVisited.containsKey(value)) {
			return "\"" + field + "\" : \"@object"
					+ _objsVisited.get(value) + "\"";
		} else {
			return "\"" + field + "\": " + getValue(value);
		}
	}

	private static String getValue(Object o) {
		if (o instanceof String || o instanceof Enum) {
			return "\"" + escapeString(o.toString()) + "\"";
		} else if (o instanceof Number) {
			return o.toString();
		} else if (o instanceof Timestamp || o instanceof Date) {
			return "\"" + o.toString() + "\"";
		} else if (o instanceof Collection) {
			return getCollectionValues((Collection<?>) o);
		} else if (o instanceof Map) {
			return getMap(o);
		} else if (o instanceof Object[]) {
			return getObjectArray(o);
		} else if (o.getClass().isArray()) {
			return getArrayOfPrimitives(o);
		}
		return doSerialize(o);
	}

	private static String getObjectArray(Object o) {
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

	private static String getMap(Object o) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		Iterator<?> it = ((Map<?, ?>) o).entrySet().iterator();
		while (it.hasNext()) {
			Entry<?, ?> key = ((Entry<?, ?>) it.next());
			json.append(key.getKey().toString() + ": "
					+ getValue(key.getValue()));
			if (it.hasNext()) {
				json.append(",");
			}
		}
		json.append("}");
		return json.toString();
	}

	private static String getArrayOfPrimitives(Object o) {
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

	private static String escapeString(String s) {
		return s.replaceAll("\"", "\\\\" + "\"").replaceAll("\r\n", "\\\\n")
				.replaceAll("\n", "\\\\n").replaceAll("\t", " ");
	}

	private static String getCollectionValues(Collection<?> o) {
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
				_objsVisited.put(e, ++_identity);
			}
		}
		json.append("]");
		_objsVisited.put(o, ++_identity);
		return json.toString();
	}
}
