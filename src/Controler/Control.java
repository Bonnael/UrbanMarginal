package Controler;

import Vue.EntreeJeu;
import Vue.Arene;
import Vue.ChoixJoueur;
import outils.connexion.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Jeu;
import Modele.JeuServeur;
import Modele.JeuClient;

public class Control implements AsyncResponse, Global {
	
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Jeu leJeu;
	
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
		if (info.equals(SERVEUR)) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene(this, SERVEUR);
			((JeuServeur)this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		// bouton connect
		} else {
			new ClientSocket(this, info, PORT);
		}
	}
	
	// Repond au bouton Go de ChoixJoueur
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient)this.leJeu).envoi(PSEUDO + STRINGSEPARATOR + pseudo + STRINGSEPARATOR + numPerso);
	}
	/**
	 * Demande provenant de JeuServeur
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch (ordre) {
		case AJOUTMUR:
			frmArene.ajoutMurs(info);
			break;
		case AJOUTPANELMUR:
			this.leJeu.envoi((Connection)info, this.frmArene.getjpnMur());
			break;
		case AJOUTJLABELJEU:
			this.frmArene.ajoutJLabelJeu((JLabel)info);
			break;
		case MODIFPANELJEU:
			this.leJeu.envoi((Connection)info, this.frmArene.getJpnJeu());
			break;
		case AJOUTPHRASE:
			this.frmArene.ajoutChat((String)info);
			((JeuServeur)this.leJeu).envoi(this.frmArene.getTxtChat());
			break;
		}
	}
	
	/**
	 * Demande provenant de JeuClient
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch (ordre) {
		case AJOUTPANELMUR:
			this.frmArene.setJpnMur((JPanel)info);
			break;
		case MODIFPANELJEU:
			this.frmArene.setJpnJeu((JPanel)info);
			break;
		case MODIFCHAT:
			this.frmArene.setTxtChat((String)info);
			break;
		case JOUESON:
			this.frmArene.joueSon((Integer)info);
			break;
		}
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case CONNEXION:
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				frmEntreeJeu.dispose();
				this.frmArene = new Arene(this, CLIENT);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION:
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION:
			break;
		}
	}
	
	/**
	 * Envoi d'information
	 * @param connection
	 * @param info
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
	/**
	 * Information provenant de la vue Arene
	 * @param info information
	 */
	public void evenementArene(Object info) {
		if (info instanceof String) {
			((JeuClient)this.leJeu).envoi(CHAT + STRINGSEPARATOR + info);
		} else if (info instanceof Integer) {
			((JeuClient)this.leJeu).envoi(ACTION + STRINGSEPARATOR + info);
		}
		
	}

}
