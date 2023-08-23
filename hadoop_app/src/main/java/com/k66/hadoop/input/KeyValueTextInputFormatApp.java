package com.k66.hadoop.input;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KeyValueTextInputFormatApp {

    private static final String INPUT = "data/kv.data";
    private static final String OUTPUT = "out/kv";

    public static void main(String[] args) throws Exception{
        Map<String , Object> configMap = new HashMap<>();
        configMap.put(KeyValueLineRecordReader.KEY_VALUE_SEPARATOR , "|");
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(KeyValueTextInputFormatApp.class)
                .setMapperClass(MyMapper.class)
                .setReducerClass(MyReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(IntWritable.class)
                .setOutputKeyClass(Text.class)
                .setOutputValueClass(IntWritable.class)
                .setChildInputFormatClass(KeyValueTextInputFormat.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(configMap);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<Text, Text , Text , IntWritable>{

        private static final IntWritable ONE = new IntWritable(1);

        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            context.write(key , ONE);
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
}
