package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.List;

import com.olbius.algorithms.core.Graph;
import com.olbius.algorithms.core.Node;
import com.olbius.alogorithms.core.ga.Chromosome;
import com.olbius.alogorithms.core.ga.Gene;
import com.olbius.alogorithms.core.ga.Individual;

public class IndividualImpl implements Individual{

	private Chromosome chromosome;
	
	private int fitness;
	
	public IndividualImpl() {
		this.chromosome = new ChromosomeImpl();
	}
	
	public IndividualImpl(List<Gene> genes) {
		this.chromosome = new ChromosomeImpl(genes);
	}
	
	@Override
	public Object getFitness() {
		return fitness;
	}

	@Override
	public List<Chromosome> getChromosomes() {
		List<Chromosome> chromosomes = new ArrayList<Chromosome>();
		chromosomes.add(chromosome);
		return chromosomes;
	}
	
	public Chromosome getChromosome() {
		return chromosome;
	}
	
	@Override
	public String toString() {
		return chromosome.toString() + " -- fitness : " + fitness;
	}
	
	@Override
	public boolean equals(Object obj) {
		IndividualImpl ind = (IndividualImpl) obj;
		return this.chromosome.equals(ind.getChromosome());
	}

	@Override
	public void calcFitness() {
		((ChromosomeImpl)chromosome).calcValue();
		fitness = (int) chromosome.getValue();
	}
	
	public void setMaxW(int maxW) {
		((ChromosomeImpl)chromosome).setMaxW(maxW);
	}
	
	public int getMaxW() {
		return ((ChromosomeImpl)chromosome).getMaxW();
	}
	
	public void setGraph(Graph graph) {
	 ((ChromosomeImpl)chromosome).setGraph(graph);
	}
	
	public void setNode(Node node) {
		((ChromosomeImpl)chromosome).setNode(node);
	}

	@Override
	public int compareTo(Individual o) {
		return this.fitness - (int)o.getFitness();
	}
	
	public Node getNode() {
		return ((ChromosomeImpl) chromosome).getNode();
	}
}
