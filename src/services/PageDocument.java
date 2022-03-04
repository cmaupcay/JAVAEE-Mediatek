package services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Document;
import services.base.Service;
import services.base.ServiceAbonne;

/**
 * Service d'affichage d'un document.
 */
public class PageDocument extends ServiceAbonne
{
    /**
     * Construction du service.
     */
    public PageDocument()
    {
        super("document", true);
    }

    /** Paramètre définissant l'identifiant numérique du document à afficher. */
    private static final String PARAM_ID = "id";

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) {}

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération de l'identifiant numérique du document depuis l'URL (GET).
        final String id_s = requete.getParameter(PARAM_ID);
        if (id_s != null)
        {
            try
            {
                final int id = Integer.parseInt(id_s);
                // Récupération dans la médiathèque depuis l'identifiant.
                final Document doc = MEDIATHEQUE.getDocument(id);
                if (doc != null)
                {
                    // Enregistrement en tant qu'attribut de requête.
                    requete.setAttribute(PARAM_DOCUMENT, doc);
                    return;
                }
            }
            catch (NumberFormatException e) { e.printStackTrace(); }
            // Si impossible de retrouver le document, erreur 404.
            try { reponse.sendError(404, "Document introuvable."); }
            catch (IOException e) { e.printStackTrace(); }
        }
        else Service.redirection("", false, requete, reponse);
    }
}
