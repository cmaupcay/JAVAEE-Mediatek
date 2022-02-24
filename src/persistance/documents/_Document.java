package persistance.documents;

import java.sql.PreparedStatement;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public abstract class _Document implements Document
{
    public static final String BD_TABLE = "documents";
    public static final String BD_ID = "id";
    public static final String BD_EMPRUNTEUR = "emprunteur";
    public static final String BD_TYPE = "type";

    private final int id;
    private final int type;
    private String emprunteur;

    protected _Document(final int id, final int type, final String emprunteur)
    {
        this.id = id;
        this.type = type;
        this.emprunteur = emprunteur;
    }

    public final int id() { return this.id; }
    public final int type() { return this.type; }
    @Override
    public final boolean disponible() { return (this.emprunteur != null); }

    @Override
    public final void emprunt(Utilisateur u) throws Exception
    {
        if (this.disponible()) this.emprunteur = u.name();
        else throw new Exception("Le document n'est pas disponible à l'emprunt.");
    }

    @Override
    public final void retour() { this.emprunteur = null; }
}