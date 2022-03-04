package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.base.Service;
import services.base.ServiceAbonne;

/**
 * Service de déconnexion.
 */
public class Deconnexion extends ServiceAbonne
{
    /**
     * Construction du service.
     */
    public Deconnexion()
    {
        super("connexion", true); // Peu importe le JSP, la redirection est directe.
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Redirection, par défaut vers l'accueil.
        String redirection = requete.getParameter(PARAM_REDIRECTION);
        if (redirection == null) redirection = "";
        requete.setAttribute(PARAM_REDIRECTION, redirection);
    }

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Suppression de l'authentification.
        requete.getSession().removeAttribute(PARAM_UTILISATEUR);
        // Redirection vers la page demandée.
        Service.redirection((String)requete.getAttribute(PARAM_REDIRECTION), false, requete, reponse);
    }
}
