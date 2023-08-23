package com.k66.hadoop.mr;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class WordCountDriver {

    private static final String INPUT = "data/wc";
    private static final String OUTPUT = "out";

    public static void main(String[] args) throws Exception{
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(WordCountDriver.class)
                .setMapperClass(WordCountMapper.class)
                .setReducerClass(WordCountReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(IntWritable.class)
                .setOutputKeyClass(Text.class)
                .setOutputValueClass(IntWritable.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(null);
        System.exit(result ? 0 : 1);
    }
}
