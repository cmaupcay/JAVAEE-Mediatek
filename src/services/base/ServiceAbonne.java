package services.base;

import mediatek2022.Utilisateur;

/**
 * Classe de base des services nécessitant une authentification en tant qu'abonné.
 * Le service peut être configuré pour accepter également les bibliothécaires.
 */
public abstract class ServiceAbonne extends ServiceAuthentification
{
    /** Drapeau indiquant si les bibliothécaires doivent êtres autorisés. */
    private final boolean accepter_bibliothecaire;

    /**
     * Construction d'un service nécessitant une authentification au moins de niveau abonné.
     * @param jsp Nom du fichier JSP associé au service, sans l'extension de fichier.
     * @param accepter_bibliothecaire Drapeau indiquant si les bibliothécaires doivent êtres autorisés.
     */
    protected ServiceAbonne(final String jsp, final boolean accepter_bibliothecaire)
    {
        super(jsp);
        this.accepter_bibliothecaire = accepter_bibliothecaire;
    }

    @Override
    protected boolean accepter_utilisateur(final Utilisateur u)
    {
        if (u.isBibliothecaire()) return this.accepter_bibliothecaire;
        return true;
    }
}
