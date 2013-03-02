package com.jin.util;

import java.util.ArrayList;

/**
 * Clean attempt at reading a json string using the state pattern
 * 
 * @author Pablo Westphalen (gotjin@gmail.com)
 */
public class JsonReader {
	private int currentChar = 0;
	private boolean done;
	private final String json;
	private final JsonObject obj = new JsonObject();
	
	private JsonReader(String json) {
		this.json = json;
	}
	
	/**
	 * Gets back to java
	 * 
	 * @param json
	 * @return
	 */
	public static JsonObject toJava(String json) throws RuntimeException{
		return new JsonReader(json).parseJson();
	}
	
	/**
	 * @param json
	 */
	private JsonObject parseJson(){
		Character c;
		while (!done) {
			c = readSkipWhitespace();
			if (c != null && c == '{') {
				do {
					String field = "";
					Object value = null;
					if (readSkipWhitespace() == '"') { // temos um field
						field = readString();
					} else {
						System.out.println("problema em ler o fieldName");
					}
					if (readSkipWhitespace() == ':') {
						value = getValue();
					} else {
						System.out.println("problema em ler o fieldValue");
					}
					obj.put(field, value);
				} while ((c = readSkipWhitespace()) != null && c == ',');
			}
		}
		return obj;
	}
	
	/**
	 * @return
	 */
	private Object getValue(){
		if (nextNonWhitespaceChar() == '"') {
			readSkipWhitespace();
			return readString();
		}
		if (nextNonWhitespaceChar() == '[') {
			readSkipWhitespace();
			return readArray();
		}
		if (Character.isDigit(nextNonWhitespaceChar())) {
			return readNumber();
		}
		if (nextNonWhitespaceChar() == '{') {
			readSkipWhitespace();
			return readObject();
		}
		if (nextNonWhitespaceChar() == 'n') {
			return readNull();
		}
		return null;
	}
	
	/**
	 * @return
	 */
	private Object readNull(){
		readSkipWhitespace();
		int pos = currentChar;
		if (json.substring(pos - 1, pos + 3).equals("null")) {
			currentChar += 3;
			return "null";
		}
		return "porra";
	}
	
	/**
	 * @return
	 */
	private Character nextNonWhitespaceChar(){
		int charsRead = 0;
		if (currentChar + 1 <= json.length()) {
			Character c;
			while (currentChar + 1 <= json.length()) {
				charsRead++;
				if (!Character.isWhitespace(c = json.charAt(currentChar++))) {
					currentChar -= charsRead;
					return c;
				}
			}
		}
		done = true;
		return null;
	}
	
	/**
	 * @return
	 */
	private JsonObject readObject(){
		String objectString = "{";
		boolean bracesBalanced = false;
		int openedBraces = 1, closedBraces = 0;
		Character c = null;
		while (!bracesBalanced) {
			c = read();
			if (c == '{') {
				openedBraces++;
			}
			if (c == '}') {
				closedBraces++;
				if (openedBraces == closedBraces) {
					bracesBalanced = true;
				}
			}
			objectString += c;
		}
		return JsonReader.toJava(objectString);
	}
	
	/**
	 * @return
	 */
	private String readNumber(){
		String value = "";
		Character c;
		while (nextNonWhitespaceChar() != ',' && nextNonWhitespaceChar() != '}') {
			c = readSkipWhitespace();
			value += c;
		}
		return value;
	}
	
	/**
	 * @return
	 */
	private Object readArray(){
		Character c = nextNonWhitespaceChar();
		if (c == '"') { // array of strings
			ArrayList<String> elements = new ArrayList<>();
			do {
				readSkipWhitespace();
				String element = readString();
				elements.add(element);
			} while ((c = readSkipWhitespace()) != null && c == ',');
			return elements;
		}
		if (c == '{') { // array of objects
			ArrayList<JsonObject> objs = new ArrayList<>();
			Character d = null;
			boolean withinString = false;
			do {
				String objectString = "";
				boolean bracesBalanced = false;
				int openedBraces = 0, closedBraces = 0;
				while (!bracesBalanced) {
					d = read();
					if (d != null) {
						if (d == '"' && withinString) {
							withinString = false;
						} else if (d == '"' && !withinString) {
							withinString = true;
						}
					}
					if (d == '{' && !withinString) {
						openedBraces++;
					}
					if (d == '}' && !withinString) {
						closedBraces++;
						if (openedBraces == closedBraces) {
							bracesBalanced = true;
						}
					}
					objectString += d;
				}
				objs.add(JsonReader.toJava(objectString));
			} while (readSkipWhitespace() == ',');
			return objs;
		}
		if (Character.isDigit(c)) { // array of numbers
			ArrayList<Number> elements = new ArrayList<>();
			do {
				String element = "";
				do {
					element += readSkipWhitespace();
					c = nextNonWhitespaceChar();
				} while (c == '.' || Character.isDigit(c));
				if (element.contains(".")) {
					elements.add(Double.parseDouble(element));
				} else {
					elements.add(Integer.parseInt(element));
				}
			} while ((c = readSkipWhitespace()) != null && c == ',');
			return elements;
		}
		return "INVALID ARRAY";
	}
	
	private String readString(){
		String value = "";
		boolean escaping = false;
		Character c;
		c = read();
		while ((!escaping && c != '"') || (escaping && c == '\\')
				|| (escaping && c == '"')) {
			value += c;
			c = read();
			if (!escaping && c == '\\') {
				escaping = true;
			}
			if (escaping && c != '\\' && c != '"') {
				escaping = false;
			}
		}
		return value;
	}
	
	/**
	 * @return
	 */
	private Character read(){
		if (currentChar + 1 <= json.length()) {
			return json.charAt(currentChar++);
		} else {
			return null;
		}
	}
	
	/**
	 * @param json
	 * @return
	 */
	private Character readSkipWhitespace(){
		if (currentChar + 1 <= json.length()) {
			Character c;
			while (currentChar + 1 <= json.length()) {
				if (!Character.isWhitespace(c = json.charAt(currentChar++))) {
					return c;
				}
			}
		}
		done = true;
		return null;
	}
}
