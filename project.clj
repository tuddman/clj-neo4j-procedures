(def neo4j-version "3.1.2")

(defproject clj-neo4j-procedures "0.1.0-SNAPSHOT"
  :description "Neo4j Stored Procedures implemented in Clojure "
  :url "https://github.com/tuddman/clj-neo4j-procedures"
  :license {:name "public domain"
            :url "http://unlicense.org"}
  :dependencies [[org.clojure/clojure "1.9.0-beta1"]
                 [org.ajoberstar/ike.cljj "0.3.0-SNAPSHOT"]
                 [im.chit/hara.reflect "2.5.2"]]
  
  :min-lein-version "2.5.3"

  :profiles {:provided {:dependencies [[org.neo4j/neo4j ~neo4j-version]]}}
  
  :aot :all

  :omit-source true

  :java-source-paths  ["src/java"]

  :source-paths  ["src/clj"]

;  :uberjar-name "clj-neo4j-procedures-0.1.0-SNAPSHOT-standalone.jar"

  :global-vars {*warn-on-reflection* true}) 
