package services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends Service
{
    public Test()
    {
        super("test");
    }

    @Override
    protected boolean GET_accepter(HttpServletRequest requete, HttpServletResponse reponse)
    {
        return true;
    }
    
    @Override
    protected void GET_pre(HttpServletRequest requete, HttpServletResponse reponse)
    {
        requete.setAttribute("documents", MEDIATHEQUE.tousLesDocumentsDisponibles());
    }
    
    @Override
    protected void GET_post(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }
    
    @Override
    protected void GET_PAGE_pre(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }

    @Override
    protected void GET_PAGE_post(HttpServletRequest requete, HttpServletResponse reponse)
    {

    }
}
