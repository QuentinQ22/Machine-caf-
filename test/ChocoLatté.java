import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChocoLatté {
    @Test
    @DisplayName("ETANT DONNÉ une machine à café" +
            "    ET un appui sur le bouton chocolatté" +
            "    QUAND on met 45 cts" +
            "    ALORS le chocolat coule" +
            "    ET une dose de lait est consommé")
    public void TestChocoLatté(){
        // ETANT DONNÉ une machine à café sur laquelle le bouton choco-lait est appuyé
        Machine machine = MachineBuilder.Default();
        int stockLaitInitial = machine.GetStockLait();
        int nombreChocoInitiaux = machine.GetNombreChocoServis();

        // ET un appui sur le bouton chocolatté
        machine.ChocoLatté();

        // QUAND on met 45 cts
        double sommeInserée = 0.45;
        machine.Insérer(sommeInserée);

        // ALORS le choco coule
        int nombreChocoFinaux = machine.GetNombreChocoServis();
        assertEquals(nombreChocoInitiaux + 1, nombreChocoFinaux);

        // ET une dose de lait est consommée
        int stockLaitFinal = machine.GetStockLait();
        assertEquals(stockLaitInitial - 1, stockLaitFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de chocolat" +
            "ET on appui sur le boutton choco-lait" +
            "QUAND on met 45 cts" +
            "ALORS aucun choco ne coule" +
            "ET l'argent est rendu")
    public void Test_Sans_ChocoLait(){
//        ETANT DONNE une machine n'ayant pas de chocolat
        Machine machine = new MachineBuilder().SansChoco().Build();

        int nombreChocoInitiaux = machine.GetNombreChocoServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

//        ET on appui sur le boutton chocolatté
        machine.ChocoLatté();

//        QUAND on met 45 cts
        machine.Insérer(0.45);

//        ALORS aucun choco ne coule
        int nombreChocoFinaux = machine.GetNombreChocoServis();
        assertEquals(nombreChocoInitiaux, nombreChocoFinaux);

//        ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }
}