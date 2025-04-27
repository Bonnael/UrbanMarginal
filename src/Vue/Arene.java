package Vue;

import Controler.Control;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Arene extends JFrame implements Global {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpnJeu;
	private JPanel jpnMur;
	private JTextArea txtChat;
	private JTextField txtSaisie;
	private Control control;
	private Boolean client;

	/**
	 * Create the frame.
	 */
	public Arene(Control control, String typeJeu) {
		this.client = typeJeu.equals(CLIENT);
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
		
		// Panel Joueur
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
			
		// Panel Mur
		jpnMur = new JPanel();
		jpnMur.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMur.setOpaque(false);
		jpnMur.setLayout(null);
		contentPane.add(jpnMur);
		
		// Saisie Chat
		if (this.client) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					validationChat(e);
				}
			});
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		}
		
		// Container Chat
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspChat.setBounds(0, 625, 800, 176);
		contentPane.add(jspChat);
		
		// Chat
		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);
			
		// Fond
		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		this.control = control;
	}
	
	/**
	 * Ajour d'un mur dans le panel des murs
	 * @param unMur le mur à ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMur.add((JLabel)unMur);
		jpnMur.repaint();
	}
	
	/**
	 * Ajout d'un joueur, son message ou sa boule, dans le panel de jeu
	 * @param unJLabel
	 */
	public void ajoutJLabelJeu(JLabel unJLabel) {
		this.jpnJeu.add(unJLabel);
		this.jpnJeu.repaint();
	}
	
	// Setter du jpnJeu
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}
	
	// Getter du jpnJeu
	public JPanel getJpnJeu() {
		return jpnJeu;
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
	
	// Setter du txtChat
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	
	// Getter du txtChat
	public String getTxtChat() {
		return txtChat.getText();
	}
	
	/**
	 * Ajout d'une phrase à inserer à la fin du chat
	 * @param phrase phrase à inserer
	 */
	public void ajoutChat(String phrase) {
		this.txtChat.setText(this.txtChat.getText() + phrase + "\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	
	public void validationChat(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!this.txtSaisie.getText().equals("")) {
				this.control.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
			}
		}
	}
}
