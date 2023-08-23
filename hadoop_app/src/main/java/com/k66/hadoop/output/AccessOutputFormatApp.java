package com.k66.hadoop.output;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AccessOutputFormatApp {

    private static final String INPUT = "data/click.log";
    private static final String OUTPUT = "out/output";

    public static void main(String[] args) throws Exception{
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(AccessOutputFormatApp.class)
                .setMapperClass(MyMapper.class)
                .setReducerClass(MyReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(NullWritable.class)
                .setOutputKeyClass(Text.class)
                .setOutputValueClass(NullWritable.class)
                .setOutputFormatClass(AccessOutputFormat.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(null);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable, Text, Text , NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            context.write(value , NullWritable.get());
        }
    }

    public static class MyReducer extends Reducer<Text , NullWritable , Text , NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Reducer<Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            for(NullWritable v : values){
                context.write(new Text(String.format("%s\r\n" , key.toString())) , v);
            }
        }
    }

    public static class AccessOutputFormat extends FileOutputFormat<Text , NullWritable>{

        @Override
        public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) {
            return new AccessRecordWriter(job.getConfiguration());
        }
    }

    public static class AccessRecordWriter extends RecordWriter<Text , NullWritable>{

        FileSystem fs;
        FSDataOutputStream googleOS;
        FSDataOutputStream pornhubOS;
        FSDataOutputStream otherOS;

        public AccessRecordWriter(Configuration c) {
            try {
                //通过文件系统自定义输出
                fs = FileSystem.get(c);
                googleOS = fs.create(new Path(String.format("%s/google" , OUTPUT)));
                pornhubOS = fs.create(new Path(String.format("%s/pornhub" , OUTPUT)));
                otherOS = fs.create(new Path(String.format("%s/other" , OUTPUT)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void write(Text key, NullWritable value) throws IOException, InterruptedException {
            String v = key.toString();
            if(v.contains("google")){
                googleOS.write(key.copyBytes());
            }else if(v.contains("pornhub")){
                pornhubOS.write(key.copyBytes());
            }else{
                otherOS.write(key.copyBytes());
            }
        }

        @Override
        public void close(TaskAttemptContext context) {
            IOUtils.closeStreams(otherOS , pornhubOS , googleOS , fs);
        }
    }
}
