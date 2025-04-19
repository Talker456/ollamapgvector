package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReadJSONService {
    private final VectorStore vectorStore;

    Logger logger = LoggerFactory.getLogger(ReadJSONService.class);

    public void read() throws IOException {
        String jsonFile = "yoga_poses_with_descriptions.json";
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(new ClassPathResource(jsonFile).getContentAsByteArray());

        List<Document> documents = new ArrayList<>();
        for (JsonNode jsonNode : rootNode) {
            String documentString = null;

            String name = jsonNode.get("name").asText();
            String sanskritName = jsonNode.get("sanskrit_name").asText();
            String photoUrl = jsonNode.get("photo_url").asText();
            String expertiseLevel = jsonNode.get("expertise_level").asText();
            String poseType = jsonNode.get("pose_type").toString();
            JsonNode candidates = jsonNode.get("description").get("candidates");
            String description = null;
            if (candidates!=null){
                for (JsonNode candidate : candidates) {
                    JsonNode parts = candidate.get("content").get("parts");
                    for (JsonNode part : parts) {
                        description = part.get("text").asText();
                    }
                }
            }

            name = name==null? "" : name;
            sanskritName = sanskritName==null? "":sanskritName;
            description = description==null? "" : description;
            expertiseLevel = expertiseLevel==null? "" : expertiseLevel;
            poseType = poseType==null? "": poseType;
            photoUrl = photoUrl == null ? "" : photoUrl;

            documentString = String.format(
                    "name : %s\ndescription : %s\nsanskrit_name: %s\nexpertise_level : %s\npose_type : %s",
                    name,description,sanskritName,expertiseLevel,poseType
            );

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("name", name);
            metadata.put("sanskrit_name", sanskritName);
            metadata.put("expertise_level", expertiseLevel);
            metadata.put("description", description);
            metadata.put("photo_url", photoUrl);
            metadata.put("pose_type", poseType);

            logger.info(documentString);
            logger.info(metadata.toString());

            Document document = new Document(documentString, metadata);
            documents.add(document);
        }

        vectorStore.add(documents);
    }
}
