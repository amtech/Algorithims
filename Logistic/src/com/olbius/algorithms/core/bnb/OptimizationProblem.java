package com.olbius.algorithms.core.bnb;

import java.util.List;

public interface OptimizationProblem extends Comparable<OptimizationProblem>{
	
	void relaxation();
	
	int getProblemSize();

	Object getValue();

	boolean isSolution();

	List<OptimizationProblem> branch();

	void performUpperBounding(Object upperbound);
}
