package com.olbius.logistics.impl.ga;

import java.util.ArrayList;
import java.util.List;

import com.olbius.alogorithms.core.ga.Chromosome;
import com.olbius.alogorithms.core.ga.Gene;

public class ChromosomeImpl implements Chromosome{

	private static List<Gene> genesGobal;
	
	private List<Gene> genes;
	
	public ChromosomeImpl() {
		genes = new ArrayList<Gene>();
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < getGenesGobal().size(); i++) {
			list.add(i);
		}
		
		int r;
		int index;

		while(!list.isEmpty()) {
			r = (int)((list.size()-1) * OperationsImpl.random.nextDouble());
			index = list.get(r);
			list.remove(r);
			this.genes.add(getGenesGobal().get(index));
		}
		
	}
	
	@Override
	public Gene getGene(int index) {
		return genes.get(index);
	}

	@Override
	public void addGene(Gene gene) {
		genes.add(gene);
	}

	@Override
	public String toString() {
		return genes.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		Chromosome c = (Chromosome) obj;
		if(c.getSize() == this.getSize() && !super.equals(obj)) {
			for(int i = 0; i < c.getSize(); i++) {
				if(!c.getGene(i).equals(this.getGene(i))) {
					return false;
				}
			}
			return true;
		}
		return super.equals(obj);
	}

	@Override
	public int getSize() {
		return genes.size();
	}
	
	public static List<Gene> getGenesGobal() {
		if(genesGobal == null) {
			return new ArrayList<Gene>();
		} else {
			return genesGobal;
		}
	}
}
