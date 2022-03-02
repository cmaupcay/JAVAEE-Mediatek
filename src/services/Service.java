package services;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.*;

public abstract class Service extends HttpServlet
{
    private static final String JSP_DOSSIER = "/WEB-INF/jsp/";
    protected static final String JSP_ENTETE = "modules/entete";
    protected static final String JSP_PIED = "modules/pied";

    private final String jsp;
    public final String jsp() { return this.jsp; }

    protected Service(final String jsp)
    {
        this.jsp = jsp;
    }

    protected static final Mediatheque MEDIATHEQUE = Mediatheque.getInstance();
    private static final String PERSISTANCE_PARAM = "persistance";
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        // Chargement de la classe de persistance de la médiathèque.
        final String persistance = config.getServletContext().getInitParameter(PERSISTANCE_PARAM);
        if (persistance == null) 
            throw new ServletException("Aucune classe de persistance n'est définie pour la médiathèque.");
        else
        {
            try { Class.forName(persistance); }
            catch (ClassNotFoundException e) { e.printStackTrace(); }
        }
    }

    protected abstract boolean GET_accepter(HttpServletRequest requete, HttpServletResponse reponse);
    protected abstract void GET_pre(HttpServletRequest requete, HttpServletResponse reponse);
    protected abstract void GET_post(HttpServletRequest requete, HttpServletResponse reponse);
    protected abstract void GET_PAGE_pre(HttpServletRequest requete, HttpServletResponse reponse);
    protected abstract void GET_PAGE_post(HttpServletRequest requete, HttpServletResponse reponse);
    @Override
    protected void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException
    {
        if (this.GET_accepter(requete, reponse))
        {
            this.GET_pre(requete, reponse);
            this.getServletContext().getRequestDispatcher(JSP_DOSSIER + JSP_ENTETE + ".jsp").include(requete, reponse);
            this.GET_PAGE_pre(requete, reponse);
            this.getServletContext().getRequestDispatcher(JSP_DOSSIER + this.jsp + ".jsp").include(requete, reponse);
            this.GET_PAGE_post(requete, reponse);
            this.getServletContext().getRequestDispatcher(JSP_DOSSIER + JSP_PIED + ".jsp").include(requete, reponse);
            this.GET_post(requete, reponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException
    {
        // TODO POST
    }
}
