(ns clj-neo4j-procedures.base
  (:gen-class
   :extends clj_neo4j_procedures.Neo4jProcedureAdapter
   :state state
   :init init
   :main false
   :methods [[showThis [] java.util.Map]
             [showState [] clojure.lang.PersistentArrayMap]]))

(defn -init []
  [[] (atom {})])

(defn -showState
  [this]
  @(.state this))

(defn -showThis
  [this]
  @this)
