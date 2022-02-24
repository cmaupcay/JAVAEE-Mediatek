package persistance.documents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatek2022.Document;
import persistance.MediathequeData;

public abstract class ResolutionDocument 
{
    public static final Document retrouver_objet(final ResultSet resultat) throws Exception
    {
        Exception precedent = null;
        try
        {
            final int id = resultat.getInt(_Document.BD_ID);
            final int type = resultat.getInt(_Document.BD_TYPE);
            final String emprunteur = resultat.getString(_Document.BD_EMPRUNTEUR);

            switch (type)
            {
            case DVD.TYPE:
                final String titre = resultat.getString(DVD.BD_TITRE);
                final String realisateur = resultat.getString(DVD.BD_REALISATEUR);
                final boolean adulte = resultat.getBoolean(DVD.BD_ADULTE);
                return new DVD(id, emprunteur, titre, realisateur, adulte);

            default:
                precedent = new Exception("Type du document inconnu.");
            }
        }
        catch (SQLException e) { precedent = e; }
        throw new Exception("Impossible de transformer le document en objet Java.", precedent);
    }

    public static final boolean preparer_ajout(final PreparedStatement operation, final int type, final Object... args) throws SQLException
    {
        switch (type)
        {
        case DVD.TYPE:
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
