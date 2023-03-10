import machineacafe.Machine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PénuriesTest {
        @Test
        @DisplayName("ETANT DONNE une machine n'ayant plus d'eau en etant sur le réseau" +
                        "QUAND on met 40cts " +
                        "ALORS aucun café ne coule " +
                        "ET l'argent est rendu")
        public void Test_Sans_Eau_reseau() {
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
        @DisplayName("ETANT DONNE une machine n'ayant plus d'eau ayant un stock" +
                        "QUAND on met 40cts " +
                        "ALORS aucun café ne coule " +
                        "ET l'argent est rendu")
        public void Test_Sans_Eau_stock() {
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
        public void Test_Sans_Gobelet() {
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
        public void Test_Sans_Café() {
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

        @Test
        @DisplayName("ETANT DONNE une machine n'ayant pas de sucre " +
                        "ET un appui sur le bouton sucre " +
                        "QUAND on insère 40 cts " +
                        "ALORS aucun café ne coule " +
                        "ET l'argent est remboursé")
        public void Decrementation_Café() {
                // ETANT DONNE une machine n'ayant plus de sucre
                Machine machine = new MachineBuilder().SansSucre().Build();
                int nombreCaféInitiaux = machine.GetNombreCafésServis();
                double argentEncaisséInitial = machine.GetArgentEncaissé();

                // ET un appui sur le bouton sucre
                machine.SucrerCafé(1);

                // QUAND on insère 40 cts
                double sommeInsérée = 0.40;
                machine.Insérer(sommeInsérée);

                // ALORS aucun café ne coule
                int nombreCafésFinaux = machine.GetNombreCafésServis();
                assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

                // ET l'argent est rendu
                double argentEncaisséFinal = machine.GetArgentEncaissé();
                assertEquals(argentEncaisséInitial + sommeInsérée, argentEncaisséFinal);
        }

        @Test
        @DisplayName("ETANT DONNE une machine n'ayant pas de touillette " +
                        "ET qu'on appuie sur le bouton sucre" +
                        "QUAND on met 40cts" +
                        "ALORS un café coule " +
                        "ET le sucre est consommé" +
                        "ET l'argent est encaissé")
        public void Test_Sans_Touillette() {
                // ETANT DONNE une machine n'ayant pas de touillette
                Machine machine = new MachineBuilder()
                                .SansTouillette()
                                .Build();

                int nombreCaféInitiaux = machine.GetNombreCafésServis();
                double argentEncaisséInitial = machine.GetArgentEncaissé();
                int nombreDoseDeSucreInitial = machine.GetStockSucre();

                // ET qu'on appuie sur le bouton sucre
                machine.SucrerCafé(1);

                // QUAND on met 40cts
                machine.Insérer(0.40);

                // ALORS un café coule
                int nombreCafésFinaux = machine.GetNombreCafésServis();
                assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

                // ET une dose de sucre est consommé
                int nombreDoseDeSucreFinal = machine.GetStockSucre();
                assertEquals(nombreDoseDeSucreInitial - 1, nombreDoseDeSucreFinal);

                // ET l'argent est encaissé
                double argentEncaisséFinal = machine.GetArgentEncaissé();
                assertEquals(argentEncaisséInitial + 0.40, argentEncaisséFinal);
        }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de Lait " +
            "ET un appui sur le bouton latté " +
            "QUAND on met 45cts " +
            "ALORS aucun café ne coule " +
            "ET l'argent est rendu")
    public void Test_Sans_Lait(){
        // ETANT DONNE une machine n'ayant plus de lait
        Machine machine = new MachineBuilder().SansLait().Build();

        int nombreCaféInitiaux = machine.GetNombreCafésServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

        // ET un appui sur le bouton latté
        machine.LattéCafé();

        // QUAND on insère 45 cts
        machine.Insérer(0.45);

        // ALORS aucun café ne coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux, nombreCafésFinaux);

        // ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine n'ayant pas de chocolat" +
            "ET on appui sur le boutton choco" +
            "QUAND on met 40 cts" +
            "ALORS aucun choco ne coule" +
            "ET l'argent est rendu")
    public void Test_Sans_Choco(){
//        ETANT DONNE une machine n'ayant pas de chocolat
        Machine machine = new MachineBuilder().SansChoco().Build();

        int nombreChocoInitiaux = machine.GetNombreChocoServis();
        double argentEncaisséInitial = machine.GetArgentEncaissé();

//        ET on appui sur le boutton choco
        machine.Choco();

//        QUAND on met 40 cts
        machine.Insérer(0.4);

//        ALORS aucun choco ne coule
        int nombreChocoFinaux = machine.GetNombreChocoServis();
        assertEquals(nombreChocoInitiaux, nombreChocoFinaux);

//        ET l'argent est rendu
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }
}