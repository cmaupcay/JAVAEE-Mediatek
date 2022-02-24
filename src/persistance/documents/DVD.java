package persistance.documents;

public class DVD extends _Document
{
    public static final int TYPE = 0;

    public static final String BD_TITRE = "titre";
    private final String titre;
    public final String titre() { return this.titre; }

    public static final String BD_REALISATEUR = "auteur";
    private final String realisateur;
    public final String realisateur() { return this.realisateur; }

    public static final String BD_ADULTE = "adulte";
    private final boolean adulte;
    public final boolean adulte() { return this.adulte; }
    
    public DVD(final int id, final String emprunteur, final String titre, final String realisateur, final boolean adulte)
    {
        super(id, TYPE, emprunteur);
        this.titre = titre;
        this.realisateur = realisateur;
        this.adulte = adulte;
    }
}
