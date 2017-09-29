package clj_neo4j_procedures.procedures;

import org.neo4j.graphdb.Node;

public class DenseNode
{
    public long nodeId;
    public long degree;

    public DenseNode( Node node )
    {
        this.nodeId = node.getId();
        this.degree = node.getDegree();
    }
}
