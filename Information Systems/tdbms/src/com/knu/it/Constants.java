package com.knu.it;

import org.json.simple.parser.JSONParser;

public class Constants {
    public static JSONParser jsonParser = new JSONParser();
    public static int COLUMN_WIDTH = 15;

    public static Class<?> GetClass(String key){

        Class<?> c;
        switch (key) {
            case "html":
                c = HTML.class;
                break;
            case "int":
                c = Integer.class;
                break;
            case "long":
                c = Long.class;
                break;
            case "char":
                c = Character.class;
                break;
            case "double":
                c = Double.class;
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }

        return c;
    }
}
