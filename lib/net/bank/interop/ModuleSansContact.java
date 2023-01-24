package net.bank.interop;

import java.util.Random;

public class ModuleSansContact implements ModulePrelevementAutomatique {

    // Fake destiné à simuler un faux module de paiement échouant 10% du temps.
    // Il est évident d'un vrai module de paiement installé à l'EPSI échoue bien plus fréquemment.
    @Override
    public boolean Prelever(double montantEnEuros) {
        return new Random().nextInt(10) == 0;
    }
}
