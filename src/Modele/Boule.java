package Modele;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controler.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable {

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur;
	
	/**
	 * Collection de murs
	 */
	private Collection lesMurs;
	
	/**
	 * joueur qui lance la boule
	 */
	private Joueur attaquant;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.jLabel = new JLabel();
		super.jLabel.setVisible(false);
		URL resource = getClass().getClassLoader().getResource(BOULE);
		super.jLabel.setIcon(new ImageIcon(resource));
		super.jLabel.setBounds(0, 0, LARGEURBOULE, HAUTEURBOULE);
	}
	
	/**
	 * Tire d'une boule
	 */
	public void tireBoule(Joueur attaquant, Collection lesMurs) {
		this.lesMurs = lesMurs;
		this.attaquant = attaquant;
		// positionnement de la boule
		if (attaquant.getOrientation() == GAUCHE) {
			posX = attaquant.getPosX() - LARGEURBOULE - 1;
		} else {
			posX = attaquant.getPosX() + LARGEURPERSO + 1;
		}
		posY = attaquant.getPosY() + HAUTEURPERSO/2;
		// nouveau thread pour lancer la boule
		new Thread(this).start();
	}
	
	@Override 
	public void run() {
		// jouer le son fight
		this.jeuServeur.envoi(FIGHT);
		// affiche l'attaquant
		this.attaquant.affiche(MARCHE, 1);
		// rend visible la boule
		super.jLabel.setVisible(true);
		// creation de la victime
		Joueur victime = null;
		// valeur du pas en fonction de l'orientation
		int lePas;
		if(attaquant.getOrientation() == DROITE) {
			lePas = PAS;
		} else {
			lePas = -PAS;
		}
		// gestion du deplacement de la boule
		do {
			posX += lePas;
			jLabel.setBounds(posX, posY, LARGEURBOULE, HAUTEURBOULE);
			pause(80,0);
			this.jeuServeur.envoiJeuATous();
			Collection lesJoueurs = this.jeuServeur.getJoueurs();
			victime = (Joueur)super.toucheCollectionObjets(lesJoueurs);
		} while (posX > 0 && posX < LARGEURARENE && this.toucheCollectionObjets(lesMurs) == null && victime == null);
		// controle s'il y a une victime
		if (victime != null && !victime.estMort()) {
			this.jeuServeur.envoi(HURT);
			victime.perteVie();
			attaquant.gainVie();
			// si la victime est touchée
			for (int i = 1; i <= NBETAPESTOUCHE; i++) {
				victime.affiche(TOUCHE, i);
				pause(80, 0);
			}
			// controle si la victime est morte
			if (victime.estMort()) {
				this.jeuServeur.envoi(DEATH);
				for (int i = 1; i <= NBETAPESMORT; i++) {
					victime.affiche(MORT, i);
					pause(80, 0);
				}
			} else {
				// remet la victime dans la position de repos
				victime.affiche(MARCHE, 1);
			}
		}
		// rend la boule invisible
		this.jLabel.setVisible(false);
		// envoyer le nouveau jeu à tous
		this.jeuServeur.envoiJeuATous();
	}
	
	private void pause(long millisecondes, int nanosecondes) {
		try {
			Thread.sleep(millisecondes, nanosecondes);
		} catch (InterruptedException e){
			System.out.println("Erreur pause");
		}
	}
	
}
