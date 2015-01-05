package com.olbius.logistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.olbius.algorithms.core.Path;

public class SolutionImpl implements Solution {

	private List<Customer> customers;
	
	private List<Path> paths;
	
	private Car car;

	private int value;
	
	public SolutionImpl(Car car) {
		this.car = car;
		customers = new ArrayList<Customer>();
		paths = new ArrayList<Path>();
	}
	
	@Override
	public Path getPathStart() {
		return paths.get(0);
	}

	@Override
	public Path getPathEnd() {
		return paths.get(paths.size()-1);
	}

	@Override
	public boolean contains(Customer customer) {
		for(Path p : paths) {
			if(p.contains(customer.getNode())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public int getSize() {
		return customers.size();
	}

	@Override
	public void insert(Customer customer) {
		for(int i = 0; i < paths.size(); i++) {
			Path p = paths.get(i);
			if(p.contains(customer.getNode())) {
				paths.remove(i);
				Path[] tmp = ((LogisticPath)p).cut(customer.getNode());
				paths.add(i, tmp[0]);
				paths.add(i+1, tmp[1]);
				customers.add(i, customer);
				setWeight();
				return;
			}
		}
	}

	@Override
	public void calcRoute() {
		value = 0;
		int w = (int) car.getWeight();
		for(int i = 0; i < paths.size(); i++) {
			Path p = paths.get(i);
			((LogisticPath)p).setWeight(w);
			((GraphLogistic)p.getGraph()).setM((int)((LogisticPath)p).getWeight());
			value += (int) p.calcRoute();
			if(w > 0) {
				w -= (int) customers.get(i).getRequest().getWeight();
			}
		}
	}

	@Override
	public void add(Customer customer, List<Path> list) {
		customers.add(customer);
		paths.addAll(list);
		setWeight();
	}
	
	public void setWeight() {
		car.setWeight(0);
		for(Customer c : customers) {
			car.setWeight((int)car.getWeight() + (int)c.getRequest().getWeight());
		}
	}
	

	@Override
	public Object increaseValue(List<Path> list, Object weight, int index) {
		int value = 0;
		for(int i = 0; i < paths.size(); i++) {
			if(i != index) {
				((GraphLogistic)paths.get(i).getGraph()).setM((int)((LogisticPath)paths.get(i)).getWeight() + (int)weight);
				value += (int) paths.get(i).calcRoute();
			} else {
				for(Path p : list) {
					((GraphLogistic)p.getGraph()).setM((int)((LogisticPath)p).getWeight());
					value += (int) p.calcRoute();
				}
			}
		}
		return value - this.value;
	}

	@Override
	public Object getWeight(int index) {
		int weight = (int) car.getWeight();
		for(int i = 0; i <= index; i++) {
			weight -= (int) customers.get(i).getRequest().getWeight();
		}
		return weight;
	}

	@Override
	public List<Customer> getCustomers() {
		return this.customers;
	}

	@Override
	public Car getCar() {
		return this.car;
	}

	@Override
	public void add(Customer customer, List<Path> list, int index) {
		customers.add(index, customer);
		
		paths.remove(index);
		
		for(Path p : list) {
			paths.add(index++, p);
		}
		setWeight();
	}

	@Override
	public Object getWeight() {
		return car.getWeight();
	}

	@Override
	public Customer getFirst() {
		return customers.get(0);
	}

	@Override
	public Customer getLast() {
		return customers.get(customers.size()-1);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += customers.toString() + "\n";
		s += paths.toString() + "\n" + "car : " + car.getWeight() + " - value : " + Integer.toString(value);
		return s;
	}
}
