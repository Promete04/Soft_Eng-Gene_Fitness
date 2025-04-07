
/**
 * Population.java
 * Holds and evolves a population of chromosomes.
 */
import java.util.*;

public class Population {
    private List<Chromosome> individuals;
    private int chromosomeLength;

    public Population(int size, int chromosomeLength) {
        if (size <= 0 || chromosomeLength <= 0) {
            throw new IllegalArgumentException("Population size and chromosome length must be positive.");
        }
        this.chromosomeLength = chromosomeLength;
        individuals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            individuals.add(new Chromosome(chromosomeLength));
        }
    }

    public List<Chromosome> getIndividuals() {
        return individuals;
    }

    public Chromosome getFittest() {
        if (individuals.isEmpty()) {
            throw new IllegalStateException("Population is empty. Cannot determine fittest chromosome.");
        }
        return Collections.max(individuals, Comparator.comparingDouble(Chromosome::getFitness));
    }

    public void evolve(double mutationRate) {
        if (individuals.isEmpty()) {
            throw new IllegalStateException("Population is empty. Cannot evolve.");
        }
        List<Chromosome> newGeneration = new ArrayList<>();

        Chromosome elite = getFittest();
        newGeneration.add(elite);

        while (newGeneration.size() < individuals.size()) {
            Chromosome parent1 = selectParent();
            Chromosome parent2 = selectParent();
            Chromosome child = parent1.crossover(parent2);
            child.mutate(mutationRate);
            child.evaluateFitness();
            newGeneration.add(child);
        }

        this.individuals = newGeneration;
    }

    private Chromosome selectParent() {
        if (individuals.size() < 2) {
            throw new IllegalStateException("Need at least two individuals for selection.");
        }
        Chromosome c1 = individuals.get((int)(Math.random() * individuals.size()));
        Chromosome c2 = individuals.get((int)(Math.random() * individuals.size()));
        return (c1.getFitness() > c2.getFitness()) ? c1 : c2;
    }
}
