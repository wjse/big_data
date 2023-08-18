package com.k66.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN, 输入数据key数据类型
 * VALUEIN, 输入数据value数据类型
 * KEYOUT, 输出数据key数据类型
 * VALUEOUT 输出数据value数据类型
 */
public class WordCountMapper extends Mapper<LongWritable , Text , Text , IntWritable> {

    private static final IntWritable ONE = new IntWritable(1);

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        System.out.println("=====setup=====");
    }

    @Override
    protected void cleanup(Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        System.out.println("=====cleanup=====");
    }

    /**
     *
     * @param key 每行数据的偏移量,每个字符的下标
     * @param value 每行数据
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("key : " + key.get());
        System.out.println(value.toString());
        String[] splits = value.toString().split(",");
        for(String s : splits){
            context.write(new Text(s) , ONE);
        }
    }
}
