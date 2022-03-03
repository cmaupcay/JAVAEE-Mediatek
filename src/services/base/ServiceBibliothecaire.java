package services.base;

import mediatek2022.Utilisateur;

/**
 * Classe de base des services nécessitant une authentification en tant que bibliothécaire uniquement.
 */
public abstract class ServiceBibliothecaire extends ServiceAuthentification
{
    /**
     * Construction d'un service nécessitant une authentification en tant que bibliothécaire.
     * @param jsp Nom du fichier JSP associé au service, sans l'extension de fichier.
     */
    protected ServiceBibliothecaire(final String jsp)
    {
        super(jsp);
    }

    @Override
    protected boolean accepter_utilisateur(final Utilisateur u) { return u.isBibliothecaire(); }
}
