package lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsGenerator {

    public static List<KnapsackItem> generate(int quantity) {
        List<KnapsackItem> items = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            items.add(new KnapsackItem((int)(Math.random()*24+1),(int)(Math.random()*28+2) ));
        }
        return items;
    }
}
