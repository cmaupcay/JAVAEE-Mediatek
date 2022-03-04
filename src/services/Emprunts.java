package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.base.ServiceAbonne;

/**
 * Service de gestion des emprunts.
 */
public class Emprunts extends ServiceAbonne
{
    /**
     * Construction du service.
     */
    public Emprunts()
    {
        super("emprunts", false); // Un bibliothécaire ne peut emprunter un document.
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // TODO Trouver comment récupérer les documents empruntés par l'utilisateur.
    }
    
    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }
}
