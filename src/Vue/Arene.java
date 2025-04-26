package Vue;

import Controler.Global;
import Modele.Mur;
import Modele.Objet;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Arene extends JFrame implements Global {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpnMur;
	private JTextField txtSaisie;

	/**
	 * Create the frame.
	 */
	public Arene() {
		// ContentPane
		setTitle("Arene");
		this.getContentPane().setPreferredSize(new Dimension(800, 800));
		this.pack();
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Panel Mur
		jpnMur = new JPanel();
		jpnMur.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMur.setOpaque(false);
		jpnMur.setLayout(null);
		contentPane.add(jpnMur);
		
		// Saisie Chat
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		// Container Chat
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspChat.setBounds(0, 625, 800, 176);
		contentPane.add(jspChat);
		
		// Chat
		JTextArea txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
			
		// Fond
		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
	}
	
	/**
	 * Ajour d'un mur dans le panel des murs
	 * @param unMur le mur à ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMur.add((JLabel)unMur);
		jpnMur.repaint();
	}
	
	// Setter sur le jpnMur
	public void setJpnMur(JPanel jpnMur) {
		this.jpnMur.add(jpnMur);
		this.jpnMur.repaint();
	}
	
	// Getter du jpnMur
	public JPanel getjpnMur() {
		return jpnMur;
	}
}
