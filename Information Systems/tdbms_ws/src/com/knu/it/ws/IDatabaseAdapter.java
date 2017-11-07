package com.knu.it.ws;

import com.knu.it.db.database.Database;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "TDBMS", targetNamespace = "http://ws.it.knu.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IDatabaseAdapter {

    @WebMethod
    @WebResult(partName = "return")
    Database[] getDatabases();

    @WebMethod
    @WebResult(partName = "return")
    Database createDatabase(String name);

}
