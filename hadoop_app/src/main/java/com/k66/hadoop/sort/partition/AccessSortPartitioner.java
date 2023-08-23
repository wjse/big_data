package com.k66.hadoop.sort.partition;

import com.k66.hadoop.sort.AccessSort;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class AccessSortPartitioner extends Partitioner<AccessSort, NullWritable> {

    @Override
    public int getPartition(AccessSort key, NullWritable value, int numPartitions) {
        String phone = key.getPhone();
        if(phone.startsWith("13")){
            return 0;
        }else if(phone.startsWith("15")){
            return 1;
        }else{
            return 2;
        }
    }
}
