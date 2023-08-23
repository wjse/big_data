package com.k66.hadoop.sort.all;

import com.k66.hadoop.sort.AccessSort;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 按照分隔符进行拆分
 * 把每一行数据转换成一个Access对象
 */
public class AccessSortMapper extends Mapper<LongWritable , Text , AccessSort, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(",");
        String phone = splits[0].trim();
        Long up = Long.parseLong(splits[1]);
        Long down = Long.parseLong(splits[2]);
        context.write(new AccessSort(phone , up , down) , NullWritable.get());
    }
}
