# clj-neo4j-procedures

Neo4j Stored Procedures + Functions implemented in Clojure

see `example/` folder  for examples of user-defined stored procedures, functions and aggregation functions.

## Why?

I wanted to be able to leverage the power of the graph, and the power of Clojure the programming language, and do it in a way that wasn't constrained to _only_ using cypher queries.  Cypher is great, and can be used directly to achieve many things. There were those times, though, that cypher felt somewhat constrained and limiting as soon as things got beyond the basics.

By exposing User-Defined Procedures & Functions to being able to be called from Clojure directly on the graph, it opens up interesting possibilities for what is possible to *elegantly* achieve.  

## Status

**WARNING**  _EXPERIMENTAL_

definitely **NOT** ready yet for production usage

## Usage

`lein uberjar` will produce a jar file that you should place in the `$NEO4J_HOME/plugins` folder.   

Restart Neo4j for it to pick up your new plugin.

Now you can freely use your custom defined function(s) and procedure(s) directly in cypher.  e.g.:

```SQL
# In cypher-land, e.g.
CALL myplugin.myprocedure() YIELD ... 

RETURN myplugin.myfunction() AS ...  
```



## Credit

I drew from the following libraries

- [clj-neo4j-proc](https://github.com/ducky427/clj-neo4j-proc)
- [neo4j-procedure-template](https://github.com/neo4j-examples/neo4j-procedure-template)


### no License

Copyright © 2017 tuddman

This work is hereby released into the public domain, for the benefit of all in the hopes that is useful.

Pull Requests Welcome
