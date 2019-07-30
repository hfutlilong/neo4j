package com.example.biglong.graph.domain;

import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "歌曲")
public class SongDomain {
    @Id
    @GeneratedValue
    private Long id;

    @Property("歌曲id")
    private Long songId;

    @Property("歌曲名")
    private String title;

    @Property("副标题")
    private String subTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
