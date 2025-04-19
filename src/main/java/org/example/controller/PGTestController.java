package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.ReadJSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PGTestController {
    private final VectorStore vectorStore;
    private final EmbeddingModel embeddingModel;
//    private final ReadJSONService readJSONService;

    Logger logger = LoggerFactory.getLogger(PGTestController.class);

    @Value("${spring.ai.vectorstore.pgvector.dimensions}") private String dimensions;

    @GetMapping("/pg-test")
    public String testsetset() throws IOException {
        logger.info(vectorStore.getName());
        logger.info("dimensions : "+dimensions);
        logger.info("Embedding Model : "+embeddingModel.toString()+", :::"+embeddingModel.dimensions());

        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));

        //insert json to pgvector
//        readJSONService.read();


// Add the documents to PGVector
//        vectorStore.add(documents);

// Retrieve documents similar to a query
//        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.builder().query("Spring").topK(5).build());

        return "OK";
    }


}
