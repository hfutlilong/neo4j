package com.example.biglong.graph.domain;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "歌手")
public class SingerDomain {
    @Id
    @GeneratedValue
    private Long id;

    @Property("歌手id")
    private Long singerId;

    @Property("歌手名")
    private String singerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
