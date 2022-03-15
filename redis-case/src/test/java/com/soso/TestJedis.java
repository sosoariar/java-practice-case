package com.soso;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestJedis {

    private Jedis jedis;


    @Test
    public void testString(){
        jedis = new Jedis("192.168.2.10",6379);
        jedis.select(0);
        String result = jedis.set("soso:user:2","redis-2");
        System.out.println(result);
        System.out.println(jedis.get("soso:user:1"));
    }

}
