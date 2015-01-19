package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.List;

import com.olbius.algorithms.core.Graph;
import com.olbius.alogorithms.core.ga.Gene;
import com.olbius.alogorithms.core.ga.Individual;
import com.olbius.alogorithms.core.ga.operations.Mutation;

public class MutationSwap implements Mutation{

	private Graph graph;
	
	private Individual individual;
	
	private Individual child;
	
	@Override
	public Object runAlgorithm() {
		return null;
	}

	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	@Override
	public void mutation() {
		int size = ((IndividualImpl)individual).getChromosome().getSize();
		
		OperationsImpl.random.setSeed(System.currentTimeMillis());
		
		int pointOne = (int) (size * OperationsImpl.random.nextDouble());
		
		while(pointOne == size) {
			pointOne = (int) (size * OperationsImpl.random.nextDouble());
		}
		
		int pointTwo = (int) (size * OperationsImpl.random.nextDouble());
		
		while(pointTwo == size) {
			pointTwo = (int) (size * OperationsImpl.random.nextDouble());
		}
		
		List<Gene> genes = new ArrayList<Gene>();
		
		for(int i = 0; i < size; i++) {
			if(i == pointOne) {
				genes.add(((IndividualImpl)individual).getChromosome().getGene(pointTwo));
			} else if(i == pointTwo) {
				genes.add(((IndividualImpl)individual).getChromosome().getGene(pointOne));
			} else {
				genes.add(((IndividualImpl)individual).getChromosome().getGene(i));
			}
		}
		
		child = new IndividualImpl(genes);
		
		((IndividualImpl)child).setGraph(graph);
		((IndividualImpl)child).setMaxW(((IndividualImpl)individual).getMaxW());
		((IndividualImpl)child).setNode(((IndividualImpl)individual).getNode());
	}

	@Override
	public Individual getChild() {
		return child;
	}

}
