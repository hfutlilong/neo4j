package com.example.biglong.dao.po;

import java.io.Serializable;
import java.util.Date;

public class SongPO implements Serializable {
    private Long id;

    private Long resourceId;

    private String url;

    private String title;

    private String subTitle;

    private String artistId;

    private String artistName;

    private Long albumId;

    private String albumName;

    private String playDuration;

    private Integer commentCount;

    private Integer crawlingStatus;

    private Integer commentCrawlingStatus;

    private Date dbUpdateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId == null ? null : artistId.trim();
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName == null ? null : artistName.trim();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName == null ? null : albumName.trim();
    }

    public String getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(String playDuration) {
        this.playDuration = playDuration == null ? null : playDuration.trim();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCrawlingStatus() {
        return crawlingStatus;
    }

    public void setCrawlingStatus(Integer crawlingStatus) {
        this.crawlingStatus = crawlingStatus;
    }

    public Integer getCommentCrawlingStatus() {
        return commentCrawlingStatus;
    }

    public void setCommentCrawlingStatus(Integer commentCrawlingStatus) {
        this.commentCrawlingStatus = commentCrawlingStatus;
    }

    public Date getDbUpdateTime() {
        return dbUpdateTime;
    }

    public void setDbUpdateTime(Date dbUpdateTime) {
        this.dbUpdateTime = dbUpdateTime;
    }
}