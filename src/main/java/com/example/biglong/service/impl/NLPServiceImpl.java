package com.example.biglong.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.biglong.dao.mapper.SongMapper;
import com.example.biglong.entity.MusicCommentBO;
import com.example.biglong.graph.domain.CommentKeywordDomain;
import com.example.biglong.graph.domain.SongDomain;
import com.example.biglong.graph.repositories.CommentKeywordRepository;
import com.example.biglong.graph.repositories.CommentRelRepository;
import com.example.biglong.graph.repositories.SongRepository;
import com.example.biglong.service.NLPService;
import com.hankcs.hanlp.HanLP;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class NLPServiceImpl implements NLPService {
    private static final Logger logger = LogManager.getLogger(NLPServiceImpl.class);

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CommentKeywordRepository commentKeywordRepository;

    @Autowired
    private CommentRelRepository commentRelRepository;

    private Lock songLock = new ReentrantLock();
    private Lock commentLock = new ReentrantLock();
    private Lock relLock = new ReentrantLock();

    @Override
    public List<String> getKeywords(String sentence) {
        if (StringUtils.isBlank(sentence)) {
            return null;
        }
        return HanLP.extractKeyword(sentence, 5);
    }

    @Override
    @Async
    public void generateNeo4jGraph() {
        List<MusicCommentBO> musicCommentBOList = songMapper.getHotCommentList(10);
        if (CollectionUtils.isEmpty(musicCommentBOList)) {
            logger.error("musicCommentBOList is empty.");
            return;
        }

        for (int i = 0; i < musicCommentBOList.size(); i++) {
            MusicCommentBO musicCommentBO = musicCommentBOList.get(i);

            // 保存歌曲
            SongDomain songDomain = new SongDomain();
            Long songId = musicCommentBO.getResourceId();
            songDomain.setSongId(songId);
            songDomain.setTitle(musicCommentBO.getTitle());
            songDomain.setSubTitle(musicCommentBO.getSubTitle());

            songLock.lock();
            try {
                int repeatedSongCount = songRepository.countBySongId(songId);
                if (repeatedSongCount == 0) {
                    songRepository.save(songDomain);
                }
            } finally {
                songLock.unlock();
            }

            // 保存评论关键词
            String comment = musicCommentBO.getCommentContent();
            if (StringUtils.isBlank(comment)) {
                logger.error("CommentContent is blank, musicCommentBO:{}.", JSON.toJSONString(musicCommentBO));
                continue;
            }
            List<String> keywords = getKeywords(comment);
            if (CollectionUtils.isEmpty(keywords)) {
                logger.error("keywords list is empty, musicCommentBO:{}.", JSON.toJSONString(musicCommentBO));
                continue;
            }

            for (String keyword : keywords) {
                CommentKeywordDomain commentKeywordDomain = new CommentKeywordDomain();
                commentKeywordDomain.setKeyword(keyword);

                commentLock.lock();
                try {
                    int repeatedKeywordCount = commentKeywordRepository.countByKeyword(keyword);
                    if (repeatedKeywordCount == 0) {
                        commentKeywordRepository.save(commentKeywordDomain);
                    }
                } finally {
                    commentLock.unlock();
                }

                // 保存“(关键词)-[评论]->(歌曲)”关系
                relLock.lock();
                try {
                    int repeatedRelCount = commentRelRepository.countCommentRel(keyword, songId);
                    if (repeatedRelCount == 0) {
                        commentRelRepository.createCommentRel(keyword, songId);
                    }
                } finally {
                    relLock.unlock();
                }
            }
        }
    }
}
