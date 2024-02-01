package com.example.demo.controller;

import com.example.demo.model.MessageRequest;
import com.example.demo.services.ChatService;
import com.theokanning.openai.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/getAllMessages/{threadId}")
    public List<Message> getAllMessages(@PathVariable String threadId) {
        return this.chatService.getAllMessages(threadId);
    }

    @PostMapping("/sendMsgToThreadAndRun")
    public List<Message> sendMessageToThread(@RequestBody MessageRequest request) {
        return this.chatService.sendMsgToThreadAndRun(request.getThreadId(), request.getMessage(), request.getAssistantId());
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, World";
    }

    @DeleteMapping("/deleteThread/{threadId}")
    public void deleteThread(@PathVariable String threadId) {
        this.chatService.deleteThread(threadId);
    }

}
