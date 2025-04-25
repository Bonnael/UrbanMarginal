package Vue;

import Vue.Arene;
import Controler.Control;
import Controler.Global;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class ChoixJoueur extends JFrame implements Global {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblPersonnage;
	private Control control;
	
	private int numPerso;


	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Control control) {
		
		// ContentPane
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		this.setResizable(false);
		setTitle("Choix Joueur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// Label choix perso
		lblPersonnage = new JLabel("");
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnage.setBounds(142, 114, 120, 120);
		contentPane.add(lblPersonnage);
		
		// Fond
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(FONDCHOIX);
		lblFond.setIcon(new ImageIcon(resource));
		contentPane.add(lblFond);
		
		// Texte Pseudo
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		// Label Fleche Gauche
		JLabel lblGauche = new JLabel("");
		lblGauche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGauche_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormal();
			}
		});
		lblGauche.setBounds(53, 139, 53, 52);
		contentPane.add(lblGauche);
		
		// Label Fleche Droite
		JLabel lblDroite = new JLabel("");
		lblDroite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblDroite_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormal();
			}
		});
		lblDroite.setBounds(292, 139, 46, 52);
		contentPane.add(lblDroite);
		
		// Label Go
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormal();
			}
		});
		lblGo.setBounds(308, 196, 68, 65);
		contentPane.add(lblGo);
		
		// Controler
		this.control = control;
		
		// Numero personnage
		this.numPerso = 1;
		
		// Affichage personnage
		this.affichePerso();
	}
	
	// Clic sur la flèche de gauche
	private void lblGauche_clic() {
		if (numPerso == 1) {
			numPerso = MAXNUMPERSO;
		} else {
			numPerso--;
		}
		this.affichePerso();
	}
	
	// Clic sur la flèche de droite
	private void lblDroite_clic() {
		if (numPerso == MAXNUMPERSO) {
			numPerso = 1;
		} else {
			numPerso++;
		}
		this.affichePerso();
	}
	
	// Clic sur le bouton go
	private void lblGo_clic() {
		if (txtPseudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocus();
		} else {
			this.control.evenementChoixJoueur(txtPseudo.getText(), numPerso);
		}
	}
	
	// Affichage des personnages
	private void affichePerso() {
		String chemin = CHEMINPERSO + PERSO + this.numPerso + MARCHE + 1 + "d" + 1 + EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		this.lblPersonnage.setIcon(new ImageIcon(resource));
	}
	
	// Affichage souris
	private void sourisNormal() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
