package com.knu.it;

import java.io.File;

public class HTML {
    public String root;
    public String relativePath;

    public HTML(String root, String relativePath){
        this.root = root;
        this.relativePath = relativePath;
    }

    public void validate() throws IllegalArgumentException{
        File file = new File(root + relativePath);
        if(!file.isFile())
            throw new IllegalArgumentException("There is no file at path " + root + relativePath);

        if(!relativePath.toLowerCase().endsWith(".html"))
            throw new IllegalArgumentException("File " + root + relativePath + " is not html file");
    }
}
