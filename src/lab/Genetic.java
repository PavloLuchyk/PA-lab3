package lab;

import java.util.*;

public class Genetic {

    public static Individual genetic(List<KnapsackItem> knapsackItems, int knapsackCapacity) {
        List<Individual> population = getInitialPopulation(knapsackItems.size());
        int iterationCount = 0;
        while (iterationCount < 1_001) {
            Individual parent1 = getFittest(knapsackItems, population);
            Individual parent2 = getRandom(population, parent1);
            Individual child = crossover(parent1, parent2);
            if (Math.random() <= 0.1) {
                mutation(child);
            }
            localImprovement(knapsackItems, child);
            if (calculateWeight(knapsackItems, child) > knapsackCapacity) {
                continue;
            }
            population.add(child);
            population.remove(getWeakest(knapsackItems, population));
            if (iterationCount%20 == 0) {
                Individual fittest = getFittest(knapsackItems, population);
                System.out.println("Iteration № " + iterationCount);
                System.out.println("Fittest's cost: " + calculateCost(knapsackItems,fittest));
                System.out.println("Fittest's weight: " + calculateWeight(knapsackItems,fittest));
                System.out.println("----------------------------------------------------------");
            }
            iterationCount++;
        }
        return getFittest(knapsackItems, population);
    }

    public static List<Individual> getInitialPopulation(int size) {
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int[] items = new int[size];
            items[i] = 1;
            population.add(new Individual(items));
        }
        return population;
    }

    public static Individual getRandom(List<Individual> individuals, Individual individual) {
        int index = individuals.indexOf(individual);
        int randomIndex = (int)(Math.random()*99);
        while (index == randomIndex) {
            randomIndex = (int)(Math.random()*99);
        }
        return individuals.get(randomIndex);
    }

    public static void mutation(Individual individual) {
        int index1 = (int)(Math.random()*99);
        int index2 = (int)(Math.random()*99);
        int[] items = individual.getItems();
        int tmp = items[index1];
        items[index1] = items[index2];
        items[index2] = tmp;
    }


    public static Individual crossover(Individual parent1, Individual parent2) {
        int[] parentItems1 = parent1.getItems();
        int[] parentItems2 = parent2.getItems();

        int[] childItems = new int[parentItems1.length];
        for(int i = 0; i < parentItems1.length*0.3; i++) {
            childItems[i] = parentItems1[i];
        }
        for (int i = (int)(parentItems1.length*0.3); i < parentItems1.length*0.7; i++) {
            childItems[i] = parentItems2[i];
        }
        for (int i = (int)(parentItems1.length*0.7); i < parentItems1.length; i++) {
            childItems[i] = parentItems1[i];
        }

        return new Individual(childItems);
    }

    public static void localImprovement(List<KnapsackItem> knapsackItems,Individual individual) {
        List<KnapsackItem> actualKnapsackItems = new ArrayList<>(knapsackItems);
        actualKnapsackItems.removeAll(getCurrentItems(knapsackItems, individual));
        KnapsackItem minElement = actualKnapsackItems.stream()
                .min(Comparator.comparingInt(x -> (x.getCost() / x.getWeight())))
                .get();
        int minElementIndex = actualKnapsackItems.indexOf(minElement);
        individual.getItems()[minElementIndex] = 1;
    }

    public static int calculateWeight(List<KnapsackItem> knapsackItems,Individual individual) {
        int weightSum = 0;
        int[] items = individual.getItems();
        for(int i = 0; i < items.length; i++ ) {
            weightSum += knapsackItems.get(i).getWeight()*items[i];
        }
        return weightSum;
    }

    public static int calculateCost(List<KnapsackItem> knapsackItems,Individual individual) {
        int costSum = 0;
        int[] items = individual.getItems();
        for(int i = 0; i < items.length; i++ ) {
            costSum += knapsackItems.get(i).getCost()*items[i];
        }
        return costSum;
    }

    public static Individual getFittest(List<KnapsackItem> knapsackItems, List<Individual> individuals) {
        return individuals.stream()
                .max(Comparator.comparingInt(x -> calculateCost(knapsackItems, x)))
                .get();
    }

    public static Individual getWeakest(List<KnapsackItem> knapsackItems, List<Individual> individuals) {
        return individuals.stream()
                .min(Comparator.comparingInt(x -> calculateCost(knapsackItems, x)))
                .get();
    }


    public static List<KnapsackItem> getCurrentItems(List<KnapsackItem> knapsackItems,Individual individual) {
        List<KnapsackItem> knapsackItemList = new ArrayList<>();
        int[] items = individual.getItems();
        for(int i = 0; i < items.length; i++) {
            if (items[i] == 1) {
                knapsackItemList.add(knapsackItems.get(i));
            }
        }
        return knapsackItemList;
    }
}
