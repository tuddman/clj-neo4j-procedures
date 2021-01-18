## links and stuff:

* I learned some stuff about Neo4j procedures & functions from [here](https://neo4j.com/developer/procedures-functions/) and [here](https://neo4j.com/developer/procedures-gallery/)  
* modeled procedures example [on this](https://github.com/neo4j/neo4j-documentation/blob/0dd013f399811249155c827f8f8c9a46d6fe0a09/manual/embedded-examples/src/main/java/org/neo4j/examples/ProcedureExample.java)
* [JavaDoc for Procedures](http://neo4j.com/docs/java-reference/3.0/javadocs/index.html?org/neo4j/procedure/Procedure.html)
* [some stuff on annotations from SO](http://stackoverflow.com/questions/7703467/attaching-metadata-to-a-clojure-gen-class?rq=1)
* Rich Hickey's [Gist on Annotations](https://gist.github.com/richhickey/377213)
* Clojure Test Examples [with Annotations](https://github.com/clojure/clojure/blob/master/test/clojure/test_clojure/genclass/examples.clj)
* [old SO thread on passing typed collections from clojure to java](http://stackoverflow.com/questions/3688730/how-to-pass-a-typed-collection-from-clojure-to-java)
* procedures [in scala](http://stackoverflow.com/questions/38313019/neo4j-3-0-3-stored-procedures-in-scala)
* [G Groups discussion :re clj<->streams](https://groups.google.com/forum/#!topic/clojure/xzHrDoKF0v8)
* some note about [ReferencePipeline](http://stackoverflow.com/questions/24196082/what-is-the-default-implementation-of-stream-in-java-8#comment48934957_24203010) being the only type of abstract implementation of stream, actually.
* [function with @Context db](https://github.com/neo4j-contrib/neo4j-apoc-procedures/blob/3.1/src/main/java/apoc/map/Maps.java#L55)
* [SO comment on meta ^tags](http://stackoverflow.com/questions/6484899/type-hinting-return-value-with-or-tag-meta?rq=1)
* [more SO notes on Annotations](https://stackoverflow.com/questions/15123214/clojure-macro-using-gen-class-doesnt-create-annotations)

#### REPL

```clojure
lein repl
(require '[hara.reflect :as r])
(require '[clojure.reflect :as reflect])
(def foo (new clj_neo4j_procedures.functions.neo4j-user-function))
(reflect/reflect foo)
(.add42 foo 5)  => 47
```

#### Stream->clj

```clojure
; how to consume a java.util.stream in clojure
(-> stream         ; start with a (Stream/of ...)  which produces a ReferencePipeline
    .iterator      ; transforms it into a java.util.Spliterators$1Adapter
    iterator-seq)  ; transforms it into a clojure seq.

; => A seq of the original stream ... 
```

So, need to go from (coll?) of `db.getAllNodes()` -> a `(.stream)` ->  (stream's impl of) `map (new OutputObject)`  e.g.


#### clj->Stream < OutputObject > 

```clojure

???

```

#### exception in neo4j log 

```bash
Caused by: org.neo4j.kernel.lifecycle.LifecycleException: Component 'org.neo4j.kernel.impl.proc.Procedures@4496c263' was successfully initialized, but failed to start. Please see attached cause exception.
   at org.neo4j.kernel.lifecycle.LifeSupport$LifecycleInstance.start(LifeSupport.java:443)
   at org.neo4j.kernel.lifecycle.LifeSupport.start(LifeSupport.java:107)
   at org.neo4j.kernel.impl.factory.GraphDatabaseFacadeFactory.initFacade(GraphDatabaseFacadeFactory.java:189)
... 9 more
Caused by: org.neo4j.kernel.api.exceptions.ProcedureException: Procedures must return a Stream of records, where a record is a concrete class
that you define, with public non-final fields defining the fields in the record.
If you''d like your procedure to return `Object`, you could define a record class like:
public class Output '{'
    public Object out;
    '}'

    And then define your procedure as returning `Stream<Output>`.

```

and the other one:  when `(deftype neo4j-user-function [^{Context true} ^GraphDatabaseService db]` 

```bash
Caused by: org.neo4j.kernel.api.exceptions.ProcedureException: Unable to find a usable public no-argument constructor in the class `neo4j-user-function`. Please add a valid, public constructor, recompile the class and try again.
 at org.neo4j.kernel.impl.proc.ReflectiveProcedureCompiler.constructor(ReflectiveProcedureCompiler.java:260)
 at org.neo4j.kernel.impl.proc.ReflectiveProcedureCompiler.compileFunction(ReflectiveProcedureCompiler.java:94)
```

```clojure
(str this)
; => clj_neo4j_procedures.functions.neo4j-user-function@21535c74
```

and

```clojure
; if you don't have:
(require 'clj-neo4j-procedures.functions)   ; in the deftype method declarations

; you get =>
Failed to invoke function `clj_neo4j_procedures.functions.numberOfNodes`: Caused by: java.lang.IllegalStateException: Attempting to call unbound fn: #'clj-neo4j-procedures.functions/-numberOfNodes
```

```clojure
; in clojure-land, to find out what annotations are attached:
(seq 
  (.getAnnotations 
    (.getMethod clj_neo4j_procedures.functions.neo4j-user-function "foo" nil)))
```




#### Casting to Neo4J recognized types:

[reference](https://neo4j.com/docs/java-reference/current/extending-neo4j/procedures-and-functions/values-and-types/)

When declaring the input type hinting, the argument type must be, or must coerce to, one of the following:

`boolean`, `java.lang.Boolean`, `double`, `java.lang.Double`, `java.lang.Long`, `java.lang.Number`, `java.lang.Object`, `java.lang.String`, `java.util.List`, `java.util.Map`, `long`, `org.neo4j.graphdb.Node`, `org.neo4j.graphdb.Path`, `org.neo4j.graphdb.Relationship`, `org.neo4j.graphdb.spatial.Geometry`, `org.neo4j.graphdb.spatial.Point`


Neo4j Stored Procedures produce [Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) of records. When declaring the record output type, each record must return a declared class containing, or before returning must coerce into, one (or more) of the following public non-final field types:

`String`, `Long`, `Double`, `Number`, `Boolean`, `org.neo4j.graphdb.Node`, `org.neo4j.graphdb.Relationship`, `org.neo4j.graphdb.Path`
`java.util.Map` with key `String` and value `Object`,
  `java.util.List` of elements of any valid field type, including  `java.util.List`,
  `Object` - meaning any of the valid field types


####

  Procedures can have a few [modes](https://neo4j.com/docs/java-reference/3.1/javadocs/index.html?org/neo4j/procedure/Procedure.html)

  `DBMS` `DEFAULT` `READ` `WRITE` `SCHEMA`




### TODO

* make this a lein template




## Scratch

```clojure

;; User Defined Procedures

(definterface INeo4jProcedure
 (findDenseNodes [^Number threshold] "clojure docstring for findDenseNodes goes here"))

#_(defn ^Stream -findDenseNodes
    [this db threshold]
    (Stream/of (map #(CljObjectResult. %) (^java.util.stream.Stream .stream (.getAllNodes db)))))

#_(deftype
    neo4j-procedure []
    INeo4jProcedure
    (^{Procedure {:mode "READ"}
     Description "Finds all nodes in the database with more relationships than the specified threshold."}
     findDenseNodes
     [this ^{Name {:value "threshold"}} threshold]
     (require 'clj-neo4j-procedures.procedures)
     (-findDenseNodes this threshold)))



#_(defrecord CljObjectResult [^java.lang.Object obj])


#_(defn returnStream
    [n]
    (-> n
     ; (doto  (java.util.ArrayList.)  (.add 1)  (.add 2))
     (.stream)
#_(.flatMap  (reify java.util.function.Function  (apply  [_ arg]  (->CljObjectResult arg))))
#_(.collect  (java.util.stream.Collectors/toList))))

#_(defn retStream
    [n]
    (-> n
     (.stream)
     ;        .iterator
#_(StreamSupport/stream false)))

    ; (type %1)
    ; => java.util.stream.ReferencePipeline$3
    (comment 
     (->  
      (doto (java.util.ArrayList.) (.add 1) (.add 2))  
      (.stream)  
      (.map (reify java.util.function.Function (apply [_ arg] (inc arg))))  
      (.collect  (java.util.stream.Collectors/toList))))
 


;;  User defined Functions

  (definterface INeo4jUserFunction
   (^long foo [])
   (^double add42 [^double number])
   (numberOfNodes []))

  (defn -add42
   [number]
   (+ number 42))


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



```
