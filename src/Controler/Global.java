package Controler;

// Contient les constantes du programme
public interface Global {
	// Numero du port
	int PORT = 6666;
	
	// Nbr de joueur max
	int MAXNUMPERSO = 3;
	
	// Vie de depart pour tous les persos
	int MAXVIE = 10;
	
	// Gain de point de vie lors d'une attaque
	int GAIN = 1;
	
	// Perte de point de vie lors d'une attaque
	int PERTE = 2;
	
	// Caractère de séparation des chemin
	String CHEMINSEPARATOR = "/";
	
	// Chemin dossier des images de fond
	String CHEMINFOND = "fonds" + CHEMINSEPARATOR;
	
	// Chemin dossier des images des personnages
	String CHEMINPERSO = "personnages" + CHEMINSEPARATOR;
	
	// Chemin dossier de l'image du mur
	String CHEMINMUR = "murs" + CHEMINSEPARATOR;
	
	// Chemin dossier de l'image des boules
	String CHEMINBOULES = "boules" + CHEMINSEPARATOR;
	
	// Debut nom images perso
	String PERSO = "perso";
	
	// Etat marche du personnage
	String MARCHE = "marche";
	
	// Etat touche du personnage
	String TOUCHE = "touche";
	
	// Etat mort du personnage
	String MORT = "mort";
	
	// Extension des fichier des images des personnages
	String EXTFICHIERPERSO = ".gif";
	
	// Chemin de l'image de fond de la vue ChoixJoueur
	String FONDCHOIX = CHEMINFOND + "fondchoix.jpg";
	
	// Chemin de l'image de fond de la vue Arene
	String FONDARENE = CHEMINFOND + "fondarene.jpg";
	
	// Chemin de l'image du mur
	String MUR = CHEMINMUR + "mur.gif";
	
	// Chemin de l'image de la boule
	String BOULE = CHEMINBOULES + "boule.gif";
	
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
	
	// Message Client pour la creation d'un client
	String CLIENT = "client";
	
	// Message "serveur" pour la création d'un serveur
	String SERVEUR = "serveur";
	
	// Message ajout mur
	String AJOUTMUR = "ajout mur";
	
	// Message d'ajout du panel de mur
	String AJOUTPANELMUR = "ajout panel mur";
	
	// Message d'ajout de jlabel dans l'arene du serveur
	String AJOUTJLABELJEU = "ajout jlabel jeu";
	
	// Message pour modifier le panel du jeu dans l'arène du client
	String MODIFPANELJEU = "modif panel jeu";
	
	// Message pour ajouter une phrase au chat
	String CHAT = "chat";
	
	// Message pour une action d'un joueur
	String ACTION = "action";
	
	// Message pour ajouter une phrase dans l'arene du serveur
	String AJOUTPHRASE = "ajout phrase";
	
	// Message pour modifier le contenu du chat dans l'arène du client
	String MODIFCHAT = "modif chat";
	
	// Largeur de l'arène
	int LARGEURARENE = 800;
	
	// Longueur de l'arène
	int HAUTEURARENE = 600;
	
	// Largeur Mur
	int LARGEURMUR = 34;
			
	// Longueur Mur
	int HAUTEURMUR = 35;
	
	// Nombre de mur sur l'arène
	int NBMURS = 20;
	
	// hauteur du personnage
	int HAUTEURPERSO = 44;
	
	// largeur du personnage
	int LARGEURPERSO = 39;
	
	// Hauteur du message
	int HAUTEURMESSAGE = 8;
	
	// Largeur de la boule
	int LARGEURBOULE = 17;
	
	// Hauteur de la boule
	int HAUTEURBOULE = 17;
	
	// Direction : droite
	int DROITE = 1;
	
	// Direction : gauche
	int GAUCHE = 0;
	
	// Nombre de pixel parcouru lors d'un deplacement
	int PAS = 10;
	
	// Nombre d'étape de l'animation de marche
	int NBETAPESMARCHE = 4;
	
	// Nombre d'étape de l'animation de mort
	int NBETAPESMORT = 2;
	
	// Nombre d'étape de l'animation de touche
	int NBETAPESTOUCHE = 2;
}
