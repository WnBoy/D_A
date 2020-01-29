package com.xupt.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 *
 * @author Wnlife
 * @create 2020-01-26 20:18
 */
public class Graph {

    /**
     * 存储节点的集合
     */
    private ArrayList<String> vertexList;
    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;
    /**
     * 表示边的数目
     */
    private int numOfEdges;

    /**
     * 记录每个节点是否被访问过
     */
    private boolean[] isVisited;

    public static void main(String[] args) {
        //节点数
        int n=5;
        //节点
        String[] vertexs = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph=new Graph(n);
        //循环增加节点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //增加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //打印图
        graph.showGraph();

        //测试dfs
        System.out.println("dfs");
        graph.dif();

        System.out.println("\n-----------");

        //测试bfs
        System.out.println("bfs");
        graph.bfs();

    }

    /**
     * 构造函数，初始化图
     * @param n 节点的个数
     */
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * 寻找当前节点的第一个邻接节点
     * @param index 当前节点的角标
     * @return 返回邻接节点的下标，没有找到就返回-1.
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 若当前节点的第一个邻接节点被访问过，则根据当前节点和第一个邻接节点，
     * 返回当前节点的下一个邻接节点。
     * @param v1 当前节点
     * @param v2 当前节点的第一个邻接节点
     * @return 当前节点的下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     * @param isVisited 记录每个接地那是否被访问过的数组
     * @param v 当前节点
     */
    private void dfs(boolean[] isVisited, int v) {
        //输出当前节点的值
        System.out.print(getValueByIndex(v) + "->");
        //将当前节点标记为读过
        isVisited[v]=true;
        //返回当前节点的第一个邻接节点
        int w = getFirstNeighbor(v);
        while (w != -1) {
            //如果第一个邻接节点没有被访问过
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果被访问过，则找下一个邻接节点
            w=getNextNeighbor(v,w);
        }
    }

    /**
     * dfs重载
     */
    public void dif(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param isVisited: 记录每个接地那是否被访问过的数组
     * @param v: 当前节点
     */
    private void bfs(boolean[]isVisited,int v){
        int u;
        int w;
        //存储已访问数组的对列
        LinkedList<Integer> queue=new LinkedList<>();
        //访问当前节点
        System.out.print(getValueByIndex(v)+"->");
        isVisited[v]=true;
        //将当前已经访问的值入列
        queue.addLast(v);
        while (!queue.isEmpty()){
            //取出对列的第一个元素
            u=(Integer) queue.removeFirst();
            //根据第一个元素得到他的邻接节点
            w = getFirstNeighbor(u);
            while (w!=-1){
                //没有被访问过
                if(!isVisited[w]){
                    //访问w
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w]=true;
                    queue.addLast(w);
                }
                //继续以u为基础节点，下一个邻接节点
                w=getNextNeighbor(u,w);
            }
        }
    }

    /**
     * 广度优先遍历的重载
     */
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }


    /**
     * 返回图中的节点数
     * @return 节点数
     */
    public int getVertexs(){
        return vertexList.size();
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        Arrays.stream(edges).forEach((v)->{
            System.out.println(Arrays.toString(v));
        });
    }

    /**
     * 得到边的数目
     * @return 得到边的数目
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
     * @param i 下标
     * @return 数据
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    /**
     * 返回两个节点的权值
     * @param v1 第一个节点的下标
     * @param v2 第二个节点的下标
     * @return 权值
     */
    public int getWeight(int v1, int v2) {
        return  edges[v1][v2];
    }

    /**
     * 插入节点
     * @param vertex 要插入的节点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 增加一条边
     * @param v1     表示第一个节点的下标
     * @param v2     表示第一个节点的下标
     * @param weight 表示权重
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
