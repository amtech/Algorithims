package com.olbius.core;

public interface Node extends Comparable<Node>{
	
	Object getInfo();
	
	Object getInfo(int index);
	
	Object getName();
}
