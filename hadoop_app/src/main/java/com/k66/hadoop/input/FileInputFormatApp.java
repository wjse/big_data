package com.k66.hadoop.input;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FileInputFormatApp {

    private static final String INPUT = "data/wc";
    private static final String OUTPUT = "out/wc";

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration c = new Configuration();
        //获取Job对象
        Job job = Job.getInstance(c);

        //设置Job运行类
        job.setJarByClass(FileInputFormatApp.class);

        //设置Mapper和Reducer
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        //设置Mapper输出数据key value数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置Reducer输出数据key value数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        clearOutput(c);
        //设置Job的输入输出路径
        TextInputFormat.setInputPaths(job , new Path(INPUT));
        FileOutputFormat.setOutputPath(job , new Path(OUTPUT));

        //提交Job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable , Text , Text , IntWritable>{

        private static final IntWritable ONE = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split(",");
            for(String s : splits){
                context.write(new Text(s) , ONE);
            }
        }
    }

    public static class MyReducer extends Reducer<Text , IntWritable , Text , IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int count = 0;
            for(IntWritable i : values){
                count += i.get();
            }
            context.write(key , new IntWritable(count));
        }
    }

    static void clearOutput(Configuration configuration) throws IOException {
        FileSystem fs = FileSystem.get(configuration);
        Path p = new Path(OUTPUT);
        if(fs.exists(p)){
            fs.delete(p);
        }
    }
}
