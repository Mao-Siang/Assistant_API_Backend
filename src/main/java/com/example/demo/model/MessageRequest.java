package com.example.demo.model;

public class MessageRequest {
    private String threadId;
    private String content;

    private String assistantId;

    public String getThreadId() {
        return this.threadId;
    }
    public String getMessage() {
        return this.content;
    }

    public String getAssistantId() {
        return this.assistantId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }
}
