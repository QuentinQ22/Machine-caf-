import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaitTest {
    @Test
    @DisplayName("ETANT DONNE une machine " +
            "ET un appui sur le bouton Latté " +
            "QUAND on insère 45 cts " +
            "ALORS un café latté coule " +
            "ET une dose de lait est consommée")
    public void Decrementation_Lait(){
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int stockLaitInitial = machine.GetStockLait();

        // ET un appui sur le bouton latté
        machine.LattéCafé();

        double sommeInsérée = 0.45;

        // QUAND on insère 45 cts
        machine.Insérer(sommeInsérée);

        // ALORS le café coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET une dose de lait est consommée
        int stockLaitFinal = machine.GetStockLait();
        assertEquals(stockLaitInitial - 1, stockLaitFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine " +
            "ET un appui sur le bouton latté " +
            "QUAND on insère 45 cts deux fois" +
            "ALORS deux cafés coulent " +
            "ET une seule dose de Lait est consommée")
    public void RaZ_Bouton_Lait(){
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int stockLaitInitial = machine.GetStockLait();

        // ET un appui sur le bouton latté
        machine.LattéCafé();

        // QUAND on insère 45 cts deux fois
        double sommeInsérée = 0.45;
        machine.Insérer(sommeInsérée);
        machine.Insérer(sommeInsérée);

        // ALORS deux cafés coulent
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 2, nombreCafésFinaux);

        // ET une dose de lait est consommée
        int stockLaitFinal = machine.GetStockLait();
        assertEquals(stockLaitInitial - 1, stockLaitFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de lait " +
            "ET un appui du technicien sur Reappro lait " +
            "QUAND on insère 45cts 2 fois en ayant appuyé sur latter Café au préalable" +
            "ALORS un seul café est servi")
    public void TestReapproLait(){
        // ETANT DONNE une machine n'ayant pas de sucre
        var machine = new MachineBuilder().SansLait().Build();
        int cafésServisInitiaux = machine.GetNombreCafésServis();

        // ET un appui du technicien sur Reappro lait
        machine.RéapprovisionnerLait();

        // QUAND on insère 45cts 2 fois en ayant appuyé sur latter Café au préalable
        double sommeInsérée = 0.45;

        machine.LattéCafé();
        machine.Insérer(sommeInsérée);

        machine.LattéCafé();
        machine.Insérer(sommeInsérée);

        // ALORS un seul café est servi
        int cafésServisFinaux = machine.GetNombreCafésServis();
        assertEquals(cafésServisInitiaux + 1, cafésServisFinaux);
    }
}