package Modele;

import Controler.Control;
import outils.connexion.Connection;
/**
 * Informations et méthodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {
	
	// Instance de Control pour communiquer avec le Controler
	protected Control control;

	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	public abstract void connexion(Connection connection);
	
	/**
	 * Réception d'une information provenant de l'ordinateur distant
	 */
	public abstract void reception(Connection connection, Object info) ;
	
	/**
	 * Déconnexion de l'ordinateur distant
	 */
	public abstract void deconnexion() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 */
	public void envoi(Connection connection, Object info) {
		this.control.envoi(connection, info);
	}
	
}
