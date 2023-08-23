package com.k66.hadoop.sort.partition;

import com.k66.hadoop.HadoopDriverUtil;
import com.k66.hadoop.sort.AccessSort;
import com.k66.hadoop.sort.all.AccessSortMapper;
import com.k66.hadoop.sort.all.AccessSortReducer;
import org.apache.hadoop.io.NullWritable;

import java.util.HashMap;
import java.util.Map;

public class AccessDriver {

    private static final String INPUT = "data/access-stat.data";
    private static final String OUTPUT = "out/sort/partition";

    public static void main(String[] args) throws Exception{
        Map<String , Object> configMap = new HashMap<>();
        configMap.put(HadoopDriverUtil.REDUCER_TASK_COUNT_KEY , 3);
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(AccessDriver.class)
                .setMapperClass(AccessSortMapper.class)
                .setReducerClass(AccessSortReducer.class)
                .setMapOutputKeyClass(AccessSort.class)
                .setMapOutputValueClass(NullWritable.class)
                .setOutputKeyClass(NullWritable.class)
                .setOutputValueClass(AccessSort.class)
                .setPartitionerClass(AccessSortPartitioner.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(configMap);
        System.exit(result ? 0 : 1);
    }
}
