package Vue;

import Vue.Arene;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoixJoueur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblGauche;
	private JLabel lblDroite;
	private JLabel lblGo;
	
	private Arene frmArene;

	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		
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
		
		// Fond
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));
		contentPane.add(lblFond);
		
		// Texte Pseudo
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		// Label Fleche Gauche
		lblGauche = new JLabel("");
		lblGauche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGauche_clic();
			}
		});
		lblGauche.setBounds(53, 139, 53, 52);
		contentPane.add(lblGauche);
		
		// Label Fleche Droite
		lblDroite = new JLabel("");
		lblDroite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblDroite_clic();
			}
		});
		lblDroite.setBounds(292, 139, 46, 52);
		contentPane.add(lblDroite);
		
		// Label Go
		lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
		});
		lblGo.setBounds(308, 196, 68, 65);
		contentPane.add(lblGo);
	}
	
	// Clic sur la flèche de gauche
	private void lblGauche_clic() {
		System.out.println("Précédent");
	}
	
	// Clic sur la flèche de droite
	private void lblDroite_clic() {
		System.out.println("Suivant");
	}
	
	// Clic sur le bouton go
	private void lblGo_clic() {
		this.frmArene = new Arene();
		this.frmArene.setVisible(true);
		this.dispose();
	}
}
