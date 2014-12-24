package com.olbius.algorithms.core.bnb;

import java.util.TreeSet;

public class OptimizationProblemImpl implements OptimizationProblem, OptimizationProblemSolution{

	private OptimizationProblem parent;
	private int value;
	private int[][] matrix;
	private int upperbound;
	private E edge;
	
	public OptimizationProblemImpl(OptimizationProblem parent) {
		this.parent = parent;
	}
	
	@Override
	public int compareTo(OptimizationProblem o) {
		return this.getValue() - o.getValue();
	}

	@Override
	public OptimizationProblem getRelaxation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProblemSize() {
		return matrix.length;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public OptimizationProblemSolution getSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid(OptimizationProblemSolution ops) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OptimizationProblem[] branch() {
		OptimizationProblem[] branch = new OptimizationProblem[2];
		
		branch[0] = new OptimizationProblemImpl(this);
		
		branch[1] = new OptimizationProblemImpl(this);
		
		TreeSet<E> set = new TreeSet<OptimizationProblemImpl.E>();
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				E e = new E(i, j);
				e.calcQ(matrix);
				set.add(e);
			}
		}
		
		E e = set.last();
		
		((OptimizationProblemImpl) branch[0]).setE(e);
		
		try {
			e = (E) e.clone();
		} catch (CloneNotSupportedException e1) {
			e = null;
			e1.printStackTrace();
		}
		
		e.setBan(true);
		
		((OptimizationProblemImpl) branch[1]).setE(e);
		
		return branch;
	}

	@Override
	public void performUpperBounding(int upperbound) {
		this.upperbound = upperbound;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public void setE(E e) {
		this.edge = e;
	}
	
	public class E implements Comparable<E>{
		
		private int row;
		private int col;
		private int q;
		private boolean ban;
		
		public E(int row, int col) {
			this.col = col;
			this.row = row;
		}
		
		@Override
		public int compareTo(E o) {
			return this.q - o.getQ();
		}
		
		public int getQ() {
			return this.q;
		}
		
		public void calcQ(int[][] matrix) {
			int min_c = Integer.MAX_VALUE;
			int min_r = Integer.MAX_VALUE;
			for(int i = 0; i < matrix.length; i++) {
				if(matrix[this.row][i] < min_r) {
					min_r = matrix[this.row][i];
				}
				if(matrix[i][this.col] < min_c) {
					min_c = matrix[i][this.col];
				}
			}
			q = min_c + min_r;
		}
		
		public boolean isBan() {
			return ban;
		}
		
		public void setBan(boolean b) {
			this.ban = b;
		}
		
		public void setQ(int q) {
			this.q = q;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			E e = new E(row, col);
			e.setQ(q);
			return e;
		}
	} 
	
}
