package com.olbius.algorithms.core.bnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MatrixImpl implements Matrix{

	private List<Row> rows;
	
	private List<Column> columns;
	
	private Map<Row, Map<Column, Element>> data;
	
	public MatrixImpl() {
		data = new HashMap<Row, Map<Column,Element>>();
	}
	
	public MatrixImpl(int[][] matrix) {
		data = new HashMap<Row, Map<Column,Element>>();
		rows = new ArrayList<Row>();
		columns = new ArrayList<Column>();
		for(int i = 0 ; i < matrix.length; i++) {
			Row row = new RowImpl(i);
			rows.add(row);
			for(int j = 0; j < matrix.length; j++) {
				Column col = null;
				if(j < columns.size()) {
					col = columns.get(j);
				} else {
					col = new ColumnImpl(j);
					columns.add(col);
				}
				initValue(row, col, matrix[i][j]);
			}
		}
	}
	
	@Override
	public void removeRow(Row row) {
		data.remove(row);
	}

	@Override
	public void removeCol(Column col) {
		for(Row row: data.keySet()) {
			data.get(row).remove(col);
		}
	}

	@Override
	public Object getValue(Row row, Column col) {
		if(data.get(row) == null) {
			return null;
		}
		if(data.get(row).get(col)==null) {
			return null;
		}
		return data.get(row).get(col).getValue();
	}

	@Override
	public void setValue(Row row, Column col, Object value) {
		if(data.get(row)!=null) {
			if(data.get(row).get(col)!=null) {
				data.get(row).get(col).setValue(value);
			}
		}
	}
	
	public void initValue(Row row, Column col, Object value) {
		if(value != null) {
			if(data.get(row)==null) {
				data.put(row, new HashMap<Column, Element>());
			}
			if(data.get(row).get(col)==null) {
				data.get(row).put(col, new ElementImpl(row, col));
			}
			data.get(row).get(col).setValue(value);
		}
	}

	@Override
	public Element getMinRow(Row row) {
		
		if(data.get(row) == null) {
			return null;
		}
		
		Set<Column> columns = data.get(row).keySet();
		
		int min = Integer.MAX_VALUE;
		Element element = null;
		
		for(Column col : columns) {
			if(min > (int) data.get(row).get(col).getValue()) {
				element = data.get(row).get(col);
				min = (int) element.getValue();
			}
		}
		
		return element;
	}

	@Override
	public Element getMinCol(Column col) {
		Set<Row> rows = data.keySet();
		
		int min = Integer.MAX_VALUE;
		Element element = null;
		
		for(Row row : rows) {
			if(data.get(row).get(col) != null) {
				if(min > (int) data.get(row).get(col).getValue()) {
					element = data.get(row).get(col);
					min = (int) element.getValue();
				}
			}
		}
		
		return element;
	}

	@Override
	public void subRow(Row row, Object value) {
		
		if(data.get(row) == null) {
			return;
		}
		
		for(Column col : data.get(row).keySet()) {
			int i = (int) data.get(row).get(col).getValue();
			if(i < Integer.MAX_VALUE) {
				i = i - (int)value;
				data.get(row).get(col).setValue(i);
			}
		}
		
	}

	@Override
	public void subCol(Column col, Object value) {
		
		for(Row row : data.keySet()) {
			if(data.get(row).get(col)!=null) {
				int i = (int) data.get(row).get(col).getValue();
				if(i < Integer.MAX_VALUE) {
					i = i - (int)value;
					data.get(row).get(col).setValue(i);
				}
			}
		}
		
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public List<Row> getRows() {
		return rows;
	}
	
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Matrix matrix = new MatrixImpl();
		
		((MatrixImpl)matrix).setRows(this.rows);
		
		((MatrixImpl)matrix).setColumns(this.columns);
		
		for(Row row : this.rows) {
			for(Column col : this.columns) {
				((MatrixImpl)matrix).initValue(row, col, this.getValue(row, col));
			}
		}
		
		return matrix;
	}
	
	public int getRelaxation() {
		int value = 0;
		
		Element element = null;
		
		for(Row row : rows) {
			element = getMinRow(row);
			if(element != null) {
				value += (int)element.getValue();
				subRow(row, element.getValue());
			}
		}
		
		for(Column col : columns) {
			element = getMinCol(col);
			if(element != null) {
				value += (int)element.getValue();
				subCol(col, element.getValue());
			}
		}
		
		return value;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Row row : rows) {
			for(Column col : columns) {
				Object value = getValue(row, col);
				if(value != null) {
					if((int)value == Integer.MAX_VALUE) {
						s+= "\u221e\t";
					} else {
						s += value + "\t";
					}
				}
			}
			s += "\n";
		}
		return s;
	}

	@Override
	public int getSize() {
		return data.size();
	}
	
	public List<Element> getElementZero() {
		
		List<Element> elements = new ArrayList<Element>();
		
		for(Row row: data.keySet()) {
			for(Column col : data.get(row).keySet()) {
				if((int)data.get(row).get(col).getValue() == 0) {
					elements.add(data.get(row).get(col));
				}
			}
		}
		
		return elements;
	}
	
	public Element getMinRowZero(Element e) {
		
		if(data.get(e.getRow()) == null) {
			return null;
		}
		
		Set<Column> columns = data.get(e.getRow()).keySet();
		
		int min = Integer.MAX_VALUE;
		Element element = null;
		
		for(Column col : columns) {
			int tmp = (int) data.get(e.getRow()).get(col).getValue();
			if(min > tmp && !col.equals(e.getCol())) {
				element = data.get(e.getRow()).get(col);
				min = tmp;
			}
		}
		
		return element;
	}
	
	public Element getMinColZero(Element e) {
		Set<Row> rows = data.keySet();
		
		int min = Integer.MAX_VALUE;
		Element element = null;
		
		for(Row row : rows) {
			if(data.get(row).get(e.getCol()) != null) {
				int tmp = (int) data.get(row).get(e.getCol()).getValue();
				if(min > tmp && !row.equals(e.getRow())) {
					element = data.get(row).get(e.getCol());
					min = tmp;
				}
			}
		}
		
		return element;
	}

	@Override
	public Set<Element> getElements() {
		HashSet<Element> set = new HashSet<Element>();
		for(Row row : data.keySet()) {
			for(Column col : data.get(row).keySet()) {
				if((int)data.get(row).get(col).getValue() == 0) {
					set.add(data.get(row).get(col));
				}
			}
		}
		return set;
	}
}
