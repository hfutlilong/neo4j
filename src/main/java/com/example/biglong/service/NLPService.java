package com.example.biglong.service;

import java.util.List;

public interface NLPService {
    List<String> getKeywords(String sentence);

    void generateNeo4jGraph();
}
