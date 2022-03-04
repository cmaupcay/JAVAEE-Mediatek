package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération des documents disponibles.
        requete.setAttribute(PARAM_DOCUMENTS, MEDIATHEQUE.tousLesDocumentsDisponibles());
    }
    
    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }
}
