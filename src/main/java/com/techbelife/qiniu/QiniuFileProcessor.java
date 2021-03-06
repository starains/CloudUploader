package com.techbelife.qiniu;

import com.techbelife.Interface.FileProcessor;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class QiniuFileProcessor implements FileProcessor {
    private static final long MAX_SIMPLE_UPLOAD_SIZE = 10485760;
    private ThreadPoolExecutor threadPoolExecutor;
    private FormUpload formUpload;
    private SliceUpload sliceUpload;

    public QiniuFileProcessor() {
        threadPoolExecutor = new ThreadPoolExecutor(uploadThreadNums, uploadThreadNums, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        formUpload = new FormUpload(threadPoolExecutor);
        sliceUpload = new SliceUpload(threadPoolExecutor);
    }

    @Override
    public void uploadFile(String path) {
        File file = new File(path);
        if (file.length() < MAX_SIMPLE_UPLOAD_SIZE) {
            formUpload.submitFile(file);
        } else {
            sliceUpload.submitFile(file);
        }
    }
}
