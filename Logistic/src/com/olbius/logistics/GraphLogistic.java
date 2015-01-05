package com.olbius.logistics;

import com.olbius.algorithms.core.GraphImpl;
import com.olbius.algorithms.core.Node;

public class GraphLogistic extends GraphImpl {
	
	private int m;
	
	public void setM(int m) {
		this.m = m;
	}
	
	public int getM() {
		return m;
	}
	
	@Override
	public Object getWeight(Node start, Node target) {
		int w = (int) super.getWeight(start, target);
		if(m > 0) {
			w += ((EdgeLogistic)getEdge(start, target)).getCost()*m;
		}
		return w;
	}
}
