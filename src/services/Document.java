package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.base.ServiceAbonne;

public class Document extends ServiceAbonne
{
    public Document()
    {
        super("document", true);
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
    }
    @Override
    protected void post(HttpServletRequest requete, HttpServletResponse reponse) {}

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
