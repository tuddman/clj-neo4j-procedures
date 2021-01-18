; (def neo4j-version "3.1.2")

(defproject clj-neo4j-procedures "0.1.0-SNAPSHOT"
  :description "Neo4j Stored Procedures implemented in Clojure "
  :url "https://github.com/tuddman/clj-neo4j-procedures"
  :license {:name "public domain"
            :url "http://unlicense.org"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.neo4j/neo4j-graphdb-api "4.2.2"]
                 [org.neo4j/neo4j-procedure "4.2.2"]
                 [org.ajoberstar/ike.cljj "0.4.1"]
                 [im.chit/hara.reflect "2.5.10"]]
  
  :profiles {:test {:dependencies [#_[org.neo4j.test/neo4j-harness "4.0.0"]]}}
  :min-lein-version "2.9.5"

  ; :profiles {:provided {:dependencies [[org.neo4j/neo4j ~neo4j-version]]}}
  
  :aot :all

  :omit-source true

 :uberjar-name "clj-neo4j-procedures.jar"

  :global-vars {*warn-on-reflection* true}) 
