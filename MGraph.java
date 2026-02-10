package Algorithm;

import java.util.Scanner;
/**
 * 邻接矩阵
 */
public class MGraph{
    private int[][] arc;
    private char[] vexs;
    private int numNode,numEdge,numArc;
    public MGraph(int numNode){
        this.numNode=numNode;
        numEdge=0;
        numArc=0;
        arc=new int[numNode][numNode];
        vexs=new char[numNode];
        init();
    }
    public MGraph(){
        this(5);
    }
    public void init(){
        int i,j;
        for(i=0;i<numNode;i++){
            for(j=0;j<numNode;j++){
                if(i!=j){
                    arc[i][j]=Integer.MAX_VALUE;
                }
            }
        }
    }
    public void addEdge(int i,int j,int weight){
        arc[i][j]=weight;
        arc[j][i]=weight;
        numEdge++;
        numArc+=2;
    }
    public void addArc(int i,int j,int weight){
        arc[i][j]=weight;
        numArc++;
    }
    public int getWeight(int i,int j){
        return arc[i][j];
    }
    public int nodeSize(){
        return numNode;
    }
    public int edgeSize(){
        return numEdge;
    }
    public int arcSize(){
        return numArc;
    }
    public char getVex(int i){
        return vexs[i];
    }
    public void setEdge(int i,int j,int weight){
        arc[i][j]=weight;
        arc[j][i]=weight;
    }
    public void setVex(int i,char vex){
        vexs[i]=vex;
    }
    public void setArc(int i,int j,int weight){
        arc[i][j]=weight;
    }
    @Override
    public String toString(){
        String str="";
        int i,j;
        for(i=0;i<numNode;i++){
            for(j=0;j<numNode;j++){
                if(arc[i][j]==Integer.MAX_VALUE){
                    str+="∞ ";
                }
                else{
                    str+=arc[i][j]+" ";
                }
            }
            str+="\n";
        }
        return str;
    }
    public int inDegree(int i){
        int res=0;
        int j;
        for(j=0;j<numNode;j++){
            if(arc[j][i]!=Integer.MAX_VALUE&&j!=i){
                res++;
            }
        }
        return res;
    }
    public int outDegree(int i){
        int res=0;
        int j;
        for(j=0;j<numNode;j++){
            if(arc[i][j]!=Integer.MAX_VALUE&&j!=i){
                res++;
            }
        }
        return res;
    }
    public void removeEdge(int i,int j){
        arc[i][j]=Integer.MAX_VALUE;
        arc[j][i]=Integer.MAX_VALUE;
        numEdge--;
        numArc-=2;
    }
    public void removeArc(int i,int j){
        arc[i][j]=Integer.MAX_VALUE;
        numArc--;
    }
    public EdgeNode[] getEdgeNode(){
        EdgeNode[] res=new EdgeNode[numEdge];
        int i,j;
        int k=0;
        for(i=0;i<numNode;i++){
            for(j=i+1;j<numNode;j++){
                if(arc[i][j]!=Integer.MAX_VALUE){
                    res[k]=new EdgeNode(i,j,arc[i][j]);
                    k++;
                }
            }
        }
        return res;
    }
    public EdgeNode[] getArcNode(){
        EdgeNode[] res=new EdgeNode[numArc];
        int i,j;
        int k=0;
        for(i=0;i<numNode;i++){
            for(j=i;j<numNode;j++){
                if(arc[i][j]!=Integer.MAX_VALUE&&i!=j){
                    res[k]=new EdgeNode(i,j,arc[i][j]);
                }
            }
        }
        return res;
    }
}
