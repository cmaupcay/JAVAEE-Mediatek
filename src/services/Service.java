package services;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.*;

/**
 * Classe de base des services de la médiathèque.
 */
public abstract class Service extends HttpServlet
{
    /** Chemin du dossier des fichiers JSP. */
    private static final String JSP_DOSSIER = "/WEB-INF/jsp/";
    /** Fichier JSP d'entête, inclus avant chaque page JSP. */
    protected static final String JSP_ENTETE = "modules/entete";
    /** Fichier JSP de pied de page, inclus après chaque page JSP. */
    protected static final String JSP_PIED = "modules/pied";

    /** Nom du fichier JSP associé au service. */
    private final String jsp;
    /**
     * Retourne le nom du fichier JSP associé au service.
     * @return Nom du fichier JSP, sans l'extension de fichier.
     */
    public final String jsp() { return this.jsp; }

    /**
     * Construction d'un service.
     * @param jsp Nom du fichier JSP associé au service, sans l'extension de fichier.
     */
    protected Service(final String jsp)
    {
        this.jsp = jsp;
    }

    /** Référence à l'instance unique de la médiathèque (raccourci). */
    protected static final Mediatheque MEDIATHEQUE = Mediatheque.getInstance();
    /** Nom du paramètre Tomcat indiquant la classe de persistance des données à utiliser. */
    private static final String PERSISTANCE_PARAM = "persistance";
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        // Chargement de la classe de persistance de la médiathèque.
        final String persistance = this.getServletContext().getInitParameter(PERSISTANCE_PARAM);
        if (persistance == null) 
            throw new ServletException("Aucune classe de persistance n'est définie pour la médiathèque.");
        else
        {
            // Chargement de la classe de persistance des données.
            try { Class.forName(persistance); }
            catch (ClassNotFoundException e) { e.printStackTrace(); }
        }
    }

    /**
     * Fonction d'acceptation des requêtes HTTP par le service.
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     * @return Indique si la requête est acceptée.
     */
    protected abstract boolean GET_accepter(HttpServletRequest requete, HttpServletResponse reponse);
    /**
     * Fonction de pré-chargement du service.
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void GET_pre(HttpServletRequest requete, HttpServletResponse reponse);
    /**
     * Fonction de post-chargement du service.
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void GET_post(HttpServletRequest requete, HttpServletResponse reponse);
    /**
     * Fonction de pré-chargement de la page (après le chargement de l'entête).
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void GET_PAGE_pre(HttpServletRequest requete, HttpServletResponse reponse);
    /**
     * Fonction de post-chargement de la page (après le chargement du fichier JSP associé au service).
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void GET_PAGE_post(HttpServletRequest requete, HttpServletResponse reponse);
    
    @Override
    protected final void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException
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
}