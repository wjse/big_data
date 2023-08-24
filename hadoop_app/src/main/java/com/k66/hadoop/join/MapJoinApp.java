package com.k66.hadoop.join;

import com.k66.hadoop.HadoopDriverUtil;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapJoinApp {

    /**
     * 大表数据
     */
    private static final String INPUT = "data/join/emp.data";
    private static final String OUTPUT = "out/join";

    public static void main(String[] args) throws Exception {
        Map<String , Object> configMap = new HashMap<>();
        configMap.put(HadoopDriverUtil.REDUCER_TASK_COUNT_KEY , 0);
        boolean result = HadoopDriverUtil.builder()
                .setDriverClass(MapJoinApp.class)
                .setMapperClass(MyMapper.class)
                .setMapOutputKeyClass(JoinInfo.class)
                .setMapOutputValueClass(NullWritable.class)
                .setCacheFileURIList(Arrays.asList(new URI("data/join/org.data")))//加载小表数据
                .setInput(INPUT)
                .setOutput(OUTPUT)
                .hadoopFSJob(configMap);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable , Text , JoinInfo , NullWritable>{

        /**
         * orgCode ==> orgName
         */
        private Map<String , String> cacheMap = new HashMap<>();

        @Override
        protected void setup(Mapper<LongWritable, Text, JoinInfo, NullWritable>.Context context) throws IOException, InterruptedException {
            URI[] files = context.getCacheFiles();
            String path = files[0].getPath();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path) , StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null){
                String[] splits = line.split("\t");
                cacheMap.put(splits[0] , splits[1]);
            }

            IOUtils.closeStreams(reader);
        }

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, JoinInfo, NullWritable>.Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t");//大表的数据
            JoinInfo info = new JoinInfo(splits[0] , splits[1] , splits[2] , "");
            if(cacheMap.containsKey(info.getOrgCode())){
                info.setOrgName(cacheMap.get(info.getOrgCode()));
            }else{
                info.setOrgName("-");
            }
            context.write(info , NullWritable.get());
        }
    }
}
