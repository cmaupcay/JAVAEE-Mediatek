package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.base.ServiceBibliothecaire;

public class Ajout extends ServiceBibliothecaire
{
    public Ajout()
    {
        super("bibliothecaire/ajout");
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
