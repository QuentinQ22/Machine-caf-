package utilities;

import machineacafe.Machine;

public class MachineBuilder {
    private int _nombreDosesCafé = 2;
    private int _nombreGobelets = 2;
    private int _stockInitialSucre = 1;

    public static Machine Default() {
        return new MachineBuilder().Build();
    }

    public Machine Build()
    {
        var machine = new Machine();

        if(_nombreDosesCafé == 0){
            machine.Insérer(0.40);
            machine.RéapprovisionnerGobelet();
        }

        if(_nombreGobelets == 0){
            machine.Insérer(0.40);
            machine.RéapprovisionnerCafé();
        }

        if(_stockInitialSucre == 0){
            machine.SucrerCafé();
            machine.Insérer(0.40);
            machine.RéapprovisionnerCafé();
            machine.RéapprovisionnerGobelet();
        }

        for (var dosesCaféDansLaMachine = 1; dosesCaféDansLaMachine < _nombreDosesCafé; dosesCaféDansLaMachine ++)
            machine.RéapprovisionnerCafé();

        for (var gobeletsDansLaMachine = 1; gobeletsDansLaMachine < _nombreGobelets; gobeletsDansLaMachine ++)
            machine.RéapprovisionnerGobelet();

        for (var sucreDansLaMachine = 1; sucreDansLaMachine < _stockInitialSucre; sucreDansLaMachine ++)
            machine.RéapprovisionnerSucre();

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
}