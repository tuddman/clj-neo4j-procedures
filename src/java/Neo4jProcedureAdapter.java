package clj_neo4j_procedures;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;


public abstract class Neo4jProcedureAdapter {

    @Context
    public GraphDatabaseService db;

    @Context
    public Log log;

}
