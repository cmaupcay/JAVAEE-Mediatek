package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import services.api.APIDoc;
import services.base.ServiceAbonne;

/**
 * Service de gestion des emprunts.
 */
public class Emprunts extends ServiceAbonne
{
    /**
     * Construction du service.
     */
    public Emprunts()
    {
        super("emprunts", false); // Un bibliothécaire ne peut emprunter un document.
    }

    @Override
    protected void pre(HttpServletRequest requete, HttpServletResponse reponse) {}

    /** Nom et identifiant du boutton associé à l'action de retour d'un document. */
    private static final String ACTION_RETOURNER = "retourner";

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération de la liste des documents empruntés par l'utilisateur courant.
        final List<String[]> documents = APIDoc.metas(APIDoc.emprunts((Utilisateur)requete.getAttribute(PARAM_UTILISATEUR)));
        requete.setAttribute(PARAM_DOCUMENTS, documents);

        requete.setAttribute("ACTION_RETOURNER", ACTION_RETOURNER);
    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        final String action = requete.getParameter(ACTION_RETOURNER);
        if (action != null)
        {
            String msg = null;
            final String id_doc_s = requete.getParameter(PARAM_DOCUMENT);
            if (id_doc_s == null)
                msg = "Aucun identifiant de document fourni.";
            else
            {
                final Integer id_doc = Integer.parseInt(id_doc_s);
                Document doc = null;
                synchronized(MEDIATHEQUE) 
                {
                    doc = MEDIATHEQUE.getDocument(id_doc);
                    if (doc == null) msg = "Document inexistant.";
                    else
                    {
                        try 
                        { 
                            MEDIATHEQUE.retour(doc, (Utilisateur)requete.getAttribute(PARAM_UTILISATEUR));
                            msg = "Document retourne !";
                        }
                        catch (Exception e) { msg = e.getMessage(); }
                    }
                }
            }
            if (msg != null) requete.setAttribute(PARAM_MSG, msg);
        }
    }
}
