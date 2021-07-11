package graph;

import com.sun.istack.internal.NotNull;
import graph.structures.Edge;
import unionfindset.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;

import graph.structures.Graph;

/**
 * A minimum spanning tree (MST) or minimum weight spanning tree
 * is a subset of the edges of a connected, edge-weighted
 * undirected graph that connects all the vertices together,
 * without any cycles and with the minimum possible total edge weight.
 */
public class MinimumSpanningTree {

    /**
     * Kruskal's algorithm is a minimum-spanning-tree algorithm
     * which finds an edge of the least possible weight that
     * connects any two trees in the forest.
     *
     * @param graph graph
     */
    public static <K, V, E> Graph<K, V, E>
    kruskalAlgorithm(@NotNull Graph<K, V, E> graph, @NotNull Comparator<Edge<K, E>> e) {
        Graph<K, V, E> mst = new Graph<>(false);
        ArrayList<Edge<K, E>> l;
        UnionFind<K> uf = new UnionFind<>();
        Edge<K, E> elem;

        graph.getNodes().forEach(n -> uf.makeSet(n.getKey()));

        l = graph.getEdges();
        l.sort(e);

        //TODO check condition
        for (Edge<K, E> keEdge : l) {
            elem = keEdge;
            if (uf.findSet(elem.getFrom()) != uf.findSet(elem.getTo())) {
                mst.addNodesEdge(elem.getFrom(), elem.getTo(), elem.getLabel());
                uf.union(elem.getFrom(), elem.getTo());
            }
        }

        return mst;
    }
}