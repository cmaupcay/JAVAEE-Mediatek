package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.api.APIDoc;
import services.base.Service;
import services.base.ServiceBibliothecaire;

/**
 * Service de la page principale de l'espace bibliothécaire.
 */
public class Documents extends ServiceBibliothecaire
{
    /**
     * Construction du service.
     */
    public Documents()
    {
        super("bibliothecaire/docs");
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
    }

    /** Nom et identifiant du boutton associé à l'action de retour à l'accueil. */
    private static final String ACTION_RETOUR = "retour";

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        requete.setAttribute("ACTION_RETOUR", ACTION_RETOUR);

        // Récupération des documents disponibles.
        requete.setAttribute(PARAM_DOCUMENTS, APIDoc.metas(APIDoc.tousLesDocuments()));
    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        String action = requete.getParameter(ACTION_RETOUR);
        if (action != null) Service.redirection("bib/", false, requete, reponse);
    }
}
