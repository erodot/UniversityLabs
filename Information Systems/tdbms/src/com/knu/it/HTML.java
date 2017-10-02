package com.knu.it;

import java.io.File;

public class HTML {
    public String relativePath;

    public HTML(String relativePath){
        this.relativePath = relativePath;
    }

    public void validate() throws IllegalArgumentException{
        File file = new File(Constants.DB_PATH + relativePath);
        if(!file.isFile())
            throw new IllegalArgumentException("There is no file at path " + Constants.DB_PATH + relativePath);

        if(!relativePath.toLowerCase().endsWith(".html"))
            throw new IllegalArgumentException("File " + Constants.DB_PATH + relativePath + " is not html file");
    }
}
