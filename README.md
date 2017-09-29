# clj-neo4j-procedures

Examples of Neo4j Stored Procedures implemented in Clojure

#### User-Defined Procedures

see `src/clj-neo4j-procedures/procedures.clj` for examples of user-defined stored procedures

#### User-Defined Functions

see `src/clj-neo4j-procedures/functions.clj` for examples of user-defined functions

## Why?

Neo4j is cool.  I use it a lot. 

Clojure is cool.  I use it a lot.

So wouldn't it be cool if they got hitched?

Basically playing around to see if I could get this to work.

I wanted to be able to leverage the power of the graph, and the power of Clojure the programming language, and do it in a way that wasn't constrained to _only_ using cypher queries.  Cypher is great, and can be used directly to achieve many things. There were those times, though, that cypher felt somewhat constrained and limiting as soon as things got beyond the basics.

By exposing User-Defined Procedures & Functions to being able to be called from Clojure directly on the graph, it opens up interesting possibilities for what is possible to *elegantly* achieve.  Like, for example, [auto-reconciliation]() between disparate Datastores.  Or whatever you can think of ;-)


## Status

**WARNING**  _EXPERIMENTAL_

definitely **NOT** ready yet for primetime production usage

## Usage

Use this repo as a springboard to dream up something cool that you can now do by coding directly against a Neo4j graph db. 

`lein uberjar` will produce a jar file, `clj-neo4j-procedures-0.1.0-SNAPSHOT-standalone.jar`, that you should place in the `$NEO4J_HOME/plugins` folder.   Although you should probably change the .jar  filename in `project.clj` to something else more appropriate to your project.

Restart Neo4j for it to pick up your new plugin.

Now you can freely use your custom defined function(s) and procedure(s) directly in cypher.  e.g.:

```SQL
# In cypher-land, e.g.
CALL myplugin.myprocedure() YIELD ... 

RETURN myplugin.myfunction() AS ...  
```

## Utility Tool

Neo4j Procedures work by dropping a *.jar file in `NEO4J_HOME/plugins` folder.  It is recommended that Neo4j NOT be running when adding new plugins.

I wrote a little nodejs script to help with: stopping neo4j -> compiling a new uberjar -> copying the uberjar into the `/plugins` folder -> restarting neo4j

run `./resources/buildnload.js` to try it out


#### Casting to Neo4J recognized types:

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

## Related Projects

If you're reading this and have interest in Clojure & Neo4j, then also have a look at [neocons](https://github.com/michaelklishin/neocons)

Two popular Neo4j plugins that use procedures and functions extensively (written in Java):

* [apoc](https://github.com/neo4j-contrib/neo4j-apoc-procedures)
* [spatial](https://github.com/neo4j-contrib/spatial)

## Credit

I drew from the following libraries

- [clj-neo4j-proc](https://github.com/ducky427/clj-neo4j-proc)
- [neo4j-procedure-template](https://github.com/neo4j-examples/neo4j-procedure-template)



### no License

Copyright © 2017 tuddman

This work is hereby released into the public domain, for the benefit of all in the hopes that is useful.

Pull Requests Welcome
