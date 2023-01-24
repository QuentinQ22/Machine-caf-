package utilities;

import net.bank.interop.ModulePrelevementAutomatique;

import java.util.ArrayList;

public class ModulePaiementSpy implements ModulePrelevementAutomatique {
    private final ModulePrelevementAutomatique _spied;

    private final ArrayList<Double> _invocationsPrelever = new ArrayList<>();

    public ModulePaiementSpy(ModulePrelevementAutomatique spied) {
        _spied = spied;
    }

    @Override
    public boolean Prelever(double montantEnEuros) {
        var response = _spied.Prelever(montantEnEuros);
        _invocationsPrelever.add(montantEnEuros);
        return response;
    }

    public boolean AEteAppeleAvecLeMontant(double montant) {
        return _invocationsPrelever.contains(montant);
    }
}
