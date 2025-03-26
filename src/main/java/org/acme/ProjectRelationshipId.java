package org.acme;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProjectRelationshipId {


    @ManyToOne
//    @JoinColumn(nullable = false)
    ProjectEntity sourceProject;

    @ManyToOne
//    @JoinColumn(nullable = false)
    ProjectEntity targetProject;
}
