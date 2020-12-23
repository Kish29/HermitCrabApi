package com.kish2.hermitcrabapi;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerThreadPool {

    public static ThreadPoolExecutor THREAD_POOL;

    static {
        int cpu = Runtime.getRuntime().availableProcessors();
        THREAD_POOL = new ThreadPoolExecutor(
                cpu,
                cpu * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(cpu),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
