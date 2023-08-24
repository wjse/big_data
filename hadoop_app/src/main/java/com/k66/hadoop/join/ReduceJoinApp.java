package com.k66.hadoop.join;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReduceJoinApp {

    private static final String INPUT = "data/join";
    private static final String OUTPUT = "out/join";

    public static void main(String[] args) throws Exception {
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(ReduceJoinApp.class)
                .setMapperClass(MyMapper.class)
                .setReducerClass(MyReducer.class)
                .setMapOutputKeyClass(Text.class)
                .setMapOutputValueClass(JoinInfo.class)
                .setOutputKeyClass(NullWritable.class)
                .setOutputValueClass(JoinInfo.class)
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(null);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable , Text , Text , JoinInfo>{

        private String fileName;

        @Override
        protected void setup(Mapper<LongWritable, Text, Text, JoinInfo>.Context context) throws IOException, InterruptedException {
            fileName = ((FileSplit)context.getInputSplit()).getPath().toString();
        }

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, JoinInfo>.Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t");
            JoinInfo info;
            if(fileName.contains("emp")){
                info = new JoinInfo(splits[0] , splits[1] , splits[2] , "emp");
                info.setOrgName("?");
            }else{
                info = new JoinInfo(splits[1] , "org");
                info.setUserId("-");
                info.setUserName("-");
                info.setOrgCode(splits[0]);
            }
            context.write(new Text(info.getOrgCode()) , info);
        }
    }

    public static class MyReducer extends Reducer<Text , JoinInfo , NullWritable , JoinInfo>{
        @Override
        protected void reduce(Text key, Iterable<JoinInfo> values, Reducer<Text, JoinInfo, NullWritable, JoinInfo>.Context context) throws IOException, InterruptedException {
            String orgName = null;
            List<JoinInfo> list = new ArrayList<>();
            for(JoinInfo info : values){
                if("emp".equals(info.getFlag())){
                    //一定要新new一个对象，否则出问题
                    //内存指针指向问题
                    list.add(new JoinInfo(info.getUserId() , info.getUserName() , info.getOrgCode() , ""));
                }else{
                    orgName = info.getOrgName();
                }
            }

            for(JoinInfo info : list){
                info.setOrgName(orgName);
                context.write(NullWritable.get() , info);
            }

        }
    }
}
