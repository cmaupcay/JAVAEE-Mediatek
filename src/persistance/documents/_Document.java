package persistance.documents;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import persistance.MediathequeData;

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
    /**
     * Retourne le pseudo de l'emprunteur actuel du document.
     * @return Emprunteur actuel, null si le document est disponible.
     */
    public final String emprunteur() { return this.emprunteur; }
    @Override
    public final boolean disponible() { return (this.emprunteur == null); }

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
        if (this.disponible())
        {
            this.emprunteur = u.name();
            // Mise à jour de l'état du document
            MediathequeData.getInstance().majDocument(this);
        }
        else throw new Exception("Le document n'est pas disponible à l'emprunt.");
    }

    @Override
    public final void retour() 
    {
        if (!this.disponible())
        {
            this.emprunteur = null;
            // Mise à jour de l'état du document
            MediathequeData.getInstance().majDocument(this);
        }
    }

    /** Séparateur des informations dans le toString. */
    protected static final String INFO_SEP = "//";
    /**
     * Retourne les informations sérialisées du document.
     * @return Informations du documents.
     */
    protected abstract String _toString();

    @Override
    public final String toString() { return this.id + INFO_SEP + this.type + INFO_SEP + this.emprunteur + INFO_SEP + this._toString(); }
}