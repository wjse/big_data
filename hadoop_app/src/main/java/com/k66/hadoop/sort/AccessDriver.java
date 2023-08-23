package com.k66.hadoop.sort;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.NullWritable;

public class AccessDriver {

    private static final String INPUT = "data/access-stat.data";
    private static final String OUTPUT = "out/sort";

    public static void main(String[] args) throws Exception{
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(AccessDriver.class)
                .setMapperClass(AccessSortMapper.class)
                .setReducerClass(AccessSortReducer.class)
                .setMapOutputKeyClass(AccessSort.class)
                .setMapOutputValueClass(NullWritable.class)
                .setOutputKeyClass(NullWritable.class)
                .setOutputValueClass(AccessSort.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(null);
        System.exit(result ? 0 : 1);
    }
}
