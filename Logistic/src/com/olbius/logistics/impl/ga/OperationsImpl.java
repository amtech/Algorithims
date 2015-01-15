package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.olbius.alogorithms.core.ga.Individual;
import com.olbius.alogorithms.core.ga.Operations;
import com.olbius.alogorithms.core.ga.Population;
import com.olbius.alogorithms.core.ga.operations.Crossover;
import com.olbius.alogorithms.core.ga.operations.Mutation;
import com.olbius.alogorithms.core.ga.operations.Selection;

public class OperationsImpl implements Operations{

	private Crossover cross;
	private Mutation mutation;
	private Selection selection;
	
	private int crossProb;
	private int mutationProb;
	private int popSize;
	
	public static Random random = new Random();
	
	public OperationsImpl() {
	}
	
	public OperationsImpl(Crossover crossover) {
		this.cross = crossover;
	}
	
	public OperationsImpl(Crossover crossover, Mutation mutation) {
		this.cross = crossover;
		this.mutation = mutation;
	}
	
	public OperationsImpl(Crossover crossover, Mutation mutation, Selection selection) {
		this.cross = crossover;
		this.mutation = mutation;
		this.selection = selection;
	}
	
	@Override
	public Individual crossover(Individual individual, Individual individual2) {
		cross.setParentOne(individual);
		cross.setParentTwo(individual2);
		cross.crossover();
		return cross.getChildOne();
	}

	@Override
	public Individual mutation(Individual individual) {
		mutation.setIndividual(individual);
		return mutation.getChild();
	}

	@Override
	public Population selection(Population pop) {
		selection.setPopulation(pop);
		selection.setSize(popSize);
		return selection.getPopulation();
	}
	
	public void setCross(Crossover cross) {
		this.cross = cross;
	}
	
	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}
	
	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	@Override
	public void crossover(Population pop) {
		List<Integer> list = new ArrayList<Integer>();
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < pop.size(); i++) {
			list.add(i);
		}
		random.setSeed(System.currentTimeMillis());
		
		int i = 0;
		
		int rate = pop.size()*crossProb/100;
		
		while(i < rate) {
			int one,two;
			one = two = -1;
			while(one == two) {
				one = (int)((list.size()-1)*random.nextDouble());
				two = (int)((list.size()-1)*random.nextDouble());
				map.put(list.get(one), list.get(two));
			}
			if(one > two) {
				list.remove(one);
				list.remove(two);
			}
			if(one < two) {
				list.remove(two);
				list.remove(one);
			}
			i+=2;
		}
		for(int x : map.keySet()) {
			crossover(pop.getIndividual(x), pop.getIndividual(map.get(x)));
		}
	}

	@Override
	public void mutation(Population pop) {
		List<Integer> list = new ArrayList<Integer>();
		
		List<Integer> list2 = new ArrayList<Integer>();
		
		for(int i = 0; i < pop.size(); i++) {
			list.add(i);
		}
		random.setSeed(System.currentTimeMillis());
		
		int i = 0;
		
		int rate = pop.size()*mutationProb/100;
		
		while(i < rate) {
			int m = (int)((list.size()-1)*random.nextDouble());
			list2.add(list.get(m));
			list.remove(m);
			i++;
		}
		for(int x : list2) {
			mutation(pop.getIndividual(x));
		}
	}

	@Override
	public void setCrossProbability(int p) {
		this.crossProb = p;
	}

	@Override
	public void setMutionProbability(int p) {
		this.mutationProb = p;
	}

	@Override
	public void setPopSize(int size) {
		this.popSize = size;
	}
	
}
