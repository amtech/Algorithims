package com.olbius.logistics.impl.ga;

import com.olbius.algorithms.core.Algorithm;
import com.olbius.algorithms.core.Graph;
import com.olbius.alogorithms.core.ga.Operations;
import com.olbius.alogorithms.core.ga.Population;

public class AlgorithmGA implements Algorithm {

	private Graph graph;
	
	private Operations operation;
	
	private Population population;
	
	private int gen;
	
	private int popSize;
	
	private int crossProb;
	
	private int mutationProb;
	
	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public Object runAlgorithm() {
		
		operation.setCrossProbability(crossProb);
		operation.setMutionProbability(mutationProb);
		operation.setPopSize(popSize);
		
		population.initi(popSize);
		
		for(int i = 0; i < gen; i++) {
			generation();
		}
		
		return null;
	}

	public void generation() {
		
		operation.crossover(population);
		
		operation.mutation(population);
		
		operation.selection(population);
		
	}
	
	public void setCrossProb(int crossProb) {
		this.crossProb = crossProb;
	}
	
	public void setGen(int gen) {
		this.gen = gen;
	}
	
	public void setMutationProb(int mutationProb) {
		this.mutationProb = mutationProb;
	}
	
	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}
	
	public void setOperation(Operations operation) {
		this.operation = operation;
	}
	
	public void setPopulation(Population population) {
		this.population = population;
	}
}
