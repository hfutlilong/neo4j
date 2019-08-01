package com.example.biglong.graph.repositories;

import com.example.biglong.graph.domain.CommentKeywordDomain;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentKeywordRepository extends Neo4jRepository<CommentKeywordDomain, Long> {
    @Query("MATCH(comment:`评论关键词`{`关键词`:{keyword}}) RETURN count(*)")
    int countByKeyword(String keyword);
}
