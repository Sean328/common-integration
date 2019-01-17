package com.xxl.concurrent.simulate_upload;
/**
 * <p> 一个线程池使用的实例，模拟文件上传过程中的线程池状况。默认传一份文件到sftp服务器耗时1s，1w文件多线程上传时使用线程池的效率（不考虑sftp服务器的吞吐）。
 * <p> 涉及入口主类{@link com.xxl.integration.concurrent.simulate_upload.UploadMain#main(String[])}
 * <p> 模拟文件上传task类 {@link com.xxl.integration.concurrent.simulate_upload.SimulateUploadTask#run()}
 * <p> 线程池监控Task 在主线程中新起一个线程来监控线程池的状况 {@link com.xxl.integration.concurrent.simulate_upload.ThreadPoolStatTask#run()}
 *
 */