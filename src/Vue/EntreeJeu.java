package Vue;

import Controler.Control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntreeJeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIPServer;
	private Control control;

	/**
	 * Create the frame.
	 */
	public EntreeJeu(Control control) {
		// Content Pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Label Start
		JLabel lblStart = new JLabel("Start a server :");
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStart.setBounds(10, 11, 116, 14);
		contentPane.add(lblStart);
		
		// Label Connect
		JLabel lblConnect = new JLabel("Connect an existing server :");
		lblConnect.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConnect.setBounds(10, 36, 184, 14);
		contentPane.add(lblConnect);
		
		// Label IP Server
		JLabel lblIPServ = new JLabel("IP server :");
		lblIPServ.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIPServ.setBounds(10, 61, 98, 14);
		contentPane.add(lblIPServ);
		
		// Texte IP Server
		txtIPServer = new JTextField();
		txtIPServer.setBounds(78, 59, 116, 20);
		txtIPServer.setText("127.0.0.1");
		txtIPServer.setColumns(10);
		contentPane.add(txtIPServer);

		// Bouton Start
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_clic();
			}
		});
		btnStart.setForeground(new Color(0, 0, 0));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStart.setBounds(203, 8, 89, 23);
		contentPane.add(btnStart);
		
		// Bouton Connect
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConnect.setBounds(203, 58, 89, 23);
		contentPane.add(btnConnect);
		
		// Bouton Exit
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit_clic();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(203, 92, 89, 23);
		contentPane.add(btnExit);
		
		// Controler
		this.control = control;
	}
	
	
	// clic sur le bouton start
	private void btnStart_clic() {
		this.control.evenementEntreeJeu("serveur");
	}
	
	// clic sur le bouton connect
	private void btnConnect_clic() {
		this.control.evenementEntreeJeu(txtIPServer.getText());
	}
	
	// clic sur le bouton exit
	private void btnExit_clic() {
		System.exit(0);
	}
}
