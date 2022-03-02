package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2022.*;
import persistance.documents.ResolutionDocument;
import persistance.documents._Document;
import persistance.utilisateurs.Abonne;
import persistance.utilisateurs.Bibliothecaire;
import persistance.utilisateurs._Utilisateur;

// TOCOMMENT
public class MediathequeData implements PersistentMediatheque
{
	static 
	{
		// Déclaration à la Médiathèque (instance unique).
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USR = "root";
	private static final String MDP = "";

	private Connection connexion;
	private final boolean connexion_valide()
	{ 
		try { return (this.connexion != null && !this.connexion.isClosed()); }
		catch (SQLException e) { return false; }
	}

	public MediathequeData() 
	{
		try { Class.forName(DRIVER); }
		catch (ClassNotFoundException e)
		{ e.printStackTrace(); }
		
		try 
		{ 
			this.connexion = DriverManager.getConnection(URL, USR, MDP);
			this.operation_documents = this.connexion.prepareStatement(SQL_DOCUMENTS);
			this.operation_utilisateur = this.connexion.prepareStatement(SQL_UTILISATEUR);
			this.operation_document = this.connexion.prepareStatement(SQL_DOCUMENT);
			this.operation_document_ajout = this.connexion.prepareStatement(SQL_DOCUMENT_AJOUT);
		}
		catch (SQLException e)
		{ e.printStackTrace(); }
	}

	private static final String SQL_DOCUMENTS = "SELECT * FROM documents WHERE disponible = 1;";
	private PreparedStatement operation_documents;
	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles()
	{
		if (this.connexion_valide())
		{
			try 
			{
				ResultSet resultat = this.operation_documents.executeQuery();
				ArrayList<Document> documents = new ArrayList<>();
				while (resultat.next())
				{
					try { documents.add(ResolutionDocument.retrouver_objet(resultat)); }
					catch (Exception e) { e.printStackTrace(); }
				}
				resultat.close();
				return documents;
			}
			catch (SQLException e)
			{ e.printStackTrace(); }
		}
		return null;
	}

	private static final String SQL_UTILISATEUR = "SELECT * FROM " + _Utilisateur.BD_TABLE + " WHERE " + _Utilisateur.BD_NOM + " = ? AND " + _Utilisateur.BD_MDP + " = ?;";
	private PreparedStatement operation_utilisateur;
	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
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
				if (resultat.first())
				{
					final String nom = resultat.getString(_Utilisateur.BD_NOM);
					if (resultat.getBoolean(_Utilisateur.BD_BIBLIOTHECAIRE))
						return new Bibliothecaire(nom);
					else
					{
						final int age = resultat.getInt(Abonne.BD_AGE);
						final boolean abonnement_actif = resultat.getBoolean(Abonne.BD_ABONNEMENT_ACTIF);
						return new Abonne(nom, age, abonnement_actif);
					}
				}
				resultat.close();
				return u;
			}
			catch (SQLException e)
			{ e.printStackTrace(); }
		}
		return null;
	}

	private static final String SQL_DOCUMENT = "SELECT * FROM " + _Document.BD_TABLE + " WHERE " + _Document.BD_ID + " = ?;";
	private PreparedStatement operation_document;
	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
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
				if (resultat.first())
				{
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

	public static final int SQL_DOCUMENT_AJOUT_TITRE = 1;
	public static final int SQL_DOCUMENT_AJOUT_AUTEUR = 2;
	public static final int SQL_DOCUMENT_AJOUT_ADULTE = 3;
	private static final String SQL_DOCUMENT_AJOUT = "INSERT INTO " + _Document.BD_TABLE + " (type, titre, auteur, adulte) VALUES (?, ?, ?, ?);";
	private PreparedStatement operation_document_ajout;
	@Override
	public void ajoutDocument(int type, Object... args)
	{
		if (this.connexion_valide())
		{
			try
			{
				this.operation_document_ajout.setInt(1, type);
				if (ResolutionDocument.preparer_ajout(this.operation_document_ajout, type, args))
					this.operation_document_ajout.execute();
			}
			catch (SQLException e)
			{ e.printStackTrace(); }
		}
	}

}
