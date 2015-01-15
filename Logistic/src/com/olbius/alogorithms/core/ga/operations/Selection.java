package com.olbius.alogorithms.core.ga.operations;

import com.olbius.algorithms.core.Algorithm;
import com.olbius.alogorithms.core.ga.Population;

public interface Selection extends Algorithm {

	void setPopulation(Population pop);
	
	void selection();
	
	Population getPopulation();
	
	void setSize(int size);
}
