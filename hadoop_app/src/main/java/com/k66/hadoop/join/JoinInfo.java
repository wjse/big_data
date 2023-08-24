package com.k66.hadoop.join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class JoinInfo implements Writable {

    private String userId;
    private String userName;
    private String orgCode;
    private String orgName;

    private String flag;

    public JoinInfo() {
    }

    public JoinInfo(String userId, String userName, String orgCode , String flag) {
        this.userId = userId;
        this.userName = userName;
        this.orgCode = orgCode;
        this.flag = flag;
    }

    public JoinInfo(String orgName , String flag) {
        this.orgName = orgName;
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public JoinInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public JoinInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public JoinInfo setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public JoinInfo setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public JoinInfo setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(userId);
        dataOutput.writeUTF(userName);
        dataOutput.writeUTF(orgCode);
        dataOutput.writeUTF(orgName);
        dataOutput.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.userId = dataInput.readUTF();
        this.userName = dataInput.readUTF();
        this.orgCode = dataInput.readUTF();
        this.orgName = dataInput.readUTF();
        this.flag = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return userId + ',' +userName + ',' +orgCode + ',' +orgName + ',' +flag;
    }
}
