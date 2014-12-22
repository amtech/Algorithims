package com.olbius.algorithms.core;

public interface Node extends Comparable<Node>{
	
	Object getInfo();
	
	Object getInfo(int index);
	
	Object getName();
}
