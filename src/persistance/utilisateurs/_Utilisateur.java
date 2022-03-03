package persistance.utilisateurs;

import java.util.ArrayList;
import java.util.List;

import mediatek2022.Utilisateur;

/**
 * Classe de base des utilisateurs de la médiathèque.
 */
public abstract class _Utilisateur implements Utilisateur
{
    /** Nom de la table SQL associée aux utilisateurs. */
    public static final String BD_TABLE = "utilisateurs";
    /** Nom de la colonne de la table SQL associée au pseudo de l'utilisateur. */
    public static final String BD_NOM = "nom";
    /** Nom de la colonne de la table SQL associée au mot de passe de l'utilisateur. */
    public static final String BD_MDP = "mdp";
    /** Nom de la colonne de la table SQL associée au drapeau du rôle Bibliothécaire. */
    public static final String BD_BIBLIOTHECAIRE = "bibliothecaire";

    /** Nom de l'utilisateur. */
    private final String nom;
    @Override
    public final String name() { return this.nom; }

    /** Drapeau indiquant si l'utilisateur possède le rôle Bibliothécaire. */
    private final boolean bibliothecaire;
    @Override
    public final boolean isBibliothecaire() { return this.bibliothecaire; }
    
    /** Liste des informations complémentaires de l'utilisateur. */
    protected final List<Object> data;
    @Override
    public final Object[] data() { return this.data.toArray(); } 

    /**
     * Construction d'un utilisateur.
     * @param nom Nom de l'utilisateur.
     * @param bibliothecaire Valeur du drapeau de rôle Bibliothécaire.
     */
    protected _Utilisateur(final String nom, final boolean bibliothecaire)
    {
        this.nom = nom;
        this.bibliothecaire = bibliothecaire;
        this.data = new ArrayList<>();
    }
}
