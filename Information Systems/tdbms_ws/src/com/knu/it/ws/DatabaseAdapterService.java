package com.knu.it.ws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "DatabaseAdapterService", targetNamespace = "http://ws.it.knu.com/", wsdlLocation = "http://tdbms-theodoreromanus.c9users.io/?wsdl")
public class DatabaseAdapterService extends Service {
    private final static URL WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://tdbms-theodoreromanus.c9users.io/?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public DatabaseAdapterService() {
        super(WSDL_LOCATION, new QName("http://ws.it.knu.com/", "DatabaseAdapterService"));
    }

    @WebEndpoint(name = "DatabaseAdapterPort")
    public IDatabaseAdapter getIDatabaseAdapterPort() {
        return super.getPort(new QName("http://ws.it.knu.com/", "DatabaseAdapterPort"), IDatabaseAdapter.class);
    }
}
