package utilities;

import machineacafe.*;
import lib.net.bank.interop.ModulePrelevementAutomatique;

public class MachineBuilder {
    private int _nombreDosesCafé = 2;
    private int _nombreGobelets = 2;
    private int _nombreTouillettes = 2;
    private int _nombreDosesLait = 1;
    private int _nombreDosesChoco = 1;
    private int _stockInitialSucre = 10;
    private int _stockInitialTouillette = 1;
    private ModulePrelevementAutomatique _modulePrélèvementAutomatique = null;

    public static Machine Default() {
        return new MachineBuilder().Build();
    }

    public Machine Build()
    {
        var machine = _modulePrélèvementAutomatique == null
                ? new Machine()
                : new Machine(_modulePrélèvementAutomatique);

        if (_nombreDosesCafé == 0) {
            machine.Insérer(0.40);
            machine.RéapprovisionnerGobelet();
        }

        if (_nombreGobelets == 0) {
            machine.Insérer(0.40);
            machine.RéapprovisionnerCafé();
        }

        if (_stockInitialSucre == 0) {
            machine.SucrerCafé(1);
            machine.Insérer(0.40);
            machine.RéapprovisionnerCafé();
            machine.RéapprovisionnerGobelet();
        }
        if (_stockInitialTouillette == 0) {
            machine.SucrerCafé(1);
            machine.RéapprovisionnerCafé();
            machine.RéapprovisionnerSucre();
        }

        if (_nombreDosesChoco == 0){
            machine.Choco();
            machine.Insérer(0.4);
            machine.RéapprovisionnerGobelet();
        }

        if (_nombreDosesLait == 0){
            machine.LattéCafé();
            machine.Insérer(0.45);
            machine.RéapprovisionnerCafé();
            machine.RéapprovisionnerGobelet();
        }

        for (var dosesCaféDansLaMachine = 1; dosesCaféDansLaMachine < _nombreDosesCafé; dosesCaféDansLaMachine++)
            machine.RéapprovisionnerCafé();

        for (var gobeletsDansLaMachine = 1; gobeletsDansLaMachine < _nombreGobelets; gobeletsDansLaMachine++)
            machine.RéapprovisionnerGobelet();

        for (var sucreDansLaMachine = 1; sucreDansLaMachine < _stockInitialSucre; sucreDansLaMachine++)
            machine.RéapprovisionnerSucre();

        for (var touillettesDansLaMachine = 1; touillettesDansLaMachine < _stockInitialTouillette; touillettesDansLaMachine++)
            machine.RéapprovisionnerTouillette();

        for (var laitDansLaMachine = 1; laitDansLaMachine < _nombreDosesLait; laitDansLaMachine ++)
            machine.RéapprovisionnerLait();

        for (var chocoDansLaMachine = 1; chocoDansLaMachine < _nombreDosesChoco; chocoDansLaMachine ++)
            machine.RéapprovisionnerChoco();

        return machine;
    }

    public MachineBuilder SansGobelets() {
        return AyantXGobelets(0);
    }

    public MachineBuilder SansCafé() {
        return AyantYDosesDeCafé(0);
    }

    public MachineBuilder AyantXGobelets(int x) {
        _nombreGobelets = x;
        return this;
    }

    public MachineBuilder AyantYDosesDeCafé(int y) {
        _nombreDosesCafé = y;
        return this;
    }

    public MachineBuilder SansSucre() {
        _stockInitialSucre = 0;
        return this;
    }

    public MachineBuilder SansTouillette() {
        _stockInitialTouillette = 0;
        return this;
    }

    public MachineBuilder SansLait() {
        _nombreDosesLait = 0;
        return this;
    }

    public MachineBuilder SansChoco() {
        _nombreDosesChoco = 0;
        return this;
    }
    public MachineBuilder AyantUnModuleDePaiement(ModulePrelevementAutomatique modulePaiement) {
        _modulePrélèvementAutomatique = modulePaiement;
        return this;
    }
}