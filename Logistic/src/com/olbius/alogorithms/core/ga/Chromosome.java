package com.olbius.alogorithms.core.ga;

public interface Chromosome {

	Gene getGene(int index);
	
	void addGene(Gene gene);
	
	int getSize();
	
	Object getValue();
}
