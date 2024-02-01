# Assistant API Backend
- Built with Spring boot and OpenAI Assistant API
- Implement with [TheoKanning/openai-java](https://github.com/TheoKanning/openai-java)
## APIs

### List All Messages in Thread
- Method: GET
- API Endpoint: localhost:8080/getAllMessages/{threadId}
- if `threadId` is empty, it will create a new thread for you(for starting a new conversation)
- return a list of `message` objects
```dtd
[
  {
    "id": "msg_RHS3LyzVi9vhF4MMGsvC9IXX",
    "object": "thread.message",
    "role": "assistant",
    "content": [
      {
        "type": "text",
        "text": {
          "value": "Hello Vincent! How can I assist you today?",
          "annotations": []
        },
        "image_file": null
      }
    ],
    "metadata": {},
    "created_at": 1706755965,
    "thread_id": "thread_rJGThxD1CU1ImCY2DE5lTA7i",
    "assistant_id": "asst_OGMgaJlHHgoqXuny8LqDVWYn",
    "run_id": "run_nuvBrKRhR4Gpcb7NvVY7OvaM",
    "file_ids": []
  },
  {
    "id": "msg_F1golJo8aKkzmi8kmmJ8hOoO",
    "object": "thread.message",
    "role": "user",
    "content": [
      {
        "type": "text",
        "text": {
          "value": "Hi, I am Vincent.",
          "annotations": []
        },
        "image_file": null
      }
    ],
    "metadata": {},
    "created_at": 1706755962,
    "thread_id": "thread_rJGThxD1CU1ImCY2DE5lTA7i",
    "assistant_id": null,
    "run_id": null,
    "file_ids": []
  },
  {
    "id": "msg_kw4AlY2nvGu6r8fZnD6THxCU",
    "object": "thread.message",
    "role": "user",
    "content": [
      {
        "type": "text",
        "text": {
          "value": "I am testing",
          "annotations": []
        },
        "image_file": null
      }
    ],
    "metadata": {},
    "created_at": 1706753972,
    "thread_id": "thread_rJGThxD1CU1ImCY2DE5lTA7i",
    "assistant_id": null,
    "run_id": null,
    "file_ids": []
  }
]
```
### Send a New Message to Thread and Run
- Method: POST
- API Endpoint: localhost:8080/sendMsgToThreadAndRun
- Request Body:
  - `threadId`: the ID of the thread
  - `content`: the new message content
  - `assistantId`: the specific assistant to run
- Function: Add a new message to the thread and run with assistant.
- Return: a list of `message` objects in thread

### Delete Thread
- Method: DELETE
- API Endpoint: localhost:8080/deleteThread/{threadId}
- Return: None

### Procedure
- Start a new chat room with `sendMessageToThread` API with a message and get the response.
- Add a new message with the same API and get the response
- List all messages if needed by `getAllMessages` API
- Delete Thread after the chat finishes