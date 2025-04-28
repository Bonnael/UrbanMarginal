package Modele;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controler.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {
	/**
	 * message sous le perso
	 */
	private JLabel message;
	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'�tape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	* vie restante du joueur
	*/
	private int vie ; 
	/**
	* tourné vers la gauche (0) ou vers la droite (1)
	*/
	private int orientation ;
	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		// Valorisation
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " crée");
		// Creation du label du perso
		super.jLabel = new JLabel();
		// Creation du label du message sous le personnage
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		// Calcul du premier positionnement
		this.premierePosition(lesJoueurs, lesMurs);
		// demande d'ajout du label du personnage et du message dans l'arene du serveur
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		// demande d'affichage du personnage
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO));
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE));
		} while (this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		// positionnement du perso et affectation de la bonne image
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSO + PERSO + this.numPerso + etat + etape + "d" + orientation + EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		// positionnement et remplissage du message sous le personnage
		this.message.setBounds(posX - 10, posY + HAUTEURPERSO, LARGEURPERSO + 10, HAUTEURMESSAGE);
		this.message.setText(pseudo + " : " + vie);
		// demande d'envoi à tous des modifications d'affichage
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action(Integer action, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		switch(action) {
		case KeyEvent.VK_LEFT :
			orientation = GAUCHE;
			posX = deplace(posX, action, -PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_RIGHT :
			orientation = DROITE;
			posX = deplace(posX, action, PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_UP :
			posY = deplace(posY, action, -PAS, HAUTEURARENE - HAUTEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_DOWN :
			posY = deplace(posY, action, PAS, HAUTEURARENE - HAUTEURPERSO, lesJoueurs, lesMurs);
			break;
		}
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Gere le deplacement des joueur
	 * @param position position de depart
	 * @param action gauche, droite, haut ou bas
	 * @param lepas valeur de deplacement (positif ou negatif)
	 * @param max valeur à ne pas depasser
	 * @param lesJoueurs collection de joueurs pour eviter les collisions
	 * @param lesMurs collection de murs pour eviter les collisions
	 * @return nouvelle position
	 */
	private int deplace(int position, int action, int lepas, int max, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		int ancpos = position;
		position += lepas;
		position = Math.max(position, 0);
		position = Math.min(position, max);
		if (action == KeyEvent.VK_LEFT || action == KeyEvent.VK_RIGHT) {
			posX = position;
		} else {
			posY = position;
		}
		if (toucheJoueur(lesJoueurs) || toucheMur(lesMurs)) {
			position = ancpos;
		}
		etape = (etape % NBETAPESMARCHE) + 1;
		return position;
	}

	/**
	 * Contrôle si le joueur touche un des autres joueurs
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for (Joueur leJoueur : lesJoueurs) {
			if(!this.equals(leJoueur)) {
				if (super.toucheObjet(leJoueur)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
	}
	
	/**
	* Contrôle si le joueur touche un des murs
	* @return true si un joueur touche un mur
	*/
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Mur leMur : lesMurs) {
			if (super.toucheObjet(leMur)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}
	
	// Getter sur le pseudo
	public String getPseudo() {
		return pseudo;
	}
}
