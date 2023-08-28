package com.k66.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;

public class HadoopUtil {

    private static Configuration c;

    private static FileSystem fs;

    static {
        System.setProperty("HADOOP_USER_NAME" , "hadoop");
        c = new Configuration();
        c.set("fs.defaultFS" , "hdfs://hadoop.krrk66.club:9000");
        c.set("dfs.client.use.datanode.hostname" , "true");
        c.set("dfs.replication" , "1");
        try {
            fs = FileSystem.get(c);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Configuration getConfiguration(){
        return c;
    }

    public void mkdir(String path){
        try {
            fs.mkdirs(new Path(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delDir(String path){
        try {
            fs.delete(new Path(path) , true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void upload(String src , String dst){
        try {
            fs.copyFromLocalFile(new Path(src) , new Path(dst));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        if(null != fs){
            IOUtils.closeStreams(fs);
        }
    }
}
