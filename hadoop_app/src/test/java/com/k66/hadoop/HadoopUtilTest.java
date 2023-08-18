package com.k66.hadoop;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(JUnit4ClassRunner.class)
public class HadoopUtilTest {

    private static HadoopUtil hadoop;

    @BeforeClass
    public static void before(){
        hadoop = new HadoopUtil();
    }

    @AfterClass
    public static void after(){
        hadoop.close();
    }

    @Test
    public void test1(){
        hadoop.mkdir("1");
    }

    @Test
    public void test2(){
        hadoop.delDir("1");
    }

    @Test
    public void test3(){
        hadoop.upload("/Users/k66/dev/hadoop_app/src/test/java/com/k66/hadoop/1-1" , "1");
    }
}
