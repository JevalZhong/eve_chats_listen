package com.jevalable.cn;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

class MyFileAlterationMonitor {

    private long interval;//线程默认启用间隔
    private static final long DEFAULT_INTERVAL = 1;
    private String path;//线程监听的文件夹路径
    private String fileSuffix;//线程监听的文件后缀名
    private FileAlterationListenerAdaptor listener;	// 事件处理类对象

    MyFileAlterationMonitor(
            String path,
            String fileSuffix,
            FileAlterationListenerAdaptor listenerAdaptor){
        this.path = path;
        this.fileSuffix = fileSuffix;
        this.interval = DEFAULT_INTERVAL;
        this.listener = listenerAdaptor;
    }

    /***
     * 开启监听
     */
    void start() {
        FileAlterationObserver observer = new FileAlterationObserver(
                path,
                FileFilterUtils.suffixFileFilter(fileSuffix)
        );
        observer.addListener(listener);
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
