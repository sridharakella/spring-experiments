package me.loki2302.entities;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassNodeRepository extends GraphRepository<ClassNode> {
    ClassNode findByName(String name);

    @Query("MATCH (c:ClassNode) RETURN ID(c) AS id, c.name AS name")
    List<ClassNodeIdAndName> getAllIdsAndNames();

    @Query("MATCH (a:ClassNode)-[r:USES]->(b:ClassNode) RETURN a")
    List<ClassNode> findAllClassesThatDependOnOtherClasses();

    @Query("MATCH (a:ClassNode)-[r:USES]->(b:ClassNode) RETURN b")
    List<ClassNode> findAllClassesThatOtherClassesDependOn();

    @Query("MATCH (dependent:ClassNode {name: {dependentName}})-[r:USES]->(dependency:ClassNode) RETURN dependency")
    List<ClassNode> findAllDependencyClasses(@Param("dependentName") String dependentName);

    @Query("MATCH (dependent:ClassNode)-[r:USES]->(dependency:ClassNode {name: {dependencyName}}) RETURN dependent")
    List<ClassNode> findAllDependentClasses(@Param("dependencyName") String dependencyName);
}
