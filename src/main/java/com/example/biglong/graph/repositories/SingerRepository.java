package com.example.biglong.graph.repositories;

import com.example.biglong.graph.domain.SingerDomain;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends Neo4jRepository<SingerDomain, Long> {
}
