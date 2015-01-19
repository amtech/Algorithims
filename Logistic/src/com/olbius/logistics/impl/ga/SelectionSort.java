package com.olbius.logistics.impl.ga;

import com.olbius.algorithms.core.Graph;
import com.olbius.alogorithms.core.ga.Population;
import com.olbius.alogorithms.core.ga.operations.Selection;

public class SelectionSort implements Selection {

	private Graph graph;
	
	private Population pop;
	
	private int maxSize;
	
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
	public void setPopulation(Population pop) {
		this.pop = pop;
	}

	@Override
	public void selection() {
		((PopulationImpl)pop).sort();
		while(pop.size() > maxSize) {
			pop.removeIndividual(pop.getIndividual(pop.size()-1));
		}
	}

	@Override
	public Population getPopulation() {
		return pop;
	}

	@Override
	public void setSize(int size) {
		maxSize = size;
	}

}
