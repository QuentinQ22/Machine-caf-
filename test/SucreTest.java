import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SucreTest {
    @Test
    @DisplayName("ETANT DONNE une machine " +
            "ET un appui sur le bouton sucre " +
            "QUAND on insère 40 cts " +
            "ALORS un café coule " +
            "ET une dose de sucre est consommée")
    public void Decrementation_Café() {
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int stockSucreInitial = machine.GetStockSucre();

        // ET un appui sur le bouton sucre
        machine.SucrerCafé(1);

        double sommeInsérée = 0.40;

        // QUAND on insère 40 cts
        machine.Insérer(sommeInsérée);

        // ALORS le café coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET une dose de sucre est consommée
        int stockSucreFinal = machine.GetStockSucre();
        assertEquals(stockSucreInitial - 1, stockSucreFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine " +
            "ET un appui sur le bouton sucre " +
            "QUAND on insère 40 cts deux fois" +
            "ALORS deux cafés coulent " +
            "ET une seule dose de sucre est consommée")
    public void RaZ_Bouton_Sucre() {
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int stockSucreInitial = machine.GetStockSucre();

        // ET un appui sur le bouton sucre
        machine.SucrerCafé(1);

        // QUAND on insère 40 cts deux fois
        double sommeInsérée = 0.40;
        machine.Insérer(sommeInsérée);
        machine.Insérer(sommeInsérée);

        // ALORS deux cafés coulent
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 2, nombreCafésFinaux);

        // ET une dose de sucre est consommée
        int stockSucreFinal = machine.GetStockSucre();
        assertEquals(stockSucreInitial - 1, stockSucreFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine " +
            "ET un appui sur le bouton sucre avec le nombre de dose de sucre à choisir" +
            "QUAND on insère 40 cts" +
            "ALORS un café coule " +
            "ET la dose de sucre consommée correspond à celle choisit par l'utilisateur")
    public void Ajout_Quantite_Sucre() {
        // ETANT DONNE une machine
        int doseDeSucre = 3;
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int stockSucreInitial = machine.GetStockSucre();

        // ET un appui sur le bouton sucre avec le nombre de dose de sucre à choisir
        machine.SucrerCafé(doseDeSucre);

        // QUAND on insère 40 cts
        double sommeInsérée = 0.40;
        machine.Insérer(sommeInsérée);

        // ALORS un café coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET la dose de sucre est consommée
        int stockSucreFinal = machine.GetStockSucre();
        assertEquals(stockSucreInitial - doseDeSucre, stockSucreFinal);

    }
}