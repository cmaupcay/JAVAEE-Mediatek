package services.base;

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

    /** Paramètre d'envoie de message, surtout pour du servlet vers le JSP via les attributs de la requête. */
    protected static final String PARAM_MSG = "msg";
    /** Paramètre de demande de redirection. */
    protected static final String PARAM_REDIRECTION = "redirect";
    protected static void redirection(final String service, final boolean demander_retour, HttpServletRequest requete, HttpServletResponse reponse)
    {
        String fin = "";
        if (demander_retour)
        {
            fin = '?' + PARAM_REDIRECTION + '=' + requete.getServletPath().substring(1);
            final String query = requete.getQueryString();
            if (query != null) fin += '?' + query;
        }
        try { reponse.sendRedirect(requete.getContextPath() + '/' + service + fin); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /** Paramètre indiquant aux JSP la racine de la plateforme web (utile pour les liens). */
    private static final String PARAM_RACINE = "RACINE";
    /** Paramètre indiquant aux JSP la page web actuelle (utile pour les liens). */
    private static final String PARAM_PAGE = "PAGE";

    /** Paramètre standard de transfert d'un document entre les objets (servlet, JSP, session). */
    protected static final String PARAM_DOCUMENT = "doc";
    /** Paramètre standard de transfert de documents entre les objets (servlet, JSP, session). */
    protected static final String PARAM_DOCUMENTS = "docs";

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
    protected abstract boolean accepter(HttpServletRequest requete, HttpServletResponse reponse);
    /**
     * Fonction de redirection des requêtes non acceptée par la fonction accepter().
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void non_acceptee(HttpServletRequest requete, HttpServletResponse reponse);

    /**
     * Fonction de pré-chargement du service, avant l'acceptation.
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void pre(HttpServletRequest requete, HttpServletResponse reponse);
    /**
     * Fonction de pré-chargement de la page.
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected abstract void pre_page(HttpServletRequest requete, HttpServletResponse reponse);

    private final void proceder(HttpServletRequest requete, HttpServletResponse reponse, final boolean post) throws ServletException, IOException
    {
        requete.getSession(true); // On créer la session si elle n'existe pas.     
        this.pre(requete, reponse);
        if (post) this.POST(requete, reponse); // La méthode POST est exécutée avant l'acceptation de la requête.
        if (this.accepter(requete, reponse))
        {
            requete.setAttribute(PARAM_RACINE, requete.getContextPath()); // Définition de la racine de la plateforme web pour les JSP.
            requete.setAttribute(PARAM_PAGE, requete.getServletPath().substring(1)); // Définition de la page web actuelle pour les JSP.

            this.pre_page(requete, reponse);
            this.getServletContext().getRequestDispatcher(JSP_DOSSIER + this.jsp + ".jsp").include(requete, reponse);
        }
        else this.non_acceptee(requete, reponse);
    }

    @Override
    protected final void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException
    { proceder(requete, reponse, false); }

    /**
     * Fonction de délégation d'une requête POST.
     * @param requete Requête HTTP.
     * @param reponse Réponse HTTP.
     */
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    { try { reponse.sendError(405); } catch (IOException e) { e.printStackTrace(); } }

    @Override
    protected final void doPost(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException
    { proceder(requete, reponse, true); }
}