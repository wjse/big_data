package com.k66.hadoop.sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 不关注输出key，所以放NullWriteable
 */
public class AccessSortReducer extends Reducer<AccessSort , NullWritable, NullWritable , AccessSort> {

    @Override
    protected void reduce(AccessSort key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for(NullWritable n : values){
            context.write(n.get() , key);
        }
    }
}
