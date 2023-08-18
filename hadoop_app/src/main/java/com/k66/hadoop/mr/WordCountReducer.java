package com.k66.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * KEYIN, 输入数据key数据类型,对应Mapper输出数据类型
 * VALUEIN, 输入数据value数据类型，对应Mapper输出数据类型
 * KEYOUT, 输出数据key数据类型
 * VALUEOUT 输出数据value数据类型
 */
public class WordCountReducer extends Reducer<Text , IntWritable , Text , IntWritable> {

    /**
     * pk,pk,pk    hello,hello    world
     * Mapper=> <pk,1><pk,1><pk,1>
     *          <hello,1><hello,1>
     *          <world,1>
     *
     * 相同的key经过shuffle之后，会在同一个reduce中进行聚合
     * Shuffling=> <pk,<1,1,1>>
     *             <hello,<1,1>>
     *             <world,<1>>
     *
     * Task顺序默认字典顺序
     * @param key 单词
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
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
