package com.olbius.algorithms.core.bnb;

public interface OptimizationProblem extends Comparable<OptimizationProblem> {

	OptimizationProblem getRelaxation();

	int getProblemSize();

	int getValue();

	OptimizationProblemSolution getSolution();

	boolean isValid(OptimizationProblemSolution ops);

	OptimizationProblem[] branch();

	void performUpperBounding(int upperbound);

}
