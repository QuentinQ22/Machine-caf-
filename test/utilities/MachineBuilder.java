package utilities;

import machineacafe.Machine;

public class MachineBuilder {
    private int _nombreDosesCafé = 2;
    private int _nombreGobelets = 2;
    private int _nombreTouillettes = 2;
    private int _stockInitialSucre = 1;
    private int _stockInitialTouillette = 1;

    public static Machine Default() {
        return new MachineBuilder().Build();
    }

    public Machine Build() {
        var machine = new Machine();

        if (_nombreDosesCafé == 0) {
            machine.Insérer(0.40);
            machine.RéapprovisionnerGobelet();
        }

        if (_nombreGobelets == 0) {
            machine.Insérer(0.40);
            machine.RéapprovisionnerCafé();
        }

        if (_stockInitialSucre == 0) {
            machine.SucrerCafé();
            machine.Insérer(0.40);
            machine.RéapprovisionnerCafé();
            machine.RéapprovisionnerGobelet();
        }
        if (_stockInitialTouillette == 0) {
            machine.SucrerCafé();
            machine.RéapprovisionnerCafé();
            machine.RéapprovisionnerSucre();
        }

        for (var dosesCaféDansLaMachine = 1; dosesCaféDansLaMachine < _nombreDosesCafé; dosesCaféDansLaMachine++)
            machine.RéapprovisionnerCafé();

        for (var gobeletsDansLaMachine = 1; gobeletsDansLaMachine < _nombreGobelets; gobeletsDansLaMachine++)
            machine.RéapprovisionnerGobelet();

        for (var sucreDansLaMachine = 1; sucreDansLaMachine < _stockInitialSucre; sucreDansLaMachine++)
            machine.RéapprovisionnerSucre();

        for (var touillettesDansLaMachine = 1; touillettesDansLaMachine < _stockInitialTouillette; touillettesDansLaMachine++)
            machine.RéapprovisionnerTouillette();

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
}