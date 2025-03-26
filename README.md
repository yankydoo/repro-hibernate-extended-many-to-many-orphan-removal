# Quarkus Entity Relationship Test Reproduction

This repository demonstrates a test case for bidirectional relationships between entities in a Quarkus application using Hibernate ORM with Panache.

## Overview

The test case illustrates an issue with clearing bidirectional relationships between project entities. The test creates a chain of three connected projects and then attempts to disconnect the middle project from both its incoming and outgoing relationships.

## Test Case Description

The test creates the following scenario:

1. Three project entities are created (PE1, PE2, PE3)
2. Relationships are established: PE1 → PE2 → PE3
3. The test then attempts to disconnect PE2 from both its relationships
4. The test verifies that PE2 has no remaining relationships

## Issue Demonstrated

The test fails because when clearing both the outgoing and incoming relationships collections without an explicit ```persistAndFlush()``` call between them, the changes aren't properly synchronized with the database.

As noted in the code comment, uncommenting the ```project.persistAndFlush()``` line after clearing the outgoing relationships makes the test pass.

## Entity Structure

The test involves the following entities:
- ```ProjectEntity```: The main entity with bidirectional relationships
- ```ProjectRelationship```: A join entity representing the relationship between projects
- ```ProjectRelationshipId```: A composite key for the relationship
- ```RelationshipType```: An enum defining the type of relationship

## How to Run

Execute the test using:

```bash
./mvnw test -Dtest=ProjectRelationshipTest
```

## Expected Behavior

When properly implemented, a project entity should be able to clear both its incoming and outgoing relationships, leaving it completely disconnected from other projects, without having to manually call ```persistAndFlush()``` between them.