package com.qf.oa.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Authoer lzq
 * @DateTime 2018/11/7  16:36
 * @Version 1.0
 */
public class Mail {
    private String subject;
    private String sendTo;
    private MultipartFile file;
    private String content;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
