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
            songRepository.save(songDomain);

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
                    singerRepository.save(singerDomain);
                    // 保存关系
                    singingRelRepository.createSingingRel(singerId, songId);
                }
            }
        }
    }
}
