package com.olbius.algorithms.core.bnb;

public interface Element {
	
	Row getRow();
	
	Column getCol();
	
	Object getValue();
	
	void setValue(Object value);
}
