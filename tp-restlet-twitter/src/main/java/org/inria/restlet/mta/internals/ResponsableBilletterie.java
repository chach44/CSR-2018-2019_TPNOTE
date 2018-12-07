package org.inria.restlet.mta.internals;

public class ResponsableBilletterie extends Thread {

	private static int NB_BILLET = 10;
	private BilletterieImpl billetterie;

	public ResponsableBilletterie (BilletterieImpl billetterie) {
		this.billetterie = billetterie;
	}

	public void run () {
		while(true) {
			try {
				try { Thread.sleep(1000); } catch(InterruptedException e) {}
				this.billetterie.addNewBillet(NB_BILLET);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
