package com.olbius.algorithms.core.bnb;

public class ElementImpl implements Element{

	private Row row;
	private Column col;
	private int value;
	
	public ElementImpl(Row row, Column col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public Row getRow() {
		return this.row;
	}

	@Override
	public Column getCol() {
		return this.col;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public void setValue(Object value) {
		this.value = (int) value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
