package persistance.utilisateurs;

public class Abonne extends _Utilisateur
{
    public Abonne(final String nom, final Integer age, final Boolean abonnement_actif)
    {
        super(nom, false);
        this.data.add(age);
        this.data.add(abonnement_actif);
    }

    public static final String BD_AGE = "age";
    private static final int DATA_AGE = 0;
    public final Integer age() { return (Integer)this.data.get(DATA_AGE); }

    public static final String BD_ABONNEMENT_ACTIF = "abonnement_actif";
    private static final int DATA_ABONNEMENT_ACTIF = 1;
    public final Boolean abonnement_actif() { return (Boolean)this.data.get(DATA_ABONNEMENT_ACTIF); }
}
