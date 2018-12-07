package org.inria.restlet.mta.internals;

public class Attraction extends Thread {
	private Navette[] navettes;
	private int numNavetteAQuai = 0;
	private boolean navetteAQuai = false;

	/**
	 * Creer une attraction avec un nombre de navettes defini en parametre
	 * @param nbNavettes
	 */
	public Attraction(int nbNavettes) {

		this.navettes = new Navette[nbNavettes];
		/* Instanciation des Navettes */
		for(int i = 0; i < nbNavettes; i++) {
			navettes[i] = new Navette(this, i);
			navettes[i].start();
		}
	}

	/**
	 * Arrivee d'une navette sur le quai de l'attraction
	 * Il ne doit y avoir aucune navette sur le quai pour que notre derniere puisse
	 * @throws InterruptedException
	 */
	public synchronized void arriverNavetteSurQuai() throws InterruptedException {
		while(navetteAQuai) {
			wait();
		}
		navetteAQuai = true;
		System.out.println("La navette " + numNavetteAQuai+ " arrive");
	}

	/**
	 * Depart de la navette presente sur le quai de l'attraction
	 * @throws InterruptedException
	 */
	public synchronized void departNavetteDuQuai() throws InterruptedException {
		navetteAQuai = false;
		System.out.println("La navette " + numNavetteAQuai+ " part");
		notify();
	}

	/**
	 * Changement de navettes
	 */
	public void run() {		
		while(numNavetteAQuai < this.navettes.length) {
			try {
				arriverNavetteSurQuai();
				navettes[numNavetteAQuai].descendreClients();
				navettes[numNavetteAQuai].monterClients();
				departNavetteDuQuai();
				//navettes[numNavetteAQuai].start();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			numNavetteAQuai++;

			if (numNavetteAQuai == this.navettes.length) {
				numNavetteAQuai = 0;
			}
		}    	
	}
}
