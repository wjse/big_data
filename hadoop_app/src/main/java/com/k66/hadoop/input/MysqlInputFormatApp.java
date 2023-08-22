package com.k66.hadoop.input;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class MysqlInputFormatApp {

    private static final String OUTPUT = "out/mysql";

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration c = new Configuration();
        DBConfiguration.configureDB(c, "com.mysql.jdbc.Driver" , "jdbc:mysql://localhost:3306/portal_dev" , "root" , "root");
        //获取Job对象
        Job job = Job.getInstance(c);

        //设置Job运行类
        job.setJarByClass(MysqlInputFormatApp.class);

        //设置Mapper和Reducer
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        //设置Mapper输出数据key value数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        clearOutput(c);
        //设置Job的输入输出路径
        DBInputFormat.setInput(job , TTaskLogWritable.class , "t_task_log" , null , null , new String[]{"id" , "number" , "name" , "create_time"});
        FileOutputFormat.setOutputPath(job , new Path(OUTPUT));

        //提交Job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable , TTaskLogWritable , Text, IntWritable>{

        private static final IntWritable ONE = new IntWritable(1);

        @Override
        protected void map(LongWritable key, TTaskLogWritable value, Mapper<LongWritable, TTaskLogWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            context.write(new Text(value.getName()) , ONE);
        }
    }

    public static class MyReducer extends Reducer<Text, IntWritable , Text, LongWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
            int sum = 0;
            for(IntWritable i : values){
                sum += i.get();
            }
            context.write(key , new LongWritable(sum));
        }
    }

    public static class TTaskLogWritable implements Writable, DBWritable {
        private Long id;
        private String number;
        private String name;
        private Date createTime;

        public TTaskLogWritable() {
        }

        public TTaskLogWritable(Long id, String number, String name, Date createTime) {
            this.id = id;
            this.number = number;
            this.name = name;
            this.createTime = createTime;
        }

        @Override
        public void write(DataOutput out) throws IOException {
            out.writeLong(id);
            out.writeUTF(number);
            out.writeUTF(name);
            out.writeLong(createTime.getTime());
        }

        @Override
        public void readFields(DataInput input) throws IOException {
            id = input.readLong();
            number = input.readUTF();
            name = input.readUTF();
            createTime = new Date(input.readLong());
        }

        @Override
        public void write(PreparedStatement ps) throws SQLException {

        }

        @Override
        public void readFields(ResultSet rs) throws SQLException {
            id = rs.getLong("id");
            number = rs.getString("number");
            name = rs.getString("name");
            createTime = rs.getDate("create_time");
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append("{");
            buffer.append("'id':").append(id).append(",");
            buffer.append("'number':").append("'").append(number).append("',");
            buffer.append("'name':").append("'").append(name).append("',");
            buffer.append("'createTime':").append(createTime.getTime());
            buffer.append("}");
            return buffer.toString();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
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
