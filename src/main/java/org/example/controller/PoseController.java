package org.example.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.dto.PoseDto;
import org.example.service.PoseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PoseController {
    private final PoseService poseService;
    Logger logger = LoggerFactory.getLogger(PoseController.class);

    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "message") String message) {
        List<PoseDto> poses = poseService.search(message);
        model.addAttribute("poses", poses);
        return "pose";
    }
}
