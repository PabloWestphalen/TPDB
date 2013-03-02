package com.jin.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonObject {
	private final LinkedHashMap<String, Object> map = new LinkedHashMap<>();
	
	public Object get(String k){
		return map.get(k);
	}
	
	public Object[] getArray(String key){
		Object obj = map.get(key);
		if (obj instanceof Object[]) {
			return (Object[]) obj;
		} else if (obj instanceof List) {
			return ((List<?>) obj).toArray();
		}
		return (Object[]) map.get(key);
	}
	
	public List<?> getList(String key){
		return (List<?>) map.get(key);
	}
	
	public Collection<?> getCollection(String key){
		return (Collection<?>) map.get(key);
	}
	
	public Map<String, Object> getMap(){
		return map;
	}
	
	public JsonObject getObject(String k){
		return (JsonObject) map.get(k);
	}
	
	
	public void put(String k, Object v){
		map.put(k, v);
	}
	
	@Override
	public String toString(){
		return JsonMaker.serialize(map);
	}
}




