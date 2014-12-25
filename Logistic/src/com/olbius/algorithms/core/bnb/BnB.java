package com.olbius.algorithms.core.bnb;

import java.util.Date;
import java.util.TreeSet;

public class BnB {

	private OptimizationProblem P;

	private int U;

	private OptimizationProblem currentbest = null;

	private TreeSet<OptimizationProblem> activeproblems;

	static int M = Integer.MAX_VALUE;

	private long nodesGenerated = 0;
	
	private double elapsedTime = 0;
	
	public BnB(OptimizationProblem problem) {
		this.P = problem;
		int n = P.getProblemSize();
		activeproblems = new TreeSet<OptimizationProblem>();
		activeproblems.add(P);
		U = M;
	}

	public OptimizationProblem solve() {
		OptimizationProblem Pi;
		OptimizationProblem Ri;
		int Li;

		Date d0 = new Date();
		
		while (!activeproblems.isEmpty()) {
			Pi = selectProblem();
			Ri = Pi.getRelaxation();
			System.out.println(Ri);
			Li = Ri.getValue();
			if (Li < U) {
				if (P.isValid(Ri.getSolution())) {
					U = Li;
					this.currentbest = Ri;
				} else {
					// optional upper bounding
					Ri.performUpperBounding(U);
					// Branching
					OptimizationProblem[] subPs = Ri.branch();
					for (int k = 0; k < subPs.length; k++) {
						this.activeproblems.add(subPs[k]);
						this.nodesGenerated++;
					} 
				}
			}
		} 
		Date d1 = new Date();
		this.elapsedTime = (double) (d1.getTime() - d0.getTime()) / 1000;
		return currentbest;
	} 

	private OptimizationProblem selectProblem() {
		OptimizationProblem selected;
		selected = this.activeproblems.first();;
		this.activeproblems.remove(selected);
		return selected;
	}

	public double getElapsedTime() {
		return this.elapsedTime;
	}

	public long getNodeCount() {
		return this.nodesGenerated;
	}
}
