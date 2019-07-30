package com.example.biglong.service.impl;

import com.example.biglong.dao.mapper.SongMapper;
import com.example.biglong.dao.po.SongPO;
import com.example.biglong.graph.domain.SingerDomain;
import com.example.biglong.graph.domain.SongDomain;
import com.example.biglong.graph.repositories.SingerRepository;
import com.example.biglong.graph.repositories.SingingRelRepository;
import com.example.biglong.graph.repositories.SongRepository;
import com.example.biglong.service.DrawNeo4jService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class DrawNeo4jServiceImpl implements DrawNeo4jService {
    private static final Logger logger = LogManager.getLogger(DrawNeo4jServiceImpl.class);

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private SingingRelRepository singingRelRepository;

    private Lock songLock = new ReentrantLock();
    private Lock singerLock = new ReentrantLock();
    private Lock relLock = new ReentrantLock();

    @Override
    public void drawNeo4j() {
        List<SongPO> allSongs = songMapper.queryAllSongs();
        if (CollectionUtils.isEmpty(allSongs)) {
            logger.info("no songs exists");
            return;
        }
        logger.info("queryAllSongs success, song num:{}.", allSongs.size());

        for (SongPO songPO : allSongs) {
            // 保存歌曲信息
            Long songId = songPO.getResourceId();
            SongDomain songDomain = new SongDomain();
            songDomain.setSongId(songId);
            songDomain.setTitle(songPO.getTitle());
            songDomain.setSubTitle(songPO.getSubTitle());

            songLock.lock();
            try {
                int repeatedSongCount = songRepository.countBySongId(songId);
                if (repeatedSongCount == 0) {
                    songRepository.save(songDomain);
                }
            } finally {
                songLock.unlock();
            }

            // 保存歌手信息
            String artistIds = songPO.getArtistId();
            String artistNames = songPO.getArtistName();
            if (StringUtils.isNotBlank(artistIds) && StringUtils.isNotBlank(artistNames)) {
                String[] artistIdsArr = artistIds.split(",");
                String[] artistNamesArr = artistNames.split(",");
                for (int i = 0; i < artistIdsArr.length; i++) {
                    Long singerId = Long.valueOf(artistIdsArr[i]);
                    SingerDomain singerDomain = new SingerDomain();
                    singerDomain.setSingerId(singerId);
                    singerDomain.setSingerName(artistNamesArr[i]);

                    singerLock.lock();
                    try {
                        int repeatedSingerCount = singerRepository.countBySingerId(songId);
                        if (repeatedSingerCount == 0) {
                            singerRepository.save(singerDomain);
                        }
                    } finally {
                        singerLock.unlock();
                    }

                    // 保存关系
                    relLock.lock();
                    try {
                        int repeatedRelCount = singingRelRepository.countRel(singerId, songId);
                        if (repeatedRelCount == 0) {
                            singingRelRepository.createSingingRel(singerId, songId);
                        }
                    } finally {
                        relLock.unlock();
                    }
                }
            }
        }
    }
}
