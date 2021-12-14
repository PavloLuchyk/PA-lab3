package lab;

public class KnapsackItem {

    private int weight;

    private int cost;

    public KnapsackItem(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KnapsackItem{");
        sb.append("weight=").append(weight);
        sb.append(", cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
