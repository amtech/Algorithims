package com.olbius.algorithms.core.bnb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class OptimizationProblemImpl implements OptimizationProblem, OptimizationProblemSolution{

	private OptimizationProblem parent;
	private int value;
	private int[] col;
	private int[] row;
	private int[][] matrix;
	private int upperbound;
	private E edge;
	
	public OptimizationProblemImpl(int[][] matrix) {
		this.matrix = matrix;
		this.col = new int[matrix.length];
		this.row = new int[matrix.length];
		for(int i = 0; i < this.matrix.length; i++) {
			
			this.col[i] = i;
			
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < this.matrix.length; j++) {
				if(this.matrix[i][j] < min) {
					min = this.matrix[i][j];
				}
			}
			if(min > 0) {
				value += min;
				for(int j = 0; j < this.matrix.length; j++) {
					this.matrix[i][j] -=min;
				}
			}
		}
		
		for(int i = 0; i < this.matrix.length; i++) {
			
			this.row[i] = i;
			
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < this.matrix.length; j++) {
				if(this.matrix[j][i] < min) {
					min = this.matrix[j][i];
				}
			}
			if(min > 0) {
				value += min;
				for(int j = 0; j < this.matrix.length; j++) {
					this.matrix[j][i] -=min;
				}
			}
		}
	}
	
	public OptimizationProblemImpl(OptimizationProblem parent) {
		this.parent = parent;
	}
	
	@Override
	public int compareTo(OptimizationProblem o) {
		return this.getValue() - o.getValue();
	}

	@Override
	public OptimizationProblem getRelaxation() {
		if(edge == null) {
			return this;
		}
		
		int[][] m = ((OptimizationProblemImpl) parent).getMatrix();
		if(edge.isBan()) {
			matrix = new int[m.length][m.length];
			col = Arrays.copyOf(((OptimizationProblemImpl) parent).getCol(), m.length);
			row = Arrays.copyOf(((OptimizationProblemImpl) parent).getRow(), m.length);
			for(int i = 0; i < m.length; i++) {
				for(int j = 0; j < m.length; j++) {
					matrix[i][j] = m[i][j];
				}
			}
			matrix[edge.getRow()][edge.getCol()] = Integer.MAX_VALUE;
			for(int i = 0; i < m.length; i++) {
				if(matrix[edge.getRow()][i] < Integer.MAX_VALUE) {
					matrix[edge.getRow()][i] -= edge.getMinRow();
				}
				if(matrix[i][edge.getCol()] < Integer.MAX_VALUE) {
					matrix[i][edge.getCol()] -= edge.getMinCol();
				}
			}
			value = parent.getValue() + edge.getQ();
		} else {
			matrix = new int[m.length-1][m.length-1];
			col = new int[m.length-1];
			row = new int[m.length-1];
			int j = 0;
			int k = 0;
			int c = 0, r = 0;
			for(int i = 0; i < m.length; i++) {
				if(edge.getCol() != i) {
					col[j++] = ((OptimizationProblemImpl) parent).getCol()[i];
				} else {
					c = i;
				}
				if(edge.getRow() != i) {
					row[k++] = ((OptimizationProblemImpl) parent).getRow()[i];
				} else {
					r = i;
				}
			}
			k = 0;
			for(int i = 0; i < m.length; i++) {
				if(i == r) {
					k++;
				} else {
					int n = 0;
					for(j = 0; j < m.length; j++) {
						if(j == c) {
							n++;
						} else {
							if(i == c && j == r) {
								matrix[i-k][j-n] = Integer.MAX_VALUE;
							} else {
								matrix[i-k][j-n] = m[i][j];
							}
						}
					}
				}
			}
			
			value = parent.getValue();
		}
		
		return this;
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
		return matrix.length == 0 ? this : null;
	}

	@Override
	public boolean isValid(OptimizationProblemSolution ops) {
		return ops != null;
	}

	@Override
	public OptimizationProblem[] branch() {
		OptimizationProblem[] branch = new OptimizationProblem[2];
		
		branch[0] = new OptimizationProblemImpl(this);
		
		branch[1] = new OptimizationProblemImpl(this);
		
		TreeSet<E> set = new TreeSet<OptimizationProblemImpl.E>();
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] == 0) {
					E e = new E(i, j);
					e.calcQ(matrix);
					set.add(e);
				}
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
		
		((OptimizationProblemImpl) branch[0]).setParent(this);
		((OptimizationProblemImpl) branch[1]).setParent(this);
		
		return branch;
	}

	@Override
	public void performUpperBounding(int upperbound) {
		this.upperbound = upperbound;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public int[] getCol() {
		return col;
	}
	
	public int[] getRow() {
		return row;
	}
	
	public void setE(E e) {
		this.edge = e;
	}
	
	public void setParent(OptimizationProblem parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < this.matrix.length; i++) {
			s += Arrays.toString(this.matrix[i])+"\n";
		}
		return s;
	}
	
	public class E implements Comparable<E>{
		
		private int row;
		private int col;
		private int min_c;
		private int min_r;
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
			min_c = Integer.MAX_VALUE;
			min_r = Integer.MAX_VALUE;
			for(int i = 0; i < matrix.length; i++) {
				if(matrix[this.row][i] < min_r && i != this.col) {
					min_r = matrix[this.row][i];
				}
				if(matrix[i][this.col] < min_c && i != this.row) {
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
		
		public int getCol() {
			return col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getMinCol() {
			return min_c;
		}
		
		public int getMinRow() {
			return min_r;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			E e = new E(row, col);
			e.setQ(q);
			return e;
		}
	} 
	
	public class M {
		
		private Map<Integer, Map<Integer, Integer>> matrix;
		
		public M() {
			matrix = new HashMap<Integer, Map<Integer,Integer>>();
		}
		
		public void setValue(int row, int col, int value) {
			if(matrix.get(row) == null) {
				matrix.put(row, new HashMap<Integer, Integer>());
			}
			matrix.get(row).put(col, value);
		}
		
		public int getValue(int row, int col) {
			if(matrix.get(row) != null) {
				return matrix.get(row).get(col);
			}
			return -1;
		}
		
		public M removeRow(int row) {
			matrix.remove(row);
			return this;
		}
		
		public M removeCol(int col) {
			for(int i : matrix.keySet()) {
				matrix.get(i).remove(col);
			}
			return this;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			M m = new M();
			for(int i : matrix.keySet()) {
				for(int j : matrix.get(i).keySet()) {
					m.setValue(i, j, matrix.get(i).get(j));
				}
			}
			return m;
		}
		
		@Override
		public String toString() {
			String s = "";
			for(int i : matrix.keySet()) {
				for(int j : matrix.get(i).keySet()) {
					s += matrix.get(i).get(j) + " ";
				}
				s += "\n";
			}
			return s;
		}
	}
	
	public String test() {
		String s = "";
		s += edge.getRow() + " " + edge.getCol() + " " + edge.getQ();
		return s;
	}
	
}
