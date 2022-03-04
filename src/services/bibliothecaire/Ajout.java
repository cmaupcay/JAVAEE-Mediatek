package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.base.ServiceBibliothecaire;

/**
 * Service d'ajout d'un document.
 */
public class Ajout extends ServiceBibliothecaire
{
    /**
     * Construction du service.
     */
    public Ajout()
    {
        super("bibliothecaire/ajout");
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) 
    {
    }

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }

    @Override
    protected void pre_contenu(HttpServletRequest requete, HttpServletResponse reponse)
    {
    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        // TODO Ajout d'un document
    }
}
