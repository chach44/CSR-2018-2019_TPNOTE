package org.inria.restlet.mta.internals;


public class BilletterieImpl implements Billetterie {

	private int nbSystem = 10;
	/**
	 * Acheter des billets
	 * True si le nombre de billets demandé est inférieur ou égal 
	 * à ce qu'il reste dans le système
	 * @param nb
	 * @throws InterruptedException 
	 */
	public synchronized boolean acheterBillet(int nb) throws InterruptedException {
		while(nb > nbSystem) {
			notify();
			wait();
		}
		System.out .println("Le client prend " +nb + " billets, direction -> Train Fantome");
		nbSystem -= nb;
		return true;
	}

	/**
	 * Ajouter des billets dans le système lorsqu'il viendrait à en manquer
	 * @param nb
	 * @throws InterruptedException 
	 */
	public synchronized void addNewBillet(int nb) throws InterruptedException {
		wait(); // Faire dormir notre Responsable Billeterie pour les premiers clients car pas necessaire
		nbSystem += nb;
		notifyAll();
		System.out.println("Il y a maintenant "+nbSystem+" billets dans notre systeme");
	}

}
