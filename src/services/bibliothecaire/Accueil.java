package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.api.APIDoc;
import services.base.Service;
import services.base.ServiceBibliothecaire;

/**
 * Service de la page principale de l'espace bibliothécaire.
 */
public class Accueil extends ServiceBibliothecaire
{
    /**
     * Construction du service.
     */
    public Accueil()
    {
        super("bibliothecaire/index");
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
    }

    /** Nom et identifiant du boutton associé à l'action d'affichage de tous les documents. */
    private static final String ACTION_DOCS = "docs";
    /** Nom et identifiant du boutton associé à l'action d'ajout d'un document. */
    private static final String ACTION_AJOUT = "ajouter";

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération des documents disponibles.
        requete.setAttribute(PARAM_DOCUMENTS, APIDoc.metas(APIDoc.tousLesDocuments()));
        requete.setAttribute("ACTION_DOCS", ACTION_DOCS);
        requete.setAttribute("ACTION_AJOUT", ACTION_AJOUT);
    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        String redirection = null;
        String action = requete.getParameter(ACTION_DOCS);
        if (action == null)
        {
            action = requete.getParameter(ACTION_AJOUT);
            if (action != null) redirection = "ajout";
        }
        else redirection = "docs";

        if (redirection != null) Service.redirection("bib/" + redirection, false, requete, reponse);
    }
}
