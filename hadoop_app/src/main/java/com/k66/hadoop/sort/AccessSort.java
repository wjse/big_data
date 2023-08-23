package com.k66.hadoop.sort;


import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义类型
 */
public class AccessSort implements WritableComparable<AccessSort> {

    private String phone;
    private long up;
    private long down;
    private long sum;

    public AccessSort() {
    }

    public AccessSort(String phone, long up, long down) {
        this.phone = phone;
        this.up = up;
        this.down = down;
        this.sum = up + down;
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(phone);
        out.writeLong(up);
        out.writeLong(down);
        out.writeLong(sum);
    }

    /**
     * 反序列化
     * 注意顺序
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        phone = in.readUTF();
        up = in.readLong();
        down = in.readLong();
        sum = in.readLong();
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s" , phone , up , down , sum);
    }

    @Override
    public int compareTo(AccessSort o) {
        return  this.sum > o.sum ? -1 : 1;
    }
}
