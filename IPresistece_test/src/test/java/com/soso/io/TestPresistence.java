package com.soso.io;

import org.junit.Test;
import java.io.InputStream;

public class TestPresistence {

    @Test
    public void test(){
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    }

}
