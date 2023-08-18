package com.k66.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    private static final String INPUT = "data";
    private static final String OUTPUT = "out";

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration c = new Configuration();
        //获取Job对象
        Job job = Job.getInstance(c);

        //设置Job运行类
        job.setJarByClass(WordCountDriver.class);

        //设置Mapper和Reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置Mapper输出数据key value数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置Reducer输出数据key value数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        clearOutput(c);
        //设置Job的输入输出路径
        FileInputFormat.setInputPaths(job , new Path(INPUT));
        FileOutputFormat.setOutputPath(job , new Path(OUTPUT));

        //提交Job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    static void clearOutput(Configuration configuration) throws IOException {
        FileSystem fs = FileSystem.get(configuration);
        Path p = new Path(OUTPUT);
        if(fs.exists(p)){
            fs.delete(p);
        }
    }
}
