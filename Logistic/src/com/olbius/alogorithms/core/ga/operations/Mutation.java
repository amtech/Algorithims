package com.olbius.alogorithms.core.ga.operations;

import com.olbius.algorithms.core.Algorithm;
import com.olbius.alogorithms.core.ga.Individual;

public interface Mutation extends Algorithm{

	void setIndividual(Individual individual);
	
	void mutation();
	
	Individual getChild();
}
