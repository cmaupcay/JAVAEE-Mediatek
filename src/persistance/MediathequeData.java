package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2022.*;
import persistance.documents.Erreur;
import persistance.documents.ResolutionDocument;
import persistance.documents._Document;
import persistance.utilisateurs.Abonne;
import persistance.utilisateurs.Bibliothecaire;
import persistance.utilisateurs._Utilisateur;

/**
 * Classe de persistance des données de la médiathèque.
 * Cette classe fait le pont entre la médiathèque et la base de données MySQL.
 */
public class MediathequeData implements PersistentMediatheque
{
	static 
	{
		// Déclaration à la médiathèque (instance unique de persistance des données).
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	/** Classe du driver JDBC à utiliser. */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	/** URL de la base de données. */
	private static final String URL = "jdbc:mysql://localhost:3306/mediatek";
	/** Utilisateur de la base de données. */
	private static final String USR = "root";
	/** Mot de passe de l'utilisateur. */
	private static final String MDP = "";

	/** Connexion à la base de données. */
	private Connection connexion;
	/**
	 * Vérifie la validité de la connexion à la base de données.
	 * @return Indique si la connexion est ouverte.
	 */
	private final boolean connexion_valide()
	{ 
		try { return (!this.connexion.isClosed()); }
		catch (Exception e) { return false; }
	}

	/**
	 * Construction de l'instance unique de persistance.
	 */
	public MediathequeData() 
	{
		// Chargement du driver JDBC.
		try 
		{ Class.forName(DRIVER); }
		catch (ClassNotFoundException e)
		{ 
			e.printStackTrace();
			return; // On quitte si on ne peut charger le driver.
		}
		
		try 
		{ 
			// Connexion à la base de données.
			this.connexion = DriverManager.getConnection(URL, USR, MDP);
			// Création des opérations SQL préparées.
			this.operation_documents = this.connexion.prepareStatement(SQL_DOCUMENTS);
			this.operation_utilisateur = this.connexion.prepareStatement(SQL_UTILISATEUR);
			this.operation_document = this.connexion.prepareStatement(SQL_DOCUMENT);
			this.operation_document_ajout = this.connexion.prepareStatement(SQL_DOCUMENT_AJOUT);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}

	/** Commande SQL de sélection des documents disponibles. */
	private static final String SQL_DOCUMENTS = "SELECT * FROM documents WHERE emprunteur IS NULL;";
	/** Opération SQL préparée de sélection des documents disponibles. */
	private PreparedStatement operation_documents;
	@Override
	public List<Document> tousLesDocumentsDisponibles()
	{
		if (this.connexion_valide())
		{
			List<Document> documents = new ArrayList<>();
			try 
			{
				ResultSet resultat = this.operation_documents.executeQuery();
				while (resultat.next())
				{
					try { documents.add(ResolutionDocument.retrouver_objet(resultat)); }
					catch (Exception e) 
					{
						documents.add(new Erreur(e)); // DEBUG
						e.printStackTrace();
					}
				}
				resultat.close();
			}
			catch (SQLException e) 
			{
				documents.add(new Erreur(e)); // DEBUG
				e.printStackTrace();
			}
			return documents;
		}
		return null;
	}

	/** Commande SQL de sélection d'un utilisateur depuis son pseudo et son mot de passe (connexion). */
	private static final String SQL_UTILISATEUR = "SELECT * FROM " + _Utilisateur.BD_TABLE + " WHERE " + _Utilisateur.BD_NOM + " = ? AND " + _Utilisateur.BD_MDP + " = ?;";
	/** Opération SQL préparée de séléction d'un utilisateur depuis son pseudo et son mot de passe. */
	private PreparedStatement operation_utilisateur;
	@Override
	public Utilisateur getUser(String login, String password) 
	{
		if (this.connexion_valide())
		{
			try
			{
				this.operation_utilisateur.setString(1, login);
				this.operation_utilisateur.setString(2, password);
				ResultSet resultat = this.operation_utilisateur.executeQuery();
				Utilisateur u = null;
				if (resultat.first()) // On attend qu'un seul résultat.
				{
					final String nom = resultat.getString(_Utilisateur.BD_NOM);
					// Construction de l'objet Utilisateur selon le rôle de l'utilisateur.
					if (resultat.getBoolean(_Utilisateur.BD_BIBLIOTHECAIRE))
						u = new Bibliothecaire(nom);
					else
					{
						final int age = resultat.getInt(Abonne.BD_AGE);
						final boolean abonnement_actif = resultat.getBoolean(Abonne.BD_ABONNEMENT_ACTIF);
						u = new Abonne(nom, age, abonnement_actif);
					}
				}
				resultat.close();
				return u;
			}
			catch (SQLException e) { e.printStackTrace(); }
		}
		return null;
	}

	/** Commande SQL de sélection d'un document depuis son identifiant numérique. */
	private static final String SQL_DOCUMENT = "SELECT * FROM " + _Document.BD_TABLE + " WHERE " + _Document.BD_ID + " = ?;";
	/** Opération SQL préparée de sélection d'un document depuis son identifiant numérique. */
	private PreparedStatement operation_document;
	@Override
	public Document getDocument(int numDocument) 
	{
		if (this.connexion_valide())
		{
			try
			{
				this.operation_document.setInt(1, numDocument);
				ResultSet resultat = this.operation_document.executeQuery();
				Document d = null;
				if (resultat.first()) // On attend qu'un seul résultat.
				{
					// Délégation ed la construction de l'objet Document à la fonction de résolution.
					try { d = ResolutionDocument.retrouver_objet(resultat); }
					catch (Exception e) { e.printStackTrace(); }
				}
				resultat.close();
				return d;
			}
			catch (SQLException e)
			{ e.printStackTrace(); }
		}
		return null;
	}

	/** Commande SQL d'insertion d'un nouveau document. */
	private static final String SQL_DOCUMENT_AJOUT = "INSERT INTO " + _Document.BD_TABLE + " (type, titre, auteur, adulte) VALUES (?, ?, ?, ?);";
	/** Opération SQL préparée d'insertion d'un nouveau document. */
	private PreparedStatement operation_document_ajout;
	/** Index du paramètre TITRE dans la commande SQL d'insertion. */
	public static final int SQL_DOCUMENT_AJOUT_TITRE = 2;
	/** Index du paramètre AUTEUR dans la commande SQL d'insertion. */
	public static final int SQL_DOCUMENT_AJOUT_AUTEUR = 3;
	/** Index du paramètre ADULTE dans la commande SQL d'insertion. */
	public static final int SQL_DOCUMENT_AJOUT_ADULTE = 4;
	@Override
	public void ajoutDocument(int type, Object... args)
	{
		if (this.connexion_valide())
		{
			try
			{
				// Modification du type.
				this.operation_document_ajout.setInt(1, type);
				// Délégation à la fonction de préparation de l'ajout.
				if (ResolutionDocument.preparer_ajout(this.operation_document_ajout, type, args))
					this.operation_document_ajout.execute();
			}
			catch (SQLException e) { e.printStackTrace(); }
		}
	}

}
