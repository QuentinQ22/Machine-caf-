package utilities;

import machineacafe.Machine;

public class MachineBuilder {
    private int _nombreDosesCafé = 1;
    private int _nombreGobelets = 1;

    public static Machine Default() {
        return new MachineBuilder().Build();
    }

    public Machine Build()
    {
        return new Machine(_nombreGobelets, _nombreDosesCafé);
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
}