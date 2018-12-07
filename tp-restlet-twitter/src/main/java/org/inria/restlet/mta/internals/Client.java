package org.inria.restlet.mta.internals;

public class Client extends Thread {

	static int NB_BILLET = 3;
	
	private BilletterieImpl billetterie;
	private Attraction trainFantome;
	private Attraction grandHuit;

	public Client (BilletterieImpl billetterie, Attraction trainFantome, Attraction grandHuit) {
		this.billetterie = billetterie;
		this.trainFantome = trainFantome;
		this.grandHuit = grandHuit;
	}
	
	public void run () {
		try {
			billetterie.acheterBillet(NB_BILLET);
			try { Thread.sleep(10); } catch(InterruptedException e) {} //Cours a la premiere attraction
			trainFantome.start();
			try { Thread.sleep(10); } catch(InterruptedException e) {} //Cours a la deuxieme attraction
			grandHuit.start();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}