package com.crm.cn.async;
import com.crm.cn.spring.SpringUtils;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 全局统一异步管理器
 */
public class AsyncManager {

    private long delay = 10;
    /*
     * 普通的类要拿容器中的Bean
     * */
    private ScheduledThreadPoolExecutor executor = SpringUtils.getBean(ScheduledThreadPoolExecutor.class);

    private AsyncManager() {

    }


    private static AsyncManager asyncManager = new AsyncManager();

    public static AsyncManager getInstance() {
        return asyncManager;
    }

    public void executeTask(Runnable runnable) {
        executor.schedule(runnable, delay, TimeUnit.SECONDS);
    }

    /**
     * 关闭线程池
     */
    public void close() {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
    }
}
