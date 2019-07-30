package com.example.biglong.dao.mapper;

import com.example.biglong.dao.po.SongPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongMapper {
    @Select("SELECT * FROM song")
    List<SongPO> queryAllSongs();
}
