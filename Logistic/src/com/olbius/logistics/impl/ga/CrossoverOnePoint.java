package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.List;

import com.olbius.algorithms.core.Graph;
import com.olbius.alogorithms.core.ga.Gene;
import com.olbius.alogorithms.core.ga.Individual;
import com.olbius.alogorithms.core.ga.operations.Crossover;

public class CrossoverOnePoint implements Crossover {

	private Individual parentOne;
	private Individual parentTwo;
	
	private Individual childOne;
	private Individual childTwo;
	
	private int point;
	
	private Graph graph;
	
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
	public void setParentOne(Individual individual) {
		this.parentOne = individual;
	}

	@Override
	public void setParentTwo(Individual individual) {
		this.parentTwo = individual;
	}

	@Override
	public void crossover() {
		if(isCrossover(parentOne, parentTwo)) {
			int size = ((IndividualImpl)parentOne).getChromosome().getSize();
			List<Gene> genes = new ArrayList<Gene>();
			List<Gene> genes2 = new ArrayList<Gene>();
			for(int i = 0 ; i < point; i++) {
				genes.add(((IndividualImpl)parentOne).getChromosome().getGene(i));
				genes2.add(((IndividualImpl)parentTwo).getChromosome().getGene(i));
			}
			for(int i = point ; i < size; i++) {
				if(!genes.contains(((IndividualImpl)parentTwo).getChromosome().getGene(i))) {
					genes.add(((IndividualImpl)parentTwo).getChromosome().getGene(i));
				}
				if(!genes2.contains(((IndividualImpl)parentOne).getChromosome().getGene(i))) {
					genes2.add(((IndividualImpl)parentOne).getChromosome().getGene(i));
				}
			}
			for(int i = 0 ; i < point; i++) {
				if(!genes.contains(((IndividualImpl)parentTwo).getChromosome().getGene(i))) {
					genes.add(((IndividualImpl)parentTwo).getChromosome().getGene(i));
				}
				if(!genes2.contains(((IndividualImpl)parentOne).getChromosome().getGene(i))) {
					genes2.add(((IndividualImpl)parentOne).getChromosome().getGene(i));
				}
			}
			childOne = new IndividualImpl(genes);
			((IndividualImpl)childOne).setGraph(graph);
			((IndividualImpl)childOne).setMaxW(((IndividualImpl)parentOne).getMaxW());
			((IndividualImpl)childOne).setNode(((IndividualImpl)parentOne).getNode());
			childTwo = new IndividualImpl(genes2);
			((IndividualImpl)childTwo).setGraph(graph);
			((IndividualImpl)childTwo).setMaxW(((IndividualImpl)parentTwo).getMaxW());
			((IndividualImpl)childTwo).setNode(((IndividualImpl)parentTwo).getNode());
		} else {
			childOne = null;
			childTwo = null;
		}
	}

	public static boolean isCrossover(Individual individual, Individual individual2) {
		return ((IndividualImpl)individual).getChromosome().getSize() == ((IndividualImpl)individual2).getChromosome().getSize();
	}
	
	@Override
	public Individual getChildOne() {
		return childOne;
	}

	@Override
	public Individual getChildTwo() {
		return childTwo;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
}
