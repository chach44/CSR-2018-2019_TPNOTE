package org.inria.restlet.mta.internals;

public interface Billetterie {

	/**
	 * Acheter des billets
	 * True si le nombre de billets demandé est inférieur ou égal 
	 * à ce qu'il reste dans le système
	 * @param nb
	 * @throws InterruptedException 
	 */
	public boolean acheterBillet(int nb) throws InterruptedException;
	
	/**
	 * Ajouter des billets dans le système lorsqu'il viendrait à en manquer
	 * @param nb
	 * @throws InterruptedException 
	 */
	public void addNewBillet(int nb) throws InterruptedException;
}
