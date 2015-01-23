package com.olbius.alogorithms.core.ga.operations;

import java.util.Set;

public interface Calculate {
	
	Object getCalc(Set<Object> set);
	
	void insertCalc(Set<Object> set, Object value);
}
