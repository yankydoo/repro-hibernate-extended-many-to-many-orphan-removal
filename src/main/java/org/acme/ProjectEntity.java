package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProjectEntity extends PanacheEntity {

    public String field;

    @OneToMany(mappedBy = "id.sourceProject", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProjectRelationship> outgoingRelationships = new ArrayList<>();
    @OneToMany(mappedBy = "id.targetProject", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProjectRelationship> incomingRelationships = new ArrayList<>();
}
