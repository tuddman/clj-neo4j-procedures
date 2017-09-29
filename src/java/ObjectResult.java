package clj_neo4j_procedures.procedures;

/**
 * from https://github.com/neo4j-contrib/neo4j-apoc-procedures/blob/3.1/src/main/java/apoc/result/ObjectResult.java
 * @author mh
 * @since 26.02.16
 */
public class ObjectResult {
    public final Object value;

    public ObjectResult(Object value) {
        this.value = value;
    }
}
