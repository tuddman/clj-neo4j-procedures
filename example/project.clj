(defproject example "0.1.0-SNAPSHOT"
  :description "example library using clj-neo4j-procedures"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-neo4j-procedures "0.1.0-SNAPSHOT"] ]
  :repl-options {:init-ns example.core}
  :main ^:skip-aot example.core)
