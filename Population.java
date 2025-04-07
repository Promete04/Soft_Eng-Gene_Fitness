
/**
 * Population.java
 * Holds and evolves a population of chromosomes.
 */
import java.util.*;

public class Population {
    private List<Chromosome> individuals;
    private int chromosomeLength;

    public Population(int size, int chromosomeLength) {
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
        return Collections.max(individuals, Comparator.comparingDouble(Chromosome::getFitness));
    }

    public void evolve(double mutationRate) {
        List<Chromosome> newGeneration = new ArrayList<>();

        // Elitism: keep the best
        Chromosome elite = getFittest();
        newGeneration.add(elite);

        // Create rest of the new generation
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
        // Tournament selection
        Chromosome c1 = individuals.get((int)(Math.random() * individuals.size()));
        Chromosome c2 = individuals.get((int)(Math.random() * individuals.size()));
        return (c1.getFitness() > c2.getFitness()) ? c1 : c2;
    }
}
