import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PénuriesTest {
    @Test
    @DisplayName("ETANT DONNE une machine n'ayant plus d'eau " +
            "QUAND on met 40cts " +
            "ALORS aucun café ne coule " +
            "ET l'argent est rendu")
    public void Test_Sans_Eau(){
        // ETANT DONNE une machine n'ayant plus d'eau
        Machine machine = MachineBuilder.Default();
        machine.CouperEau();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

        // QUAND on met 40cts
        machine.Insérer(0.40);

        // ALORS aucun café ne coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux, nombreCafésFinaux);

        // ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de gobelets " +
            "QUAND on met 40cts " +
            "ALORS aucun café ne coule " +
            "ET l'argent est rendu")
    public void Test_Sans_Gobelet(){
        // ETANT DONNE une machine n'ayant pas de gobelets
        Machine machine = new MachineBuilder()
                .SansGobelets()
                .Build();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

        // QUAND on met 40cts
        machine.Insérer(0.40);

        // ALORS aucun café ne coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux, nombreCafésFinaux);

        // ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de café " +
            "QUAND on met 40cts " +
            "ALORS aucun café ne coule " +
            "ET l'argent est rendu")
    public void Test_Sans_Café(){
        // ETANT DONNE une machine n'ayant pas de café
        Machine machine = new MachineBuilder()
                .SansCafé()
                .Build();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

        // QUAND on met 40cts
        machine.Insérer(0.40);

        // ALORS aucun café ne coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux, nombreCafésFinaux);

        // ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }
}
