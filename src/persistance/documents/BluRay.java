package persistance.documents;

/**
 * Représentation d'un BluRay, qui est simplement un DVD avec un type différent.
 */
public class BluRay extends DVD
{
    /** Identifiant numérique des BluRay. */
    public static final int TYPE = 1;

    /**
     * Construction d'un BluRay.
     * @param id Identifiant numérique du document.
     * @param emprunteur Emprunteur du document, null si disponible.
     * @param titre Titre du film.
     * @param realisateur Réalisateur du film.
     * @param adulte Drapeau indiquant si le film est destiné exclusivement aux adultes.
     */
    public BluRay(final int id, final String emprunteur, final String titre, final String realisateur, final boolean adulte)
    {
        super(id, TYPE, emprunteur, titre, realisateur, adulte);
    }
}
