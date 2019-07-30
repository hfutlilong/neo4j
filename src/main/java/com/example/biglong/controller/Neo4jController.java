package com.example.biglong.controller;

import com.example.biglong.service.DrawNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/neo4j")
public class Neo4jController {
    @Autowired
    private DrawNeo4jService drawNeo4jService;

    @RequestMapping("/draw")
    @ResponseBody
    public String draw() {
        drawNeo4jService.drawNeo4j();
        return "success";
    }
}
