package org.inria.restlet.mta.application;

import org.inria.restlet.mta.internals.Attraction;
import org.inria.restlet.mta.internals.BilletterieImpl;
import org.inria.restlet.mta.internals.Client;
import org.inria.restlet.mta.internals.ResponsableBilletterie;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 */
public class MyParcAsterixApplication extends Application
{
	static final int maxClients = 100;

	private Client[] clients = new Client[maxClients];
	private int nbClients = 0;
	
	private BilletterieImpl billetterie;
	private Attraction trainFantome;
	private Attraction grandHuit;
	private ResponsableBilletterie MrFoursov;

	public MyParcAsterixApplication(/*Context context*/)
	{
		//super(context);
		this.trainFantome = new Attraction(7);
		this.grandHuit = new Attraction(5);
		
		this.billetterie = new BilletterieImpl();

		this.MrFoursov = new ResponsableBilletterie(billetterie);
		this.MrFoursov.start();
		
		/* Instanciation des clients */
		for(int j = 0; j < maxClients; j++) 
			nouveauClient();
	}

	private boolean nouveauClient() {

		if(nbClients == maxClients) {
			System.out.println("Le nombre maximum de clients est atteint.");
			return false;
		}

		clients[nbClients] = new Client(billetterie, trainFantome, grandHuit);
		clients[nbClients].start();
		nbClients++;

		return true;

	}


	/**
	 *  Point d'entree du programme 
	 */
	public static void main(String[] args) {
		MyParcAsterixApplication parcAsterix = new MyParcAsterixApplication(/*context*/);

	}


	@Override
	public Restlet createInboundRoot()
	{
		Router router = new Router(getContext());

		return router;
	}
}
