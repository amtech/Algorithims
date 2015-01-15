package com.olbius.alogorithms.core.ga;

import java.util.List;

public interface Individual {
	
	Object getFitness();
	
	List<Chromosome> getChromosomes();
	
	void calcFitness();
}
