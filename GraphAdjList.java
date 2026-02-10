package Algorithm;

public class GraphAdjList {
    public class GraphAdjListEdgeNode {
        int adjvex;
        int weight;
        GraphAdjListEdgeNode next;

        public GraphAdjListEdgeNode() {
            this.adjvex = -1;
            this.weight = -1;
            this.next = null;
        }

        public GraphAdjListEdgeNode(int adjvex, int weight) {
            this.adjvex = adjvex;
            this.weight = weight;
        }

        public GraphAdjListEdgeNode(int adjvex, int weight, GraphAdjListEdgeNode next) {
            this.adjvex = adjvex;
            this.weight = weight;
            this.next = next;
        }
    }

    private int numNode, numEdge, numArc;
    private GraphAdjListEdgeNode[] firstedge;
    private char[] key;

    public GraphAdjList() {
        this.numNode = 0;
        numEdge = 0;
        numArc = 0;
        firstedge = null;
    }

    public GraphAdjList(int numNode) {
        this.numNode = numNode;
        numEdge = 0;
        numArc = 0;
        firstedge = new GraphAdjListEdgeNode[numNode];
        for (int i = 0; i < numNode; i++) {
            firstedge[i] = new GraphAdjListEdgeNode();
        }
    }

    public void addEdge(int i, int j, int weight) {
        GraphAdjListEdgeNode node = new GraphAdjListEdgeNode(j, weight);
        node.next = firstedge[i].next;
        firstedge[i].next = node;
        GraphAdjListEdgeNode node2 = new GraphAdjListEdgeNode(i, weight);
        node.next = firstedge[j].next;
        firstedge[j].next = node2;
        numEdge++;
        numArc += 2;
    }

    public void addArc(int i, int j, int weight) {
        GraphAdjListEdgeNode node = new GraphAdjListEdgeNode(j, weight);
        node.next = firstedge[i].next;
        firstedge[i].next = node;
        numArc++;
    }

    public int getWeight(int i, int j) {
        GraphAdjListEdgeNode node = firstedge[i].next;
        while (node != null) {
            if (node.adjvex == j) {
                return node.weight;
            }
            node = node.next;
        }
        return Integer.MAX_VALUE;
    }

    public int nodeSize() {
        return numNode;
    }

    public int edgeSize() {
        return numEdge;
    }

    public int arcSize() {
        return numArc;
    }

    public void setEdge(int i, int j, int weight) {
        setArc(i, j, weight);
        setArc(j, i, weight);
    }

    public void setArc(int i, int j, int weight) {
        GraphAdjListEdgeNode node = firstedge[i].next;
        while (node != null) {
            if (node.adjvex == j) {
                node.weight = weight;
                break;
            }
            node = node.next;
        }
    }

    public void setVex(int i, char vex) {
        key[i] = vex;
    }

    public char getVex(int i) {
        return key[i];
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < numNode; i++) {
            str += key[i] + "->";
            GraphAdjListEdgeNode node = firstedge[i].next;
            while (node != null) {
                str += key[node.adjvex] + "(" + node.weight + ")->";
                node = node.next;
            }
            str += "\n";
        }
        return str;
    }

    public int inDegree(int i) {
        int count = 0;
        GraphAdjListEdgeNode node;
        for (int j = 0; j < numNode; j++) {
            node = firstedge[j].next;
            while (node != null) {
                if (node.adjvex == i) {
                    count++;
                }
                node = node.next;
            }
        }
        return count;
    }

    public int outDegree(int i) {
        int count = 0;
        GraphAdjListEdgeNode node = firstedge[i].next;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public GraphAdjListEdgeNode getFirstEdge(int i) {
        return firstedge[i].next;
    }
}


