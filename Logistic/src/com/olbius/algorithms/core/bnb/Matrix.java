package com.olbius.algorithms.core.bnb;

import java.util.Set;

public interface Matrix {
	
	void removeRow(Row row);
	
	void removeCol(Column col);
	
	Object getValue(Row row, Column col);
	
	void setValue(Row row, Column col, Object value);
	
	Element getMinRow(Row row);
	
	Element getMinCol(Column col);
	
	void subRow(Row row, Object value);
	
	void subCol(Column col, Object value);
	
	int getSize();

	Set<Element> getElements();
}
