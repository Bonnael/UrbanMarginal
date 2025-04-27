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
	public void envoi() {
	}
	
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
