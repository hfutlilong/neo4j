package com.example.biglong.graph.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRelRepository extends Neo4jRepository<CommentRelRepository, Long> {
    @Query("MATCH (a:`评论关键词`),(b:`歌曲`) WHERE a.关键词 = {keyword} AND b.歌曲id = {songId} CREATE (a)-[r:评论]->(b) RETURN r")
    void createCommentRel(String keyword, Long songId);

    @Query("MATCH (a:`评论关键词`{`关键词`:{keyword}})-[评论]->(song:`歌曲`{`歌曲id`:{songId}}) RETURN count(*)")
    int countCommentRel(String keyword, Long songId);
}
