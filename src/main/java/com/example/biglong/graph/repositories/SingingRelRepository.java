package com.example.biglong.graph.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingingRelRepository extends Neo4jRepository<SingingRelRepository, Long> {
    @Query("MATCH (a:`评论关键词`),(b:`歌曲`) WHERE a.歌手id = {singerId} AND b.歌曲id = {songId} CREATE (a)-[r:演唱]->(b) RETURN r")
    void createSingingRel(Long singerId, Long songId);

    @Query("MATCH (singer:`歌手`{`歌手id`:{singerId}})-[演唱]->(song:`歌曲`{`歌曲id`:{songId}}) RETURN count(*)")
    int countRel(Long singerId, Long songId);
}
