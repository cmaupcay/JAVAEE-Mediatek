package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Utilisateur;
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

    @Override
    protected void pre_page(HttpServletRequest requete, HttpServletResponse reponse) 
    {
        // Récupération de la liste des documents empruntés par l'utilisateur courant.
        final List<String[]> documents = APIDoc.metas(APIDoc.emprunts((Utilisateur)requete.getAttribute(PARAM_UTILISATEUR)));
        requete.setAttribute(PARAM_DOCUMENTS, documents);
    }
}
