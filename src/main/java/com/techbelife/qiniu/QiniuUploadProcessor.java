package com.techbelife.qiniu;

import com.techbelife.Interface.FileProcessor;
import com.techbelife.Interface.UploadProcessor;
import com.techbelife.Interface.UploadQueue;


public class QiniuUploadProcessor implements UploadProcessor {
    private UploadQueue fileQueue;
    private FileProcessor fileProcessor;

    @Override
    public void setUploadQueue(UploadQueue queue) {
        this.fileQueue = queue;
    }

    @Override
    public void run() {
        while (true) {
            String path = fileQueue.get();
            fileProcessor.uploadFile(path);
        }
    }

    public QiniuUploadProcessor() {
        fileProcessor = new QiniuFileProcessor();
    }
}
