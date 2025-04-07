package com.backend;
/**
 * Gene.java
 * Represents a single gene in a chromosome (basic unit of data).
 */
public class Gene {
    private double value; // Float gene: value between 0.0 and 1.0

    public Gene(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void mutate() {
        // Slightly perturb the gene value within [0.0, 1.0]
        double delta = (Math.random() - 0.5) * 0.2; // +/- 0.1
        value += delta;
        value = Math.max(0.0, Math.min(1.0, value)); // clamp to [0, 1]
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }
}
