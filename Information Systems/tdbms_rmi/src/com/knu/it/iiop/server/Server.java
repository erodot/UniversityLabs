package com.knu.it.iiop.server;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class Server {

    public Server() {}

    public static void main(String args[]) {

        try {
            DatabaseAdapter obj = new DatabaseAdapter();
            IDatabaseAdapter databaseAdapter = (IDatabaseAdapter) UnicastRemoteObject.exportObject(obj, 0);

            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
            Context initialNamingContext = new InitialContext(props);
            initialNamingContext.rebind("DatabaseAdapter", databaseAdapter );

            System.out.println("Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
