package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.PoseDto;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PoseService {
    private final VectorStore vectorStore;

    public List<PoseDto> search(String prompt) {
        List<Document> poses = vectorStore.similaritySearch(SearchRequest.builder()
                .query(prompt)
                .topK(3)
                .build());

        List<PoseDto> poseList = new ArrayList<>();
        assert poses != null;
        for (Document pose : poses) {
            Map<String, Object> metadata = pose.getMetadata();
            String description = (String) metadata.get("description");
            String photoUrl = (String) metadata.get("photo_url");
            String name = (String) metadata.get("name");
            poseList.add(PoseDto.builder()
                    .name(name)
                    .photoUrl(photoUrl)
                    .description(description)
                    .build());
        }
        return poseList;
    }
}
