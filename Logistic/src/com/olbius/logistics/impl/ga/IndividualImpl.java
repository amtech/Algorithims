package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.List;

import com.olbius.alogorithms.core.ga.Chromosome;
import com.olbius.alogorithms.core.ga.Individual;

public class IndividualImpl implements Individual{

	private Chromosome chromosome;
	
	private int fitness;
	
	public IndividualImpl() {
		this.chromosome = new ChromosomeImpl();
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
		return chromosome.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		IndividualImpl ind = (IndividualImpl) obj;
		return this.chromosome.equals(ind.getChromosome());
	}

	@Override
	public void calcFitness() {
		
	}
}
