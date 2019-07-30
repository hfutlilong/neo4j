package com.example.biglong.graph.repositories;

import com.example.biglong.graph.domain.SongDomain;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends Neo4jRepository<SongDomain, Long> {
}
