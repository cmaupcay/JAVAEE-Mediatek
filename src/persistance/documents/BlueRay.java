package persistance.documents;

public class BlueRay extends DVD
{
    public static final int TYPE = 1;

    public BlueRay(final int id, final String emprunteur, final String titre, final String realisateur, final boolean adulte)
    {
        super(id, TYPE, emprunteur, titre, realisateur, adulte);
    }
}
