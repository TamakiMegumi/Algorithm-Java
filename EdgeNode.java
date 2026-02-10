package Algorithm;

public class EdgeNode {
    public int begin, end, weight;

    public EdgeNode(int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public EdgeNode() {
        this.begin = -1;
        this.end = -1;
        this.weight = -1;
    }

    public int getWeight() {
        return weight;
    }
}
