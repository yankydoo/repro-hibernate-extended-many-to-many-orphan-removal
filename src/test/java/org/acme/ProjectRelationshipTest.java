package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ProjectRelationshipTest {

    @Test
    void run() {
        Long pe2Id = createAndConnectProjects();

        disconnectProjects(pe2Id);

        ProjectEntity pe2 = ProjectEntity.findById(pe2Id);
        assertTrue(pe2.incomingRelationships.isEmpty());
        assertTrue(pe2.outgoingRelationships.isEmpty());
    }

    @Transactional
    void disconnectProjects(Long id) {
        ProjectEntity project = ProjectEntity.findById(id);
        project.outgoingRelationships.clear();
        // if you comment in the following line, the test works
        // project.persistAndFlush();
        project.incomingRelationships.clear();
        project.persist();
    }

    @Transactional
    Long createAndConnectProjects() {
        var pe1 = createProject();
        var pe2 = createProject();
        var pe3 = createProject();

        connectProjects(pe1, pe2);
        connectProjects(pe2, pe3);
        return pe2.id;
    }


    ProjectEntity createProject() {
        ProjectEntity pe = new ProjectEntity();
        pe.field = "lorem ipsum";
        pe.persist();
        return pe;
    }

    void connectProjects(ProjectEntity source, ProjectEntity target) {
        var pr = new ProjectRelationship();
        pr.id = new ProjectRelationshipId();
        pr.id.sourceProject = source;
        pr.id.targetProject = target;
        pr.type = RelationshipType.REFERENCES;
        source.outgoingRelationships.add(pr);
        target.incomingRelationships.add(pr);
        source.persist();
        target.persist();
    }
}
