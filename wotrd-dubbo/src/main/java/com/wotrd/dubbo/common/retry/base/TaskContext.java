package com.wotrd.dubbo.common.retry.base;

import java.io.Serializable;

public class TaskContext<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -5568412953853536289L;
    private long nextExecuteTime;
    private int curExecuteCnt;
    private T taskParam;

    public TaskContext() {
    }

    public long getNextExecuteTime() {
        return this.nextExecuteTime;
    }

    public void setNextExecuteTime(long nextExecuteTime) {
        this.nextExecuteTime = nextExecuteTime;
    }

    public int getCurExecuteCnt() {
        return this.curExecuteCnt;
    }

    public void setCurExecuteCnt(int curExecuteCnt) {
        this.curExecuteCnt = curExecuteCnt;
    }

    public T getTaskParam() {
        return this.taskParam;
    }

    public void setTaskParam(T taskParam) {
        this.taskParam = taskParam;
    }

    public String toString() {
        return "TaskContext{nextExecuteTime=" + this.nextExecuteTime + ", curExecuteCnt=" + this.curExecuteCnt + ", taskParam=" + this.taskParam + '}';
    }
}
