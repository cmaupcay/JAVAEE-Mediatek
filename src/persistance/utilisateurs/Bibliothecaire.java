package persistance.utilisateurs;

/**
 * Représentation des bibliothécaires.
 */
public class Bibliothecaire extends _Utilisateur
{
    /**
     * Construction d'une bibliothécaire.
     * @param nom Nom de l'utilisateur.
     */
    public Bibliothecaire(final String nom)
    {
        super(nom, true);
    }
}
