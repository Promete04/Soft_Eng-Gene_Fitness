
/**
 * Chromosome.java
 * Represents a candidate solution (individual in the population).
 */
import java.util.*;


public class Chromosome {
    private List<Gene> genes;
    private double fitness;

    public Chromosome(int length) {
        genes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            genes.add(new Gene(Math.random())); // Random float 0.0 to 1.0
        }
        evaluateFitness();
    }

    public Chromosome(List<Gene> genes) {
        this.genes = new ArrayList<>(genes);
        evaluateFitness();
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public double getFitness() {
        return fitness;
    }

    public void evaluateFitness() {
        // Example fitness: sum of gene values
        fitness = genes.stream().mapToDouble(Gene::getValue).sum();
    }

    public Chromosome crossover(Chromosome partner) {
        // Single-point crossover
        int point = (int)(Math.random() * genes.size());
        List<Gene> childGenes = new ArrayList<>();

        for (int i = 0; i < point; i++) {
            childGenes.add(new Gene(this.genes.get(i).getValue()));
        }
        for (int i = point; i < genes.size(); i++) {
            childGenes.add(new Gene(partner.genes.get(i).getValue()));
        }

        return new Chromosome(childGenes);
    }

    public void mutate(double mutationRate) {
        for (Gene gene : genes) {
            if (Math.random() < mutationRate) {
                gene.mutate();
            }
        }
    }

    @Override
    public String toString() {
        return genes.toString() + " | Fitness: " + String.format("%.2f", fitness);
    }
}
