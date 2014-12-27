package com.olbius.algorithms.core.bnb;

public class ColumnImpl implements Column{
	
	private int index;
	
	public ColumnImpl(int i) {
		this.index = i;
	}
	
	@Override
	public void setIndex(int i) {
		this.index = i;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int compareTo(Column o) {
		return this.index - o.getIndex();
	}
	
	@Override
	public String toString() {
		return Integer.toString(index);
	}
}
