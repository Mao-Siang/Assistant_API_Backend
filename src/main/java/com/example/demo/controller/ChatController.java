package com.example.demo.controller;

import com.example.demo.model.MessageRequest;
import com.example.demo.services.ChatService;
import com.theokanning.openai.messages.Message;
import org.jetbrains.annotations.NotNull;
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

    @GetMapping("/get/{threadId}")
    public List<Message> getAllMessages(@PathVariable String threadId) {
        return this.chatService.getAllMessages(threadId);
    }

    @PostMapping("/sendMessageToThread")
    public String sendMessageToThread(@RequestBody MessageRequest request) {
        return this.chatService.sendMessageToThread(request.getThreadId(), request.getMessage(), request.getAssistantId());
    }

    @GetMapping("/test")
    public String test() {
        return "Hello, World";
    }

}
