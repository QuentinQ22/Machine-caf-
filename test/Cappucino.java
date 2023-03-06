import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Cappucino {
    @Test
    @DisplayName("ETANT DONNÉ une machine à café sur laquelle le bouton cappuccino est appuyé" +
            "QUAND on met 50cts" +
            "ALORS le cappucino coule" +
            "ET une dose de lait et de chocolat sont consommé")
    public void Decrementation_Lait() {
        // ETANT DONNE une machine
        Machine machine = MachineBuilder.Default();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        int stockLaitInitial = machine.GetStockLait();
        int stockChocoInitial = machine.GetStockChoco();

        // ET un appui sur le bouton cappucino
        machine.Cappucino();

        double sommeInsérée = 0.50;

        // QUAND on insère 50 cts
        machine.Insérer(sommeInsérée);

        // ALORS le cappucino coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET une dose de lait et de choco sont consommée
        int stockLaitFinal = machine.GetStockLait();
        assertEquals(stockLaitInitial - 1, stockLaitFinal);
        int stockChocoFinal = machine.GetStockChoco();
        assertEquals(stockChocoInitial - 1, stockChocoFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de Lait " +
            "ET un appui sur le bouton cappuccino " +
            "QUAND on met 50cts " +
            "ALORS aucun café ne coule " +
            "ET l'argent est rendu")
    public void Test_Sans_Cappucino(){
        // ETANT DONNE une machine n'ayant plus de lait
        Machine machine = new MachineBuilder().SansLait().Build();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

        // ET un appui sur le bouton latté
        machine.Cappucino();

        // QUAND on insère 45 cts
        machine.Insérer(0.5);

        // ALORS aucun café ne coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux, nombreCafésFinaux);

        // ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }
}