package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.Utilisateur;
import services.base.Service;
import services.base.ServiceAuthentification;

public class Connexion extends Service
{
    public Connexion()
    {
        super("connexion");
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Si une redirection est demandée, on l'enregistre en session.
        final String redirection = requete.getParameter(PARAM_REDIRECTION);
        if (redirection != null) 
        {
            final HttpSession session = requete.getSession();
            session.setAttribute(PARAM_REDIRECTION, redirection);
        }
    }
    @Override
    protected void post(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected boolean accepter(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        final HttpSession session = requete.getSession();
        final Utilisateur u = (Utilisateur)session.getAttribute(ServiceAuthentification.UTILISATEUR);
        return u == null; // Si l'utilisateur est connecté, on ne l'accepte pas.
    }
    @Override
    protected void non_acceptee(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        final HttpSession session = requete.getSession();
        String redirection = (String)session.getAttribute(PARAM_REDIRECTION);
        if (redirection == null) redirection = "";          // Si aucune redirection définie, on redirige vers l'accueil.
        else session.removeAttribute(PARAM_REDIRECTION);    // Sinon, on supprime de la session la redirection définie.
        Service.redirection(redirection, requete, reponse);
    }

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) {}
    @Override
    protected void post_page(HttpServletRequest requete, HttpServletResponse reponse) {}
    
    private static final String PARAM_POST_NOM = "nom";
    private static final String PARAM_POST_MDP = "mdp";
    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {
        // Les noms et identifiants des champs du formulaires sont définis dans le service et envoyé via la requête.
        requete.setAttribute("PARAM_POST_NOM", PARAM_POST_NOM);
        requete.setAttribute("PARAM_POST_MDP", PARAM_POST_MDP);
    }
    @Override
    protected void post_contenu(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        String message = "";
        // Récupération des informations de connexion.
        final String nom = requete.getParameter(PARAM_POST_NOM);
        final String mdp = requete.getParameter(PARAM_POST_MDP);
        if (nom != null && mdp != null)
        {
            // Authentification.
            final Utilisateur u = MEDIATHEQUE.getUser(nom, mdp);
            if (u != null)
            {
                // Enregistrement de l'utilisateur en sesssion.
                final HttpSession session = requete.getSession();
                session.setAttribute(ServiceAuthentification.UTILISATEUR, u);
            }
            else message = "Identifiants incorrects";
        }
        else message = "Informations manquantes.";
        requete.setAttribute(PARAM_MSG, message);
    }
}
