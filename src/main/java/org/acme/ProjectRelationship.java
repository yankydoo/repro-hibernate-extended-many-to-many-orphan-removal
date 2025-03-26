package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class ProjectRelationship extends PanacheEntityBase {

    @EmbeddedId
    public ProjectRelationshipId id;

    @Enumerated(EnumType.STRING)
    public RelationshipType type;
}
