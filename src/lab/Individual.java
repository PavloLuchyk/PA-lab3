package lab;

public class Individual {

    private int[] items;

    public Individual(int[] items) {
        this.items = items;
    }

    public Individual(int size) {
        this.items = new int[size];
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Individual{");
        sb.append("items=");
        if (items == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < items.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(items[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
