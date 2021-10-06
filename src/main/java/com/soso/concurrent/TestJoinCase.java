package com.soso.concurrent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date:Created in 17:51 2021/10/6
 */

public class TestJoinCase {

    private static final Log logger = LogFactory.getLog(TestJoinCase.class);

    @Test
    public void test01() throws InterruptedException {
        Thread t1 = new Thread(()->{
            int count = 10;
            while(count>0){
                logger.info(count--);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            int count = 10;
            while(count>0){
                logger.info(count--);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        logger.debug(Thread.currentThread().getName()+" end");
    }

    @Test
    public void test02() throws InterruptedException {
        Thread t1 = new Thread(()->{
            int count = 10;
            while(count>0){
                logger.info(count--);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int count = 10;
            while(count>0){
                logger.info(count--);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t2.join();

        logger.debug(Thread.currentThread().getName()+" end");
    }

    // 等待指定时间后,会把原线程也终止掉
    @Test
    public void test03() throws InterruptedException {
        Thread t = new Thread(()->{
            int count = 10;
            while(count>0){
                logger.info(count--);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        t.join(2000);
        logger.warn("end");
    }
}
