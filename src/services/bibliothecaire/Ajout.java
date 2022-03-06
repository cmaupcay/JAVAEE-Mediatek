package services.bibliothecaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.api.APIDoc;
import services.base.Service;
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

    /** Nom et identifiant du boutton associé à l'action de retour à l'accueil. */
    private static final String ACTION_RETOUR = "retour";
    /** Nom et identifiant du boutton associé à l'action d'ajout d'un document. */
    private static final String ACTION_AJOUT = "ajouter";

    /** Nom et identifiant du champs associé au type du document. */
    private static final String PARAM_POST_TYPE = "type";
    /** Nom et identifiant du champs associé au titre du document. */
    private static final String PARAM_POST_TITRE = "titre";
    /** Nom et identifiant du champs associé à l'auteur du document. */
    private static final String PARAM_POST_AUTEUR = "auteur";
    /** Nom et identifiant du champs associé à l'age requis. */
    private static final String PARAM_POST_ADULTE = "adulte";
    
    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse)
    {
        requete.setAttribute("ACTION_AJOUT", ACTION_AJOUT);
        requete.setAttribute("ACTION_RETOUR", ACTION_RETOUR);

        requete.setAttribute("PARAM_POST_TYPE", PARAM_POST_TYPE);
        requete.setAttribute("PARAM_POST_TITRE", PARAM_POST_TITRE);
        requete.setAttribute("PARAM_POST_AUTEUR", PARAM_POST_AUTEUR);
        requete.setAttribute("PARAM_POST_ADULTE", PARAM_POST_ADULTE);

    }

    @Override
    protected void POST(HttpServletRequest requete, HttpServletResponse reponse)
    {
        String action = requete.getParameter(ACTION_RETOUR);
        if (action == null) 
        {
            action = requete.getParameter(ACTION_AJOUT);
            if (action != null)
            {
                String msg = null;
                try
                {
                    final int type = Integer.parseInt(requete.getParameter(PARAM_POST_TYPE));
                    final String titre = requete.getParameter(PARAM_POST_TITRE);
                    final String auteur = requete.getParameter(PARAM_POST_AUTEUR);
                    final String adulte_s = requete.getParameter(PARAM_POST_ADULTE);
                    if (titre != null && auteur != null)
                    {
                        final Boolean adulte = (adulte_s == null ? false : adulte_s.equals("on"));
                        MEDIATHEQUE.ajoutDocument(type, titre, auteur, adulte);
                        msg = "Document ajoute !"; // TOFIX Problème d'affichage des caractères spéciaux (é).
                    }
                    else msg = "Informations manquantes...";
                }
                catch (Exception e) { msg = e.getMessage(); } // Toute erreur annule l'ajout.
                if (msg != null) requete.setAttribute(PARAM_MSG, msg);
            }
        }
        else Service.redirection("bib/", false, requete, reponse);
    }
}
