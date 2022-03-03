package persistance.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

/**
 * Classe de base des documents implémentés.
 */
public abstract class _Document implements Document
{
    /** Nom de la table SQL associée aux documents. */
    public static final String BD_TABLE = "documents";
    /** Nom de la colonne de la table SQL associée à l'identifiant numérique du document. */
    public static final String BD_ID = "id";
    /** Nom de la colonne de la table SQL associée à l'emprunteur actuel du document. */
    public static final String BD_EMPRUNTEUR = "emprunteur";
    /** Nom de la colonne de la table SQL associée au type du document. */
    public static final String BD_TYPE = "type";

    /** Identifiant numérique unique du document. */
    private final int id;
    /**
     * Retourne l'identifiant numérique unique du document.
     * @return Identifiant numérique du document.
     */
    public final int id() { return this.id; }

    /** Identifiant numérique du type du document. */
    private final int type;
    /**
     * Retourne l'identifiant numérique du type du document.
     * @return Identifiant numérique du type du document.
     */
    public final int type() { return this.type; }

    /** Pseudo de l'emprunteur actuel du document, null si le document est disponible. */
    private String emprunteur;
    @Override
    public final boolean disponible() { return (this.emprunteur != null); }

    /**
     * Construction d'un document.
     * @param id Identifiant numérique du document.
     * @param type Identifiant numérique du type du document.
     * @param emprunteur Emprunteur du document, null si disponible.
     */
    protected _Document(final int id, final int type, final String emprunteur)
    {
        this.id = id;
        this.type = type;
        this.emprunteur = emprunteur;
    }

    @Override
    public final void emprunt(Utilisateur u) throws Exception
    {
        if (this.disponible()) this.emprunteur = u.name();
        else throw new Exception("Le document n'est pas disponible à l'emprunt.");
    }

    @Override
    public final void retour() { this.emprunteur = null; }
}