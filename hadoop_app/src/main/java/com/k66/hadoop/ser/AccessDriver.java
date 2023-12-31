package com.k66.hadoop.ser;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class AccessDriver {

    private static final String INPUT = "data/access.data";
    private static final String OUTPUT = "out";

    public static void main(String[] args) throws Exception{
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(AccessDriver.class)
                .setMapperClass(AccessMapper.class)
                .setReducerClass(AccessReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(Access.class)
                .setOutputKeyClass(NullWritable.class)
                .setOutputValueClass(Access.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(null);
        System.exit(result ? 0 : 1);
    }
}
