package persistance.utilisateurs;

/**
 * Représentation des utilisateurs abonnés.
 */
public class Abonne extends _Utilisateur
{
    /**
     * Construction d'un abonné.
     * @param nom Nom de l'utilisateur.
     * @param age Age de l'utilisateur.
     * @param abonnement_actif Drapeau indiquant si l'abonnement de l'utilisateur est actif.
     */
    public Abonne(final String nom, final Integer age, final Boolean abonnement_actif)
    {
        super(nom, false);
        this.data.add(age);
        this.data.add(abonnement_actif);
    }

    /** Défini l'age à partir du quel on considère un abonné comme adulte. */
    public static final int AGE_ADULTE = 18;
    /** Nom de la colonne de la table SQL associée à l'age de l'utilisateur. */
    public static final String BD_AGE = "age";
    /** Index de l'âge de l'utilisateur dans la liste des informations complémentaires.  */
    private static final int DATA_AGE = 0;
    /**
     * Retourne l'âge de l'utilisateur.
     * @return Âge de l'utilisateur.
     */
    public final Integer age() { return (Integer)this.data.get(DATA_AGE); }

    /** Nom de la colonne de la table SQL associée au drapeau d'activité de l'abonnement. */
    public static final String BD_ABONNEMENT_ACTIF = "abonnement_actif";
    /** Index de l'âge de l'utilisateur dans la liste des informations complémentaires.  */
    private static final int DATA_ABONNEMENT_ACTIF = 1;
    /**
     * Retourne la valeur du drapeau indiquant si l'abonnement de l'utilisateur est actif.
     * @return Drapeau d'activité de l'abonnement.
     */
    public final Boolean abonnement_actif() { return (Boolean)this.data.get(DATA_ABONNEMENT_ACTIF); }
}
