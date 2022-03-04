package services.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.Utilisateur;

/**
 * Classe de base des services nécessitant une authentification.
 */
public abstract class ServiceAuthentification extends Service
{
    /**
     * Construction d'un service nécessitant une authentification.
     * @param jsp Nom du fichier JSP associé au service, sans l'extension de fichier.
     */
    protected ServiceAuthentification(final String jsp)
    {
        super(jsp);
    }

    /** Nom de l'attribut associé à l'utilisateur dans la session. */
    public static final String PARAM_UTILISATEUR = "u";

    /**
     * Conditions d'acceptation d'un utilisateur.
     * @param u Utilisateur demandant une autorisation.
     * @return Indique si l'utilisateur doit être accepté.
     */
    protected abstract boolean accepter_utilisateur(final Utilisateur u);

    @Override
    protected final boolean accepter(HttpServletRequest requete, HttpServletResponse reponse)
    {
        try
        {
            final HttpSession session = requete.getSession();
            if (session != null)
            {
                final Utilisateur u = (Utilisateur)session.getAttribute(PARAM_UTILISATEUR);
                if (this.accepter_utilisateur(u)) return true;
            }
        }
        catch (Exception e) { e.printStackTrace(); } // Toute erreur invalide l'authentification.
        return false;
    }

    /** URL du service de connexion (relatif à l'application web). */
    private static final String SERVICE_CONNEXION = "connexion";

    @Override
    protected final void non_acceptee(HttpServletRequest requete, HttpServletResponse reponse)
    {
        final Utilisateur u = (Utilisateur)requete.getSession().getAttribute(PARAM_UTILISATEUR);
        if (u == null) // Redirection vers la page de connexion avec demande de retour vers ce service.
            Service.redirection(SERVICE_CONNEXION, true, requete, reponse);
        // Si l'utilisateur est déjà authentifié, on envoit le code erreur 403 (Forbidden).
        else try { reponse.sendError(403); } catch (IOException e) { e.printStackTrace(); }
    }
}
