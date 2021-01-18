(ns clj-neo4j-procedures.procedures
  (:import [org.neo4j.procedure Context Description Name Procedure]
           [org.neo4j.graphdb GraphDatabaseService Label Node Relationship]
           [java.util.stream Stream StreamSupport]))
