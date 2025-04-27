package Modele;

import javax.swing.JPanel;

import Controler.Control;
import Controler.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {	
	// Objet de connexion pour communiquer avec le serveur
	private Connection connection;
	private Boolean mursOk = false;
	
	/**
	 * Controleur
	 */
	public JeuClient(Control control) {
		super.control = control;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			if (!this.mursOk) {
				// arrivée du panel des murs
				this.control.evenementJeuClient(AJOUTPANELMUR, info);
				this.mursOk = true;
			} else {
				this.control.evenementJeuClient(MODIFPANELJEU, info);
			}
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}

}
