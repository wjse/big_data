package com.k66.hadoop.comb;

import com.k66.hadoop.HadoopDriverUtil;
import com.k66.hadoop.mr.WordCountMapper;
import com.k66.hadoop.mr.WordCountReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class WordCountDriver {

    private static final String INPUT = "data/wc";
    private static final String OUTPUT = "out/comb";

    public static void main(String[] args) throws Exception{
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(com.k66.hadoop.mr.WordCountDriver.class)
                .setMapperClass(WordCountMapper.class)
                .setReducerClass(WordCountReducer.class)
                .setCombinerClass(WordCountCombiner.class)
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
