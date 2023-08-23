package com.k66.hadoop.output;

import com.k66.hadoop.HadoopDriverUtil;
import com.k66.hadoop.input.MysqlInputFormatApp;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Date;

/**
 * 将文件数据写入到mysql中
 */
public class DBOutputFormatApp {
    private static final String INPUT = "data/taskLog.data";
    public static void main(String[] args) throws Exception{
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(DBOutputFormatApp.class)
                .setMapperClass(MyMapper.class)
                .setReducerClass(MyReducer.class)
                .setMapOutputKeyClass(LongWritable.class)
                .setMapOutputValueClass(Text.class)
                .setOutputKeyClass(MysqlInputFormatApp.TTaskLogWritable.class)
                .setOutputValueClass(NullWritable.class)
                .setDbDriverClass("com.mysql.jdbc.Driver")
                .setDbUrl("jdbc:mysql://localhost:3306/portal_dev")
                .setDbUser("root")
                .setDbPass("root")
                .setDbTableName("t_task_log")
                .setDbColumns(new String[]{"id" , "number" , "name" , "create_time"})
                .setInput(INPUT)
                .hadoopDBJob();
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable , Text , LongWritable , Text>{
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context) throws IOException, InterruptedException {
            context.write(key , value);
        }
    }

    public static class MyReducer extends Reducer<LongWritable , Text , MysqlInputFormatApp.TTaskLogWritable , NullWritable> {
        @Override
        protected void reduce(LongWritable key, Iterable<Text> values, Reducer<LongWritable, Text, MysqlInputFormatApp.TTaskLogWritable, NullWritable>.Context context) throws IOException, InterruptedException {
            Date date = new Date();
            for(Text text : values){
                String[] splits = text.toString().split(",");
                context.write(new MysqlInputFormatApp.TTaskLogWritable(Long.parseLong(splits[0]) , splits[1] , splits[2] , date) , NullWritable.get());
            }
        }
    }
}
