import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StocksTest {
    @Test
    @DisplayName("ETANT DONNE une machine ayant un seul gobelet " +
            "QUAND on insère deux fois 40 cts " +
            "ALORS seul un café coule " +
            "ET l'argent du second café est rendu")
    public void Decrementation_Gobelets(){
        // ETANT DONNE une machine ayant un seul gobelet
        Machine machine = new MachineBuilder()
                .AyantXGobelets(1)
                .Build();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();
        double sommeInsérée = 0.40;

        // QUAND on insère deux fois 40 cts
        machine.Insérer(sommeInsérée);
        machine.Insérer(sommeInsérée);

        // ALORS seul un café coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET l'argent du second café est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial + sommeInsérée, argentEncaisséFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine ayant une seule dose de café " +
            "QUAND on insère deux fois 40 cts " +
            "ALORS seul un café coule " +
            "ET l'argent du second café est rendu")
    public void Decrementation_Café(){
        // ETANT DONNE une machine ayant une seule dose de café
        Machine machine = new MachineBuilder()
                .AyantYDosesDeCafé(1)
                .Build();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();
        double sommeInsérée = 0.40;

        // QUAND on insère deux fois 40 cts
        machine.Insérer(sommeInsérée);
        machine.Insérer(sommeInsérée);

        // ALORS seul un café coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET l'argent du second café est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial + sommeInsérée, argentEncaisséFinal);
    }
}