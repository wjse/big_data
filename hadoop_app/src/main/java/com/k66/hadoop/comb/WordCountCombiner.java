package com.k66.hadoop.comb;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountCombiner extends Reducer<Text, IntWritable, Text , IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        //累计求和
        for(IntWritable value : values){
            sum += value.get();
        }

        //输出
        context.write(key , new IntWritable(sum));
    }
}
