package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import services.base.Service;
import services.base.ServiceAuthentification;

/**
 * Service de la page principale.
 */
public class Accueil extends Service
{
    /**
     * Construction du service.
     */
    public Accueil()
    {
        super("index");
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected boolean accepter(HttpServletRequest requete, HttpServletResponse reponse) { return true; }
    @Override
    protected void non_acceptee(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération de la liste des documents diponibles.
        final List<Document> documents = MEDIATHEQUE.tousLesDocumentsDisponibles();
        requete.setAttribute(PARAM_DOCUMENTS, documents);

        // Récupération de l'utilisateur courant.
        final Utilisateur utilisateur = (Utilisateur)requete.getSession().getAttribute(ServiceAuthentification.PARAM_UTILISATEUR);
        if (utilisateur != null) requete.setAttribute(ServiceAuthentification.PARAM_UTILISATEUR, utilisateur);
    }
    
    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse) {}
}
