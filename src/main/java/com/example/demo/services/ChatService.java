// package reference: https://github.com/TheoKanning/openai-java/blob/main/service/src/test/java/com/theokanning/openai/service/ThreadTest.java

package com.example.demo.services;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.messages.Message;
import com.theokanning.openai.messages.MessageRequest;
import com.theokanning.openai.runs.Run;
import com.theokanning.openai.runs.RunCreateRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.Thread;
import com.theokanning.openai.threads.ThreadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.lang.String;

import java.util.List;

@Service
public class ChatService {

    private final OpenAiService service;

    @Value("${openai.api.key}")
    private String api_token;

    private String assistantId;

    @Autowired
    public ChatService() {
        String token = "";
        this.service = new OpenAiService(token);
        // Default Assistant
        this.assistantId = "asst_OGMgaJlHHgoqXuny8LqDVWYn";
    }

    public List<Message> getAllMessages(String threadId) {
        return service.listMessages(threadId).getData();
    }

    public String createThread() {
        // Create a new Thread and return the threadId
        ThreadRequest threadRequest = ThreadRequest.builder().build();
        Thread thread = service.createThread(threadRequest);

        return thread.getId();
    }

    public List<Message> sendMsgToThreadAndRun(String threadId, String content, String assistantId) {
        // Add message to thread by threadId and create run with assistantId
        if (threadId.isEmpty()) threadId = this.createThread();

        MessageRequest messageRequest = MessageRequest.builder().content(content).build();
        Message message = service.createMessage(threadId, messageRequest);
        if (assistantId.isEmpty()) assistantId = this.assistantId;

        threadId = this.createRunOnThread(threadId, assistantId);

        return this.getAllMessages(threadId);
    }

    public String createRunOnThread(String threadId, String assistantId) {
        // Create Run with Assistant (specified by assistantId)
        RunCreateRequest runCreateRequest = RunCreateRequest.builder().assistantId(assistantId).build();
        Run run = service.createRun(threadId, runCreateRequest);

        Run retrievedRun;
        do {
            retrievedRun = service.retrieveRun(threadId, run.getId());
        }
        while(!(retrievedRun.getStatus().equals("completed")) && !(retrievedRun.getStatus().equals("failed")));

        return threadId;
    }

    public boolean deleteThread(String threadId) {
        // Delete thread by OpenAI API
        DeleteResult deleteResult = service.deleteThread(threadId);
        return deleteResult.isDeleted();
    }

    public String getApi_token() {
        return this.api_token;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }
}
