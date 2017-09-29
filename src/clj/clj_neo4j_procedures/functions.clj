(ns clj-neo4j-procedures.functions
  (:require [clojure.reflect :as reflect])
  (:import (org.neo4j.procedure Description Name UserFunction)
           (org.neo4j.graphdb GraphDatabaseService Label Node Relationship)
           (clj_neo4j_procedures base)))

(definterface INeo4jUserFunction
  (^long foo [])
  (^double add42 [^double number])
  (numberOfNodes []))

(defn -add42
  [number]
  (+ number 42))

(defn -numberOfNodes
  [this]
  (let [b (base.)]
  (.showState (base.))))


;; Example of Neo4j User Defined Functions

(deftype neo4j-user-function []  
  INeo4jUserFunction
  
  (^{UserFunction {} Description "a straighforward description example for foo"} foo [this] 42)

  (^{UserFunction {} Description "a good description for add42 is that it adds 42 to a number"}
    add42
    [this ^{Name {:value "number"}} number]
    (require 'clj-neo4j-procedures.functions)  ;; 'require' this namespace to be able to 'call out' to other functions defined within this (or another) namespace.
    (-add42 number))
  
  (^{UserFunction {} Description "get the number of nodes in the DB"}
    numberOfNodes
    [this]
    (require 'clj-neo4j-procedures.functions)  
    (-numberOfNodes this)))

