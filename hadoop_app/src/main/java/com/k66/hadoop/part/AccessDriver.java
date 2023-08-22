package com.k66.hadoop.part;

import com.k66.hadoop.ser.Access;
import com.k66.hadoop.ser.AccessMapper;
import com.k66.hadoop.ser.AccessReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AccessDriver {

    private static final String INPUT = "data/access.data";
    private static final String OUTPUT = "out/part";

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(AccessDriver.class);
        job.setMapperClass(AccessMapper.class);
        job.setReducerClass(AccessReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Access.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Access.class);

        //设置自定义分区规则
        job.setPartitionerClass(AccessPartitioner.class);
        //设置分区个数满足自定义分区的定义
        /*
        *   reduce数量和分区数量（3）的关系
        * 1）reduce = partitioner ,输出3个文件
        * 2) reduce > partitioner ,输出5个文件，有两个文件时空的，reduce数量决定输出文件数量
        * 3）reduce = 1
        * 4) reduce < partitioner
        * */
        job.setNumReduceTasks(3);

        clearOutput(configuration);

        FileInputFormat.setInputPaths(job , new Path(INPUT));
        FileOutputFormat.setOutputPath(job , new Path(OUTPUT));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    static void clearOutput(Configuration configuration) throws IOException {
        FileSystem fs = FileSystem.get(configuration);
        Path p = new Path(OUTPUT);
        if(fs.exists(p)){
            fs.delete(p);
        }
    }
}
