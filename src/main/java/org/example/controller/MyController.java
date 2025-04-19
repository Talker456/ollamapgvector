package org.example.controller;

import org.example.dto.MyRequest;
import org.example.dto.MyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
public class MyController {

    private final ChatClient chatClient;
    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    public MyController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    public String generation(Model model, @RequestParam(value = "message", defaultValue = "hello") String message) {
        String text = this.chatClient.prompt()
                .user(message)
                .call()
                .content();
        model.addAttribute("response",new MyResponse(text));
        return "ai";
    }

    @GetMapping("/stream")
    public String generateStream(Model model, @RequestParam(value = "message", defaultValue = "lion vs tiger") String message) {
        Flux<MyResponse> content = chatClient.prompt()
                .user(message)
                .stream()
                .content().map(MyResponse::new);


        model.addAttribute("content", content);
        return "streamai";
    }

    @GetMapping("/streams")
    public Flux<String> gen(@RequestParam(value = "message", defaultValue = "hello") String message) {
        logger.info(" *** GET MAPPING FOR /STREAMS ***");
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }

    //@PostMapping("/api/articles")
    //    public ResponseEntity<Article> addArticle(@RequestBody AddArticleResponse request, Principal principal) {
    //        Article savedArticle = blogService.save(request, principal.getName());
    //
    //        return ResponseEntity.status(HttpStatus.CREATED)
    //                .body(savedArticle);
    //    }
    @PostMapping("/streams")
    public Flux<String> genPost(@RequestBody MyRequest request) {
        return chatClient.prompt()
                .user(request.getMessage())
                .stream()
                .content();
    }


}