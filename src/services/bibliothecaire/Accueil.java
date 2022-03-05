package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /** Nom et identifiant du boutton associé à l'action d'ajout d'un document. */
    private static final String ACTION_AJOUT = "ajouter";

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération des documents disponibles.
        requete.setAttribute(PARAM_DOCUMENTS, MEDIATHEQUE.tousLesDocumentsDisponibles());
        
        requete.setAttribute("ACTION_AJOUT", ACTION_AJOUT);

    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        final String ajout = requete.getParameter(ACTION_AJOUT);
        if (ajout != null) Service.redirection("bib/ajout", false, requete, reponse);
    }
}
