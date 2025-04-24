package Controler;

import Vue.EntreeJeu;

public class Control {
	
	private EntreeJeu frmEntreeJeu;
	
	public static void main(String[] args) {
		new Control();

	}
	
	// Constructeur
	private Control() {
		this.frmEntreeJeu = new EntreeJeu();
		this.frmEntreeJeu.setVisible(true);
	}

}
