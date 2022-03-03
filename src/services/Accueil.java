package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.base.Service;

public class Accueil extends Service
{
    public Accueil()
    {
        super("index");
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
    }
    @Override
    protected void post(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected boolean accepter(HttpServletRequest requete, HttpServletResponse reponse) { return true; }
    @Override
    protected void non_acceptee(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) {}
    @Override
    protected void post_page(HttpServletRequest requete, HttpServletResponse reponse) {}
    
    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }
    @Override
    protected void post_contenu(HttpServletRequest requete, HttpServletResponse reponse) {}
}
