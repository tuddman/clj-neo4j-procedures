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

### TODO

* make this a lein template


