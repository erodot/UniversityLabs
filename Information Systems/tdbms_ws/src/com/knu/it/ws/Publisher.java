package com.knu.it.ws;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        String address = "http://localhost:9999/";
        Endpoint.publish(address, new DatabaseAdapter());
        System.out.println("Server run on " + address);
    }
}
