package com.olbius.algorithms.core.bnb;

public class RowImpl implements Row{

	private int index;
	
	public RowImpl(int i) {
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
	public int compareTo(Row o) {
		return this.index - o.getIndex();
	}

	@Override
	public String toString() {
		return Integer.toString(index);
	}
}
