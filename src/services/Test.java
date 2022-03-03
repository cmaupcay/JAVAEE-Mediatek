package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Utilisateur;
import services.base.ServiceAbonne;

public class Test extends ServiceAbonne
{
    public Test()
    {
        super("test", true);
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }
    
    @Override
    protected void post(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }
    
    @Override
    protected void post_page(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }
    
    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {
        requete.setAttribute("DOCUMENTS", MEDIATHEQUE.tousLesDocumentsDisponibles());
        requete.setAttribute("UTILISATEUR", ((Utilisateur)requete.getSession().getAttribute(UTILISATEUR)).name());
    }

    @Override
    protected void post_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }
}
