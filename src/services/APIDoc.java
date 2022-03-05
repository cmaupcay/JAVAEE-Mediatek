package services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

/**
 * Classe d'utilitaires relatifs aux documents de la médiathèque.
 * Permet de contourner certaines limites de l'interface mediatek2022.Mediatheque.
 */
public abstract class APIDoc
{
    /**
     * Récupère les métadonnées d'un document depuis sa sérialisation.
     * @param doc Document à décomposer.
     * @return Metadonnées du document.
     */
    public static final String[] meta(Document doc) 
    { 
        final String[] meta = doc.toString().split("//");
        for (int m = 0; m < meta.length; m++)
            if (meta[m].equals("null")) meta[m] = null;
        return meta;
    }
    /**
     * Transforme une liste de documents en une liste de métadonnées.
     * @param docs Liste de documents.
     * @return Liste de métadonnées.
     */
    public static final List<String[]> metas(List<Document> docs)
    {
        final List<String[]> metas = new ArrayList<>();
        for (Document doc : docs) metas.add(meta(doc));
        return metas;
    }

    /** Index de l'identifiant numérique du document. */
    public static final int ID = 0;
    /** Index du type du document. */
    public static final int TYPE = 1;
    /** Index de l'emprunteur actuel du document. */
    public static final int EMPRUNTEUR = 2;
    /** Index du titre du document. */
    public static final int TITRE = 3;
    /** Index de l'auteur du document. */
    public static final int AUTEUR = 4;
    /** Index de l'indicateur ADULTE. */
    public static final int ADULTE = 5;

    /**
     * Ajout des index des métadonnées dans une requête HTTP (utile aux JSP).
     * @param requete Requête HTTP.
     */
    public static final void ajouter_attributs(HttpServletRequest requete)
    {
        requete.setAttribute("ID", ID);
        requete.setAttribute("TYPE", TYPE);
        requete.setAttribute("EMPRUNTEUR", EMPRUNTEUR);
        requete.setAttribute("TITRE", TITRE);
        requete.setAttribute("AUTEUR", AUTEUR);
        requete.setAttribute("ADULTE", ADULTE);
    }

    /**
     * Retourne la liste de tous les documents de la médiathèque, peu importe leur état.
     * ATTENTION : cette méthode est très gourmante et est à éviter.
     * @return Liste de tous les documents de la médiathèque.
     */
    public static final List<Document> tousLesDocuments()
    {
        List<Document> docs = new ArrayList<>();
        int d = 1;
        Document doc = null;
        while ((doc = Mediatheque.getInstance().getDocument(d)) != null)
        {
            docs.add(doc);
            d++;
        }
        return docs;
    }

    /**
     * Test si un utilisateur est l'emprunteur d'un document.
     * @param u Utilisateur à tester.
     * @param meta Métadonnées du document à tester.
     * @return Résultat du test.
     */
    public static final boolean estEmprunteur(final Utilisateur u, final String[] meta)
    {
        if (u == null || meta == null || meta[EMPRUNTEUR] == null) return false; 
        return meta[EMPRUNTEUR].equals(u.name());
    }
    /**
     * Test si un utilisateur est l'emprunteur d'un document.
     * @param u Utilisateur à tester.
     * @param doc Document à tester.
     * @return Résultat du test.
     */
    public static final boolean estEmprunteur(final Utilisateur u, final Document doc) 
    { return estEmprunteur(u, meta(doc)); }

    /**
     * Retourne la liste des documents empruntés par un utilisateur.
     * ATTENTION : cette méthode est très gourmante et est à éviter.
     * @param u Utilisateur cible.
     * @return Liste des documents empruntés actuellement par l'utilisateur.
     */
    public static final List<Document> emprunts(final Utilisateur u)
    {
        List<Document> docs = new ArrayList<>();
        int d = 1;
        Document doc = null;
        while ((doc = Mediatheque.getInstance().getDocument(d)) != null)
        {
            if (estEmprunteur(u, doc)) docs.add(doc);
            d++;
        }
        return docs;
    }
}
