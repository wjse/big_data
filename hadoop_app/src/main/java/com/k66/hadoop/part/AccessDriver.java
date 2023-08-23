package com.k66.hadoop.part;

import com.k66.hadoop.HadoopDriverUtil;
import com.k66.hadoop.ser.Access;
import com.k66.hadoop.ser.AccessMapper;
import com.k66.hadoop.ser.AccessReducer;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import java.util.HashMap;
import java.util.Map;

public class AccessDriver {

    private static final String INPUT = "data/access.data";
    private static final String OUTPUT = "out/part";

    public static void main(String[] args) throws Exception{
        //设置分区个数满足自定义分区的定义
        /*
         *   reduce数量和分区数量（3）的关系
         * 1）reduce = partitioner ,输出3个文件
         * 2) reduce > partitioner ,输出5个文件，有两个文件时空的，reduce数量决定输出文件数量
         * 3）reduce = 1
         * 4) reduce < partitioner
         * */
        Map<String , Object> configMap = new HashMap<>();
        configMap.put(HadoopDriverUtil.REDUCER_TASK_COUNT_KEY , 3);
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(AccessDriver.class)
                .setMapperClass(AccessMapper.class)
                .setReducerClass(AccessReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(Access.class)
                .setOutputKeyClass(NullWritable.class)
                .setOutputValueClass(Access.class)
                .setPartitionerClass(AccessPartitioner.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(configMap);
        System.exit(result ? 0 : 1);
    }
}
