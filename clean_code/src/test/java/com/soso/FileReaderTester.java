package com.soso;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    public void testRead() throws Exception{
        char ch = '&';
        for(int i=0; i<4; i++){
            ch = (char) input.read();
        }
        assert('d' == ch);
    }

    public void testReadAtEnd() throws IOException {
        int ch = -1234;
        for(int i=0; i<141; i++){
            ch = input.read();
        }
        assertEquals(-1,input.read());
    }

    public static TestSuite suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(new FileReaderTester("testRead"));
        return suite;
    }

    private FileReader input;
}
