package persistance.documents;

/**
 * Représentation d'un DVD.
 */
public class DVD extends _Document
{
    /** Identifiant numérique des DVDs. */
    public static final int TYPE = 0;

    /** Nom de la colonne de la table SQL associée au titre du film. */
    public static final String BD_TITRE = "titre";
    /** Titre du film. */
    private final String titre;
    /**
     * Retourne le titre du film.
     * @return Titre du film.
     */
    public final String titre() { return this.titre; }

    /** Nom de la colonne de la table SQL associée au réalisateur du film. */
    public static final String BD_REALISATEUR = "auteur";
    /** Réalisateur du film. */
    private final String realisateur;
    /**
     * Retourne le réalisateur du film.
     * @return Réalisateur du film.
     */
    public final String realisateur() { return this.realisateur; }

    /** Nom de la colonne de la table SQL associée au drapeau ADULTE. */
    public static final String BD_ADULTE = "adulte";
    /** Drapeau indiquant si le film est destiné exclusivement aux adultes. */
    private final boolean adulte;
    /**
     * Retourne la valeur du drapeau indiquant si le film est exclusivement réservé aux adultes. 
     * @return Drapeau indiquant s'il s'agit d'un film pour adulte.
     */
    public final boolean adulte() { return this.adulte; }
    
    /**
     * Construction d'un DVD pour les DVD plus spécialisés (ex: BlueRay).
     * @param id Identifiant numérique du document.
     * @param type Identifiant numérique du type du document.
     * @param emprunteur Emprunteur du document, null si disponible.
     * @param titre Titre du film.
     * @param realisateur Réalisateur du film.
     * @param adulte Drapeau indiquant si le film est destiné exclusivement aux adultes.
     */
    protected DVD(final int id, final int type, final String emprunteur, final String titre, final String realisateur, final boolean adulte)
    {
        super(id, type, emprunteur);
        this.titre = titre;
        this.realisateur = realisateur;
        this.adulte = adulte;
    }

    /**
     * Construction d'un DVD.
     * @param id Identifiant numérique du document.
     * @param emprunteur Emprunteur du document, null si disponible.
     * @param titre Titre du film.
     * @param realisateur Réalisateur du film.
     * @param adulte Drapeau indiquant si le film est destiné exclusivement aux adultes.
     */
    public DVD(final int id, final String emprunteur, final String titre, final String realisateur, final boolean adulte)
    {
        super(id, TYPE, emprunteur);
        this.titre = titre;
        this.realisateur = realisateur;
        this.adulte = adulte;
    }
}
