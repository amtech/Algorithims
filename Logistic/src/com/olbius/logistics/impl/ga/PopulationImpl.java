package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.olbius.algorithms.core.Graph;
import com.olbius.algorithms.core.Node;
import com.olbius.alogorithms.core.ga.Individual;
import com.olbius.alogorithms.core.ga.Population;
import com.olbius.alogorithms.core.ga.operations.Calculate;

public class PopulationImpl implements Population{

	private List<Individual> individuals;
	
	private Graph graph;
	
	private Node node;
	
	private int maxW;
	
	private Calculate calculate;
	
	public PopulationImpl() {
		individuals = new ArrayList<Individual>();
	}
	
	@Override
	public Individual getIndividual(int index) {
		return individuals.get(index);
	}

	@Override
	public boolean contain(Individual individual) {
		for(Individual i : individuals) {
			if(i.equals(individual)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addIndividual(Individual individual) {
		individuals.add(individual);
	}

	@Override
	public void removeIndividual(Individual individual) {
		individuals.remove(individual);
	}

	@Override
	public void initi(int size) {
		
		OperationsImpl.random.setSeed(System.currentTimeMillis());
		
		Individual individual;
		
		while(individuals.size() < size) {
			
			individual = new IndividualImpl();
			
			if(!contain(individual)) {
				addIndividual(individual);
				((IndividualImpl)individual).setGraph(graph);
				((IndividualImpl)individual).setMaxW(maxW);
				((IndividualImpl)individual).setNode(node);
				((IndividualImpl)individual).setCalculate(calculate);
				individual.calcFitness();
			}
		}
		
	}

	@Override
	public int size() {
		return individuals.size();
	}

	@Override
	public String toString() {
		String s = "";
		for(Individual ind : individuals) {
			s += ind.toString() + "\n";
		}
		return s;
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	public void sort() {
		Collections.sort(individuals);
	}
	
	public void setMaxW(int maxW) {
		this.maxW = maxW;
	}
	
	public void setCalculate(Calculate calculate) {
		this.calculate = calculate;
	}
}
