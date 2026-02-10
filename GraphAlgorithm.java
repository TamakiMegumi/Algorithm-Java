package Algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAlgorithm {
    private GraphAlgorithm() {
    }

    public static int prim(MGraph g) {
        int[] minDist = new int[g.nodeSize()];
        boolean[] visited = new boolean[g.nodeSize()];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        int i, j;
        for (i = 1; i < g.nodeSize(); i++) {
            int cur = -1;
            int min = Integer.MAX_VALUE;
            for (j = 0; j < g.nodeSize(); j++) {
                if (!visited[j] && minDist[j] < min) {
                    min = minDist[j];
                    cur = j;
                }
            }
            visited[cur] = true;
            for (j = 0; j < g.nodeSize(); j++) {
                if (!visited[j] && g.getWeight(cur, j) < minDist[j]) {
                    minDist[j] = g.getWeight(cur, j);
                }
            }
        }
        int res = 0;
        for (i = 1; i < g.nodeSize(); i++) {
            res += minDist[i];
        }
        return res;
    }

    public static int kruskal(EdgeNode[] g) {
        Arrays.stream(g).sorted(Comparator.comparingInt(EdgeNode::getWeight));
        int res = 0;
        UnionFind uf = new UnionFind(g.length);
        for (EdgeNode e : g) {
            if (!uf.isSame(e.begin, e.end)) {
                uf.merge(e.begin, e.end);
                res += e.weight;
                uf.merge(e.begin, e.end);
            }
        }
        return res;
    }

    public static int dijkstra(MGraph g, int start, int end) {
        int[] minDist = new int[g.nodeSize()];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[g.nodeSize()];
        minDist[start] = 0;
        int i, j;
        for (i = 0; i < g.nodeSize(); i++) {
            int minval = Integer.MAX_VALUE;
            int cur = start;
            for (j = 0; j < g.nodeSize(); j++) {
                if (!visited[j] && minDist[j] < minval) {
                    minval = minDist[j];
                    cur = j;
                }
            }
            visited[cur] = true;
            for (j = 0; j < g.nodeSize(); j++) {
                if (!visited[j] && g.getWeight(cur, j) != Integer.MAX_VALUE &&
                        minDist[cur] + g.getWeight(cur, j) < minDist[j]) {
                    minDist[j] = minDist[cur] + g.getWeight(cur, j);
                }
            }
        }
        if (minDist[end] == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist[end];
    }

    public static int BellmanFord(EdgeNode[] g, int start, int end) {
        int[] minDist = new int[g.length];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0;
        int i;
        for (i = 0; i < g.length - 1; i++) {
            for (EdgeNode e : g) {
                if (minDist[e.begin] != Integer.MAX_VALUE &&
                        minDist[e.end] > minDist[e.begin] + e.weight) {
                    minDist[e.end] = minDist[e.begin] + e.weight;
                }
            }
        }
        if (minDist[end] == Integer.MAX_VALUE) {
            return -1;
        }
        return minDist[end];
    }

    public static int floyd(MGraph g, int start, int end) {
        /**start,end为从0开始，g的节点数为n，对应坐标为n-1，默认包含0节点*/
        int i, j, k;
        int[][] dp = new int[g.nodeSize()][g.nodeSize()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (i = 0; i < g.nodeSize(); i++) {
            for (j = 0; j < g.nodeSize(); j++) {
                dp[i][j] = g.getWeight(i, j);
            }
        }
        for (k = 0; k < g.nodeSize(); k++) {
            for (i = 0; i < g.nodeSize(); i++) {
                for (j = 0; j < g.nodeSize(); j++) {
                    if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE &&
                            dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        if (dp[start][end] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[start][end];
    }

    public static int[] topologicalSort(GraphAdjList g) {
        int[] inDegree = new int[g.nodeSize()];
        int[] res = new int[g.nodeSize()];
        int i, j = 0;
        for (i = 0; i < g.nodeSize(); i++) {
            GraphAdjList.GraphAdjListEdgeNode node = g.getFirstEdge(i);
            while (node != null) {
                inDegree[node.adjvex]++;
                node = node.next;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (i = 0; i < g.nodeSize(); i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            res[j++] = cur;
            GraphAdjList.GraphAdjListEdgeNode node = g.getFirstEdge(cur);
            if (node != null) {
                for (; node != null; node = node.next) {
                    inDegree[node.adjvex]--;
                    if (inDegree[node.adjvex] == 0) {
                        q.offer(node.adjvex);
                    }
                }
            }
        }
        if (j != g.nodeSize()) {
            return null;
        }
        return res;
    }
    /**public static int[] criticalPath(GraphAdjList g){
     int[] inDegree=new int[g.nodeSize()];
     int[] res=new int[g.nodeSize()];
     int[] earliest=new int[g.nodeSize()];
     int[] latest=new int[g.nodeSize()];
     int i,j=0;
     for(i=0;i<g.nodeSize();i++){
     GraphAdjList.GraphAdjListEdgeNode node=g.getFirstEdge(i);
     while(node!=null){
     inDegree[node.adjvex]++;
     node=node.next;
     }
     }
     Queue<Integer> q=new LinkedList<>();
     for(i=0;i<g.nodeSize();i++){
     if(inDegree[i]==0){
     q.offer(i);
     }
     }
     while(!q.isEmpty()){
     int cur=q.poll();
     res[j++]=cur;
     GraphAdjList.GraphAdjListEdgeNode node=g.getFirstEdge(cur);
     if(node!=null){
     for(;node!=null;node=node.next){
     inDegree[node.adjvex]--;
     if(inDegree[node.adjvex]==0){
     q.offer(node.adjvex);
     }

     }

     }

     }
     if(j!=g.nodeSize()){
     return null;
     }
     for(i=0;i<g.nodeSize();i++){
     GraphAdjList.GraphAdjListEdgeNode node=g.getFirstEdge(i);
     while(node!=null){
     earliest[node.adjvex]=Math.max(earliest[node.adjvex],earliest[i]+g.getWeight(i,node.adjvex));
     node=node.next;
     }

     }
     for(i=g.nodeSize()-1;i>=0;i--){
     GraphAdjList.GraphAdjListEdgeNode node=g.getFirstEdge(i);
     while(node!=null){
     latest[i]=Math.min(latest[i],latest[node.adjvex]-g.getWeight(i,node.adjvex));
     node=node.next;
     }
     }
     return res;
     }*/
}
