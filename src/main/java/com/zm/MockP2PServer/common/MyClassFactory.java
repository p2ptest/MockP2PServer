package com.zm.MockP2PServer.common;

import com.zm.MockP2PServer.task.UdpTask;
import com.zm.MockP2PServer.thread.ListenThreadImpl;
import com.zm.MockP2PServer.thread.ProcessThreadImpl;
import com.zm.MockP2PServer.thread.RecAndSendThreadImpl;
import com.zm.frame.thread.server.ClassFactory;
import com.zm.frame.thread.task.Task;
import com.zm.frame.thread.thread.BasicThread;

import static com.zm.MockP2PServer.common.MyDef.TASK_TYPE_UDP;

/**
 * Created by zhangmin on 2017/8/23.
 */
public class MyClassFactory extends ClassFactory {
    @Override
    public BasicThread genThread(int threadType, int threadId, Object arg) {
        BasicThread ret = null;
        switch (threadType) {
            case MyDef.THREAD_TYPE_LISTEN :
                ret = new ListenThreadImpl(threadType, threadId);
                break;
            case MyDef.THREAD_TYPE_REC_AND_SEND :
                ret = new RecAndSendThreadImpl(threadType, threadId);
                break;
            case MyDef.THREAD_TYPE_PROCESS :
                ret = new ProcessThreadImpl(threadType, threadId);
                break;
        }
        return ret;
    }

    @Override
    public Task genTask(int taskType, int taskId, BasicThread thread, int time, Object arg) {
        Task ret = null;
        switch (taskType) {
            case TASK_TYPE_UDP:
                ret = new UdpTask(taskId, thread, time, arg);
                break;
        }
        return ret;
    }
}