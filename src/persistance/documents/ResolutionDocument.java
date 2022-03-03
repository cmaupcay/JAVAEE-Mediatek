package persistance.documents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatek2022.Document;
import persistance.MediathequeData;

/**
 * Classe abstraite aidant à la résolution du type d'un document, depuis et vers la BD.
 */
public abstract class ResolutionDocument 
{
    /**
     * Reconstruction de l'objet associé à un document dans un ResultSet.
     * @param resultat Résultat d'une opération SQL. Le curseur n'est pas déplacé.
     * @return Document reconstruit.
     * @throws Exception Impossible de transformer le document en objet Java.
     */
    public static final Document retrouver_objet(final ResultSet resultat) throws Exception
    {
        Exception precedent = null;
        try
        {
            final int id = resultat.getInt(_Document.BD_ID);                        // Récupération de l'identifiant unique.
            final int type = resultat.getInt(_Document.BD_TYPE);                    // Récupération du type.
            final String emprunteur = resultat.getString(_Document.BD_EMPRUNTEUR);  // Récupération de l'emprunteur actuel.

            switch (type)   // Résolution selon le type.
            {
            // Les DVDs et BlueRay ont le même constructeur.
            case DVD.TYPE:
            case BlueRay.TYPE:
                final String titre = resultat.getString(DVD.BD_TITRE);              // Récupération du titre du film.
                final String realisateur = resultat.getString(DVD.BD_REALISATEUR);  // Récupération du réaliateur.
                final boolean adulte = resultat.getBoolean(DVD.BD_ADULTE);          // Récupération du drapeau ADULTE.
                // Renvoi d'une nouvelle instance de la classe de document, selon le type résolu.
                if (type == BlueRay.TYPE) return new BlueRay(id, emprunteur, titre, realisateur, adulte);
                else return new DVD(id, emprunteur, titre, realisateur, adulte);

            default:
                precedent = new Exception("Type du document inconnu (" + type + ").");
            }
        }
        catch (SQLException e) { precedent = e; }
        throw new Exception("Impossible de transformer le document en objet Java.", precedent);
    }

    /**
     * Paramètre un opération SQL de type INSERT (ou UPDATE) selon le type de document souhaité.
     * @param operation Opération SQL préparé.
     * @param type Identifiant numérique du type de document à ajouter.
     * @param args Arguments de création du document.
     * @return Indique si le type à pu être résolu.
     * @throws SQLException Erreur lors de la modification de l'opération SQL.
     */
    public static final boolean preparer_ajout(final PreparedStatement operation, final int type, final Object... args) throws SQLException
    {
        switch (type)
        {
        case DVD.TYPE:
        case BlueRay.TYPE:
            operation.setString(MediathequeData.SQL_DOCUMENT_AJOUT_TITRE, (String)args[0]);     // Titre
            operation.setString(MediathequeData.SQL_DOCUMENT_AJOUT_AUTEUR, (String)args[1]);    // Réalisateur
            operation.setBoolean(MediathequeData.SQL_DOCUMENT_AJOUT_ADULTE, (Boolean)args[2]);  // Adulte ou non
            break;

        default:
            return false;
        }
        return true;
    }
}
