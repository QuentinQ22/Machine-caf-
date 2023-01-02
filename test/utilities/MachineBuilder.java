package utilities;

import machineacafe.Machine;

public class MachineBuilder {
    private boolean _possèdeCafé = true;
    private boolean _possèdeGobelets = true;

    public static Machine Default() {
        return new MachineBuilder().Build();
    }

    public Machine Build()
    {
        return new Machine(_possèdeGobelets ? 1 : 0, _possèdeCafé ? 1 : 0);
    }

    public MachineBuilder SansGobelets() {
        _possèdeGobelets = false;
        return this;
    }

    public MachineBuilder SansCafé() {
        _possèdeCafé = false;
        return this;
    }
}
