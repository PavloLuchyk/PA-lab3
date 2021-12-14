package lab;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<KnapsackItem> knapsackItems = ItemsGenerator.generate(100);
        knapsackItems.forEach(System.out::println);
        Individual solution = Genetic.genetic(knapsackItems, 250);
        System.out.println("Solution: " + solution);
        System.out.println("Solution's cost: " + Genetic.calculateCost(knapsackItems,solution));
        System.out.println("Solution's weight: " + Genetic.calculateWeight(knapsackItems,solution));
    }
}
