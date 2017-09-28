package com.knu.it;

import org.json.simple.parser.JSONParser;

public class Constants {
    public static String DB_PATH = "/Users/tedromanus/Documents/db";
    public static JSONParser jsonParser = new JSONParser();

    public static Class<?> GetClass(String key){

        Class<?> c;
        switch (key) {
            case "int":
                c = Integer.class;
                break;
            case "long":
                c = Long.class;
                break;
            case "string":
                c = String.class;
                break;
            case "float":
                c = Float.class;
                break;
            default:
                c = Object.class;
                break;
        }

        return c;
    }
}
