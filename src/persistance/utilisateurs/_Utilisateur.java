package persistance.utilisateurs;

import java.util.ArrayList;
import java.util.List;

import mediatek2022.Utilisateur;

public abstract class _Utilisateur implements Utilisateur
{
    public static final String BD_TABLE = "utilisateurs";
    public static final String BD_NOM = "nom";
    public static final String BD_MDP = "mdp";
    public static final String BD_BIBLIOTHECAIRE = "bibliothecaire";

    private final String nom;
    private final boolean bibliothecaire;
    protected final List<Object> data;

    protected _Utilisateur(final String nom, final boolean bibliothecaire)
    {
        this.nom = nom;
        this.bibliothecaire = bibliothecaire;
        this.data = new ArrayList<>();
    }

    @Override
    public final String name() { return this.nom; }

    @Override
    public final boolean isBibliothecaire() { return this.bibliothecaire; }

    @Override
    public final Object[] data() { return this.data.toArray(); } 
}
