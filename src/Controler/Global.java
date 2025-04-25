package Controler;

// Contient les constantes du programme
public interface Global {
	// Numero du port
	int PORT = 6666;
	
	// Nbr de joueur max
	int MAXNUMPERSO = 3;
	
	// Caractère de séparation des chemin
	String CHEMINSEPARATOR = "/";
	
	// Chemin dossier des images de fond
	String CHEMINFOND = "fonds" + CHEMINSEPARATOR;
	
	// Chemin dossier des images des personnages
	String CHEMINPERSO = "personnages" + CHEMINSEPARATOR;
	
	// Debut nom images perso
	String PERSO = "perso";
	
	// Etat marche du personnage
	String MARCHE = "marche";
	
	// Extension des fichier des images des personnages
	String EXTFICHIERPERSO = ".gif";
	
	// Chemin de l'umage de fond de la vue ChoixJoueur
	String FONDCHOIX = CHEMINFOND + "fondchoix.jpg";
	
	String FONDARENE = CHEMINFOND + "fondarene.jpg";
	
	// Message "pseudo" envoyé pour la création d'un joueur
	String PSEUDO = "pseudo";
	
	// Charactère de séparation d'info dans un string
	String STRINGSEPARATOR = "~";
	
	// Message "Connection" envoyé par la classe Connection
	String CONNEXION = "connexion";
	
	// Message "Reception" envoyé par la classe Connection
	String RECEPTION = "reception";
		
	// Message "Deconnection" envoyé par la classe Connection
	String DECONNEXION = "deconnexion";
	
	// Message "serveur" pour la création d'un serveur
	String SERVEUR = "serveur";
}
