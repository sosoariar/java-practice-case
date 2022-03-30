package com.soso;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileReaderTester extends TestCase {

    public FileReaderTester(String name){
        super(name);
    }

    protected void setUp(){
        try{
            input = new FileReader("data.txt");
        }catch(FileNotFoundException e){
            throw new RuntimeException("unable to open test file");
        }
    }

    protected void tearDown(){
        try{
            input.close();
        }catch(Exception e){
            throw new RuntimeException("error on closing test file");
        }
    }

    private FileReader input;
}
