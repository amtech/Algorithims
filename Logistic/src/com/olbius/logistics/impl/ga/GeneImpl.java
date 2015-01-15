package com.olbius.logistics.impl.ga;

import com.olbius.alogorithms.core.ga.Gene;
import com.olbius.logistics.Customer;

public class GeneImpl implements Gene{

	private Customer customer;
	
	public GeneImpl(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public Object getInfo() {
		return customer;
	}

	@Override
	public String toString() {
		return customer.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		Gene gene = (Gene) obj;
		return this.getInfo().equals(gene.getInfo());
	}
}
