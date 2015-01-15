package com.olbius.alogorithms.core.ga;

public interface Operations {

	Individual crossover(Individual individual, Individual individual2);
	
	Individual mutation(Individual individual);
	
	Population selection(Population pop);
	
	void crossover(Population pop);
	
	void mutation(Population pop);
	
	void setCrossProbability(int p);
	
	void setMutionProbability(int p);
	
	void setPopSize(int size);
}
