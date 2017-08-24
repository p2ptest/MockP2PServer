package com.zm.MockP2PServer.thread;

import com.zm.MockP2PServer.common.MyDef;
import com.zm.frame.conf.Definition;
import com.zm.frame.thread.thread.NoBlockingThread;

public class OnTimeThreadImpl extends NoBlockingThread {

    private long lastCheckTaskTime = System.currentTimeMillis();

    public OnTimeThreadImpl(int threadType, int threadId, Object arg) {
        super(threadType, threadId, (int) arg);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void afterProcessMsg() {
        long timeNow = System.currentTimeMillis();
        //每秒发送消息，处理一次任务超时
        if ((timeNow - lastCheckTaskTime) >= (1 * 1000)) {
            sendThreadMsgTo(Definition.MSG_TYPE_CHECK_TASK_TIMEOUT, null, MyDef.THREAD_TYPE_REC_AND_SEND);
            lastCheckTaskTime = timeNow;
        }
    }
}
