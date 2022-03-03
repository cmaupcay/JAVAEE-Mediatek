package persistance.documents;

/**
 * Fausse classe de Document. Utile au debug de MediathequeData.
 */
public class Erreur extends _Document
{
    /** Exception encapsulée. */
    private final Exception e;

    /**
     * Construction d'un nouveau document encapsulant une erreur.
     * @param e Erreur à encapsuler.
     */
    public Erreur(final Exception e)
    {
        super(0, -1, null);
        this.e = e;
    }
    
    @Override
    public final String toString() { return e.toString(); }
}
