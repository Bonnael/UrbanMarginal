package Controler;

import Vue.EntreeJeu;
import Vue.Arene;
import Vue.ChoixJoueur;
import outils.connexion.*;

public class Control implements AsyncResponse {
	
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	
	private String typeJeu;
	private final int PORT = 6666;
	
	public static void main(String[] args) {
		new Control();
	}
	
	// Constructeur
	private Control() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}
	
	// Repond au bouton connect ou start de EntreeJeu
	public void evenementEntreeJeu(String info) {
		// bouton start
		if (info.equals("serveur")) {
			typeJeu = "serveur";
			new ServeurSocket(this, PORT);
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
			frmEntreeJeu.dispose();
		// bouton connect
		} else {
			typeJeu = "client";
			new ClientSocket(this, info, PORT);
		}
	}
	
	// Repond au bouton Go de ChoixJoueur
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion":
			if (typeJeu.equals("client")) {
				frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}
			break;
		case "reception":
			break;
		case "deconnexion":
			break;
		}
	}

}
