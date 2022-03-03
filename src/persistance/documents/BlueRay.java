package persistance.documents;

/**
 * Représentation d'un BlueRay, qui est simplement un DVD avec un type différent.
 */
public class BlueRay extends DVD
{
    /** Identifiant numérique des BlueRays. */
    public static final int TYPE = 1;

    /**
     * Construction d'un BlueRay.
     * @param id Identifiant numérique du document.
     * @param emprunteur Emprunteur du document, null si disponible.
     * @param titre Titre du film.
     * @param realisateur Réalisateur du film.
     * @param adulte Drapeau indiquant si le film est destiné exclusivement aux adultes.
     */
    public BlueRay(final int id, final String emprunteur, final String titre, final String realisateur, final boolean adulte)
    {
        super(id, TYPE, emprunteur, titre, realisateur, adulte);
    }
}
