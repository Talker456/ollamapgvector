package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FluxController {

    private final ChatClient chatClient;
    private final Logger logger = LoggerFactory.getLogger(FluxController.class);

    public FluxController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @PostMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux(@RequestParam(value = "message") String message) {
//        return chatClient.prompt()
//                .user(message)
//                .stream()
//                .content();

        logger.info(" *** POST MAPPING FOR /FLUX *** ");
        return Flux.just("sentence1", "sentence2", "sentence3", "sentence4", "sentence5");
    }
}
