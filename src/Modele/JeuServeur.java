package Modele;

import Controler.Control;
import Controler.Global;
import outils.connexion.Connection;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	
	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();
	
	/**
	 * Constructeur
	 */
	public JeuServeur(Control control) {
		super.control = control;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] lesInfos = ((String)info).split(STRINGSEPARATOR);
		String ordre = lesInfos[0];
		switch (ordre) {
			case PSEUDO:
				this.control.evenementJeuServeur(AJOUTPANELMUR, connection);
				String pseudo = lesInfos[1];
				int numPerso = Integer.parseInt(lesInfos[2]);
				this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, this.lesJoueurs.values(), this.lesMurs);
				String premierMessage = "*** " + pseudo + " vient de se connecter ***";
				this.control.evenementJeuServeur(AJOUTPHRASE, premierMessage);
				break;
			case CHAT:
				String phrase = lesInfos[1];
				phrase = this.lesJoueurs.get(connection).getPseudo() + " > " + phrase;
				this.control.evenementJeuServeur(AJOUTPHRASE, phrase);
				break;
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 */
	public void envoi(Object info) {
		for (Connection laConnection : this.lesJoueurs.keySet()) {
			super.envoi(laConnection, info);
		}
	}
	
	// Envoi du panel de jeu à tous les joueurs
	public void envoiJeuATous() {
		for (Connection laConnection : this.lesJoueurs.keySet()) {
			this.control.evenementJeuServeur(MODIFPANELJEU, laConnection);
		}
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for (int i = 0; i < NBMURS; i++) {
			this.lesMurs.add(new Mur());
			this.control.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getjLabel());
		}
	}
	
	public void ajoutJLabelJeuArene (JLabel jLabel) {
		this.control.evenementJeuServeur(AJOUTJLABELJEU, jLabel);
	}
	
}
