package Modele;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Controler.Global;
/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur
	 */
	public Mur() {
		// calcul position aléatoire du mur
		posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURMUR));
		posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURMUR));
		
		// création du JLabel pour ce mur
		jLabel = new JLabel();
		// caracteristiques du mur
		jLabel.setBounds(posX, posY, LARGEURMUR, HAUTEURMUR);
		URL resource = getClass().getClassLoader().getResource(MUR);
		jLabel.setIcon(new ImageIcon(resource));
	}
	
}
