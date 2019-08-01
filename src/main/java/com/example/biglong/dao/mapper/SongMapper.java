package com.example.biglong.dao.mapper;

import com.example.biglong.dao.po.MusicCommentPO;
import com.example.biglong.dao.po.SongPO;
import com.example.biglong.entity.MusicCommentBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongMapper {
    @Select("SELECT * FROM song")
    List<SongPO> queryAllSongs();

//    @Select("SELECT * FROM music_comment WHERE page_type = 1 ORDER BY like_count DESC LIMIT #{num}")
//    List<MusicCommentPO> getHotCommentList(Integer num);

    @Select("SELECT a.resource_id, b.title, b.sub_title, a.comment_id, a.comment_content FROM ( SELECT * FROM music_comment WHERE page_type = 1 ORDER BY like_count DESC LIMIT #{num} ) a LEFT JOIN song b ON b.resource_id = a.resource_id")
    List<MusicCommentBO> getHotCommentList(Integer num);
}
