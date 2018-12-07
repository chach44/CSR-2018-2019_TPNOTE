package org.inria.restlet.mta.internals;

public class Navette extends Thread {

	static final int placeMax = 4;

	private Attraction attraction;
	private int numNavette;
	private int nbClientsNavette = 0;

	public Navette (Attraction attraction, int numNavette) {
		this.attraction = attraction;
		this.numNavette = numNavette;
	}

	/**
	 * Un client monte dans la navette
	 * @throws InterruptedException
	 */
	public synchronized void monterClients() throws InterruptedException {
		while(nbClientsNavette > placeMax) {
			wait();
		}
		nbClientsNavette++;
		System.out.println("Navette " +numNavette+ " : " +nbClientsNavette+ " clients montent");
	}

	/**
	 * Un client descend de la navette
	 * @throws InterruptedException
	 */
	public synchronized void descendreClients() throws InterruptedException {
		if (nbClientsNavette == 0) {
			System.out.println("La navette " + numNavette+ " n'avait aucun client");;
		} else {
			System.out.println("Navette " +numNavette+ " : " +nbClientsNavette+ " clients descendent");
			this.nbClientsNavette = 0;
			notifyAll();
		}

	}


	/**
	 * Une navette arrive sur le quai
	 * Elle fait descendre les clients presents
	 * Elle recupere des clients
	 * Elle quitte le quai et commence son tour de manege
	 * @throws InterruptedException 
	 */

	public void run() {		
		try { Thread.sleep(10); } catch(InterruptedException e) {} // Temps tour de manege
	}


}
