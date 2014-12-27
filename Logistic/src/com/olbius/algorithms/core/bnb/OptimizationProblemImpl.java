package com.olbius.algorithms.core.bnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class OptimizationProblemImpl implements OptimizationProblem{

	private OptimizationProblem parent;
	private Matrix matrix;
	private Element element;
	private int value;
	private HashMap<Integer, Integer> map;
	private boolean solution;
	private int upperbound;
	
	public OptimizationProblemImpl(OptimizationProblem parent, Element e) {
		this.parent = parent;
		
		this.map = new HashMap<Integer, Integer>();
		
		for(int i : ((OptimizationProblemImpl)parent).getMap().keySet()) {
			this.map.put(i, ((OptimizationProblemImpl)parent).getMap().get(i));
		}
		
		if(e!=null) {
			this.map.put(e.getRow().getIndex(), e.getCol().getIndex());
		}
		
		this.value = (int) parent.getValue();
	}
	
	public OptimizationProblemImpl(int[][] matrix) {
		this.matrix = new MatrixImpl(matrix);
		
		this.map = new HashMap<Integer, Integer>();
		
		this.upperbound = Integer.MAX_VALUE;
		
		relaxation();
	}
	
	public HashMap<Integer, Integer> getMap() {
		return map;
	}
	
	@Override
	public int compareTo(OptimizationProblem o) {
		return this.value - (int) o.getValue();
	}

	@Override
	public int getProblemSize() {
		return matrix.getSize();
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public boolean isSolution() {
		return solution;
	}

	@Override
	public List<OptimizationProblem> branch() {
		
		List<OptimizationProblem> list = new ArrayList<OptimizationProblem>();
		
		if(solution) {
			return list;
		}
		
//		relaxation();
		
		MatrixImpl m = (MatrixImpl) matrix;
		
		List<Element> elements = m.getElementZero();
		
		int _q = -1;
		
		Element _e = null;
		
		for(Element e : elements) {
			Element _min_r = m.getMinRowZero(e);
			Element _min_c = m.getMinColZero(e);
			int tmp  = (int) _min_r.getValue() + (int) _min_c.getValue();
			if (tmp > _q) {
				_q = tmp;
				_e = e;
			}
		}
		
		element = _e;
		
		OptimizationProblemImpl op = new OptimizationProblemImpl(this, element);
		
		MatrixImpl mt = null;
		try {
			mt = (MatrixImpl) m.clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		
		mt.removeRow(element.getRow());
		
		mt.removeCol(element.getCol());
		
		op.setMatrix(mt);
		
		op.relaxation();
		
		if((int)op.getValue() < upperbound) {
			list.add(op);
		}
		
		
		op = new OptimizationProblemImpl(this, null);
		
		mt = null;
		
		try {
			mt = (MatrixImpl) m.clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		
		mt.setValue(element.getRow(), element.getCol(), Integer.MAX_VALUE);
		
		op.setMatrix(mt);
		
		op.relaxation();
		
		if((int)op.getValue() < upperbound) {
			list.add(op);
		}
		
		return list;
	}

	public void inf() {
		for(int i : map.keySet()) {
			Column col = ((MatrixImpl) matrix).getColumns().get(i);
			int j = i;
			while(map.get(j) != null) {
				j = map.get(j);
				Row row = ((MatrixImpl) matrix).getRows().get(j);
				matrix.setValue(row, col, Integer.MAX_VALUE);
			}
		}
	}
	
	@Override
	public void relaxation() {
		inf();
		value += ((MatrixImpl)matrix).getRelaxation();
		if(getProblemSize() == 2) {
			Set<Element> set = matrix.getElements();
			for(Element e : set) {
				map.put(e.getRow().getIndex(), e.getCol().getIndex());
			}
			solution = true;
			return;
		}
	}

	public void setParent(OptimizationProblem parent) {
		this.parent = parent;
	}
	
	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
	
	@Override
	public String toString() {
//		return Integer.toString(value);
		return matrix.toString() + "\n";
	}

	@Override
	public void performUpperBounding(Object upperbound) {
		this.upperbound = (int) upperbound;
	}
}
