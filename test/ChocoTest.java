import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChocoTest {
    @Test
    @DisplayName("ETANT DONNÉ une machine à café " +
            "ET un appui sur le bouton choco" +
            "QUAND on insère 40cts " +
            "ALORS un choco coule " +
            "ET une dose de choco est consommée")
    public void Decrementation_Choco(){
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();

        int nombreChocoInitiaux = machine.GetNombreChocoServis();
        int stockChocoInitial = machine.GetStockChoco();

//        ET un appui sur le bouton choco
        machine.Choco();

        // QUAND on insère 40cts
        double sommeInsérée = 0.40;
        machine.Insérer(sommeInsérée);


        // ALORS le choco coule
        int nombreChocoFinaux = machine.GetNombreChocoServis();
        assertEquals(nombreChocoInitiaux + 1, nombreChocoFinaux);

        // ET une dose de choco est consommée
        int stockChocoFinal = machine.GetStockChoco();
        assertEquals(stockChocoInitial - 1, stockChocoFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine " +
            "ET un appui sur le bouton choco " +
            "QUAND on insère 40 cts deux fois" +
            "ALORS un choco et un cafe coulent " +
            "ET une seule dose de choco est consommée")
    public void RaZ_Bouton_Choco(){
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int nombreChocoInitiaux = machine.GetNombreChocoServis();
        int stockChocoInitial = machine.GetStockChoco();

        // ET un appui sur le bouton choco
        machine.Choco();

        // QUAND on insère 40 cts deux fois
        double sommeInsérée = 0.4;
        machine.Insérer(sommeInsérée);
        machine.Insérer(sommeInsérée);

        // ALORS un choco et un cafe coulent
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);
        int nombreChocoFinaux = machine.GetNombreChocoServis();
        assertEquals(nombreChocoInitiaux + 1, nombreChocoFinaux);

        // ET une seule dose de choco est consommée
        int stockChocoFinal = machine.GetStockChoco();
        assertEquals(stockChocoInitial - 1, stockChocoFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de choco " +
            "ET un appui du technicien sur Reappro choco puis sur le bouton choco " +
            "QUAND on insère 40cts 2 fois " +
            "ALORS un seul choco est servi")
    public void TestReapproChoco(){
        // ETANT DONNE une machine n'ayant pas de choco
        var machine = new MachineBuilder().SansChoco().SansSucre().Build();
        int chocoServisInitiaux = machine.GetNombreChocoServis();

        // ET un appui du technicien sur Reappro choco
        machine.RéapprovisionnerChoco();
        machine.Choco();

        // QUAND on insère deux fois 40 cts
        double sommeInsérée = 0.40;
        machine.Insérer(sommeInsérée);
        machine.Insérer(sommeInsérée);

        // ALORS un seul choco est servi
        int chocoServisFinaux = machine.GetNombreChocoServis();
        assertEquals(chocoServisInitiaux + 1, chocoServisFinaux);
    }
}