package com.olbius.algorithms.core.bnb;

import java.util.Date;
import java.util.List;
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
		activeproblems = new TreeSet<OptimizationProblem>();
		activeproblems.add(P);
		U = M;
	}

	public OptimizationProblem solve() {
		OptimizationProblem Ri;
		int Li;

		Date d0 = new Date();
		
		while (!activeproblems.isEmpty()) {
			Ri = selectProblem();
			Li = (int) Ri.getValue();
//			System.out.println(Li);
			if (Li < U) {
				if (Ri.isSolution()) {
					U = Li;
					this.currentbest = Ri;
				} else {
					// optional upper bounding
					Ri.performUpperBounding(U);
					// Branching
					List<OptimizationProblem> subPs = Ri.branch();
					
//					System.out.println(subPs);
					
					for (OptimizationProblem op :subPs) {
						this.activeproblems.add(op);
						this.nodesGenerated++;
					} 
				}
			}
		} 
		
		System.out.println(currentbest.getValue());
		
		System.out.println(((OptimizationProblemImpl)currentbest).getMap());
		
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
