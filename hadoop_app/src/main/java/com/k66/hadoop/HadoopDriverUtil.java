package com.k66.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Map;

public class HadoopDriverUtil {

    private  Class<?> driverClass;

    private  Class<? extends Mapper> mapperClass;
    private  Class<? extends Reducer> reducerClass;
    private  Class<? extends Reducer> combinerClass;

    private  Class<? extends Writable> mapOutputKeyClass;
    private  Class<? extends Writable> mapOutputValueClass;
    private  Class<? extends Writable> outputKeyClass;
    private  Class<? extends Writable> outputValueClass;

    private  String input;
    private  String output;

    private  Class<? extends FileInputFormat> childInputFormatClass;

    private Class<? extends Partitioner> partitionerClass;
    
    public static final String SPLIT_COUNT_KEY = "split.count";
    public static final String REDUCER_TASK_COUNT_KEY = "reducer.task.count";

    public  boolean hadoopFSJob(Map<String , Object> configMap) throws Exception{
        if(null == driverClass || null == input || null == output){
            return false;
        }

        Configuration c = new Configuration();
        //获取Job对象
        Job job = Job.getInstance(c);

        //设置Job运行类
        job.setJarByClass(driverClass);

        //设置Mapper和Reducer
        if(null != mapperClass){
            job.setMapperClass(mapperClass);
        }
        if(null != reducerClass){
            job.setReducerClass(reducerClass);
        }

        //设置Combiner
        if(null != combinerClass){
            job.setCombinerClass(combinerClass);
        }

        //设置Mapper输出数据key value数据类型
        if(null != mapOutputKeyClass){
            job.setMapOutputKeyClass(mapOutputKeyClass);
        }
        if(null != mapOutputValueClass){
            job.setMapOutputValueClass(mapOutputValueClass);
        }

        //设置Reducer输出数据key value数据类型
        if(null != outputKeyClass){
            job.setOutputKeyClass(outputKeyClass);
        }
        if(null != outputValueClass){
            job.setOutputValueClass(outputValueClass);
        }

        //设置Partitioner
        if(null != partitionerClass){
            job.setPartitionerClass(partitionerClass);
            if(null != configMap && configMap.containsKey(REDUCER_TASK_COUNT_KEY)){
                job.setNumReduceTasks((Integer) configMap.get(REDUCER_TASK_COUNT_KEY));
            }
        }

        //设置其他InputFormat
        if(null != childInputFormatClass){
            if(childInputFormatClass.getName().equals(KeyValueTextInputFormat.class.getName())){
                if(null != configMap && configMap.size() > 0){
                    configMap.forEach((k , v) -> c.set(k , v.toString()));
                }
            }else if(childInputFormatClass.getName().equals(NLineInputFormat.class.getName())){
                if(null != configMap && configMap.containsKey(SPLIT_COUNT_KEY)){
                    NLineInputFormat.setNumLinesPerSplit(job , (Integer) configMap.get(SPLIT_COUNT_KEY));
                }
            }
            job.setInputFormatClass(childInputFormatClass);
        }

        clearOutput(c);

        //设置Job的输入输出路径
        FileInputFormat.setInputPaths(job , new Path(input));
        FileOutputFormat.setOutputPath(job , new Path(output));

        //提交Job
        return job.waitForCompletion(true);
    }
    
    private HadoopDriverUtil(){}
    
    public static HadoopDriverUtil builder(){
        return new HadoopDriverUtil();
    }
    
     void clearOutput(Configuration configuration) throws IOException {
        FileSystem fs = FileSystem.get(configuration);
        Path p = new Path(output);
        if(fs.exists(p)){
            fs.delete(p);
        }
    }

    public HadoopDriverUtil setDriverClass(Class<?> driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public HadoopDriverUtil setMapperClass(Class<? extends Mapper> mapperClass) {
        this.mapperClass = mapperClass;
        return this;
    }

    public HadoopDriverUtil setReducerClass(Class<? extends Reducer> reducerClass) {
        this.reducerClass = reducerClass;
        return this;
    }

    public HadoopDriverUtil setCombinerClass(Class<? extends Reducer> combinerClass) {
        this.combinerClass = combinerClass;
        return this;
    }

    public HadoopDriverUtil setMapOutputKeyClass(Class<? extends Writable> mapOutputKeyClass) {
        this.mapOutputKeyClass = mapOutputKeyClass;
        return this;
    }

    public HadoopDriverUtil setMapOutputValueClass(Class<? extends Writable> mapOutputValueClass) {
        this.mapOutputValueClass = mapOutputValueClass;
        return this;
    }

    public HadoopDriverUtil setOutputKeyClass(Class<? extends Writable> outputKeyClass) {
        this.outputKeyClass = outputKeyClass;
        return this;
    }

    public HadoopDriverUtil setOutputValueClass(Class<? extends Writable> outputValueClass) {
        this.outputValueClass = outputValueClass;
        return this;
    }

    public HadoopDriverUtil setInput(String input) {
        this.input = input;
        return this;
    }

    public HadoopDriverUtil setOutput(String output) {
        this.output = output;
        return this;
    }

    public HadoopDriverUtil setChildInputFormatClass(Class<? extends FileInputFormat> childInputFormatClass) {
        this.childInputFormatClass = childInputFormatClass;
        return this;
    }

    public HadoopDriverUtil setPartitionerClass(Class<? extends Partitioner> partitionerClass) {
        this.partitionerClass = partitionerClass;
        return this;
    }
}