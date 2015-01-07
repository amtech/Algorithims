package com.olbius.logistics;

import java.util.List;

import com.olbius.algorithms.core.Node;
import com.olbius.algorithms.core.Path;

public interface Solution {
	
	Path getPathStart();
	
	Path getPathEnd();
	
	boolean contains(Customer customer);
	
	Object getValue();
	
	int getSize();
	
	void insert(Customer customer);
	
	void calcRoute();
	
	void add(Customer customer, List<Path> list);
	
	void add(Customer customer, List<Path> list, int index);
	
	Object increaseValue(List<Path> list, Object weight, int index);
	
	Object getWeight(int index);
	
	Object getWeight();
	
	List<Customer> getCustomers();
	
	Car getCar();
	
	Customer getFirst();
	
	Customer getLast();
	
	Object getPaths();
}
