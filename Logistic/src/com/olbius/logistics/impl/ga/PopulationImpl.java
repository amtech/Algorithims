package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.List;

import com.olbius.alogorithms.core.ga.Individual;
import com.olbius.alogorithms.core.ga.Population;

public class PopulationImpl implements Population{

	private List<Individual> individuals;
	
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
				individual.calcFitness();
			}
			
		}
		
	}

	@Override
	public int size() {
		return individuals.size();
	}

}
