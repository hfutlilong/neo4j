package com.example.biglong.controller;

import com.example.biglong.service.NLPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("nlp")
public class NLPController {
    @Autowired
    private NLPService nlpService;

    @RequestMapping("generateNeo4jGraph")
    @ResponseBody
    public String generateNeo4jGraph() {
        nlpService.generateNeo4jGraph();
        return "success";
    }
}
