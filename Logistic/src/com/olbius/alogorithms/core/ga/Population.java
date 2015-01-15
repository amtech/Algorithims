package com.olbius.alogorithms.core.ga;

public interface Population {
	
	Individual getIndividual(int index);
	
	boolean contain(Individual individual);
	
	void addIndividual(Individual individual);
	
	void removeIndividual(Individual individual);
	
	void initi(int size);
	
	int size();
}
