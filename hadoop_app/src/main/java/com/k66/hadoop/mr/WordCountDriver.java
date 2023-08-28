package com.k66.hadoop.mr;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class WordCountDriver {

    public static void main(String[] args) throws Exception{
        if(null == args || args.length != 2){
            System.out.println("Please input the right args : <input><output>");
            System.exit(1);
        }

        String input = args[0];
        String output = args[1];
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(WordCountDriver.class)
                .setMapperClass(WordCountMapper.class)
                .setReducerClass(WordCountReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(IntWritable.class)
                .setOutputKeyClass(Text.class)
                .setOutputValueClass(IntWritable.class)
                .setInput(input)
                .setOutput(output)
                .hadoopFSJob(null);
        System.exit(result ? 0 : 1);
    }
}
