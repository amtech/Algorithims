package com.olbius.alogorithms.core.ga.operations;

import com.olbius.algorithms.core.Algorithm;
import com.olbius.alogorithms.core.ga.Individual;

public interface Crossover extends Algorithm{
	
	void setParentOne(Individual individual);
	
	void setParentTwo(Individual individual);
	
	void crossover();
	
	Individual getChildOne();
	
	Individual getChildTwo();
}
