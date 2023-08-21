package com.k66.hadoop.ser;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 按照分隔符进行拆分
 * 把每一行数据转换成一个Access对象
 */
public class AccessMapper extends Mapper<LongWritable , Text , Text , Access> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(",");
        String phone = splits[0].trim();
        Long up = Long.parseLong(splits[1]);
        Long down = Long.parseLong(splits[2]);
        context.write(new Text(phone) , new Access(phone , up , down));
    }
}
