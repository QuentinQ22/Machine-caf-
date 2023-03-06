import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.MachineBuilder;
import utilities.ModulePaiementMock;
import utilities.ModulePaiementSpy;

import static org.junit.jupiter.api.Assertions.*;

public class PaiementCarteTest {
    @Test
    @DisplayName("ETANT DONNE une machine reliée à un module sans-contact répondant toujours ok" +
            "QUAND on appuie sur le bouton CB " +
            "ALORS le module est interrogé " +
            "ET un café coule" +
            "ET 40cts sont encaissés")
    public void TestCaféCB(){
        // ETANT DONNE une machine reliée à un module sans-contact répondant toujours ok
        var modulePaiement = new ModulePaiementSpy(new ModulePaiementMock(true));

        var machine = new MachineBuilder()
                .AyantUnModuleDePaiement(modulePaiement)
                .Build();

        double argentEncaisséInitial = machine.GetArgentEncaissé();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();

        // QUAND on appuie sur le bouton CB
        ( machine).PayerEnCB();

        // ALORS le module est interrogé
        assertTrue(modulePaiement.AEteAppeleAvecLeMontant(0.4));

        // ET un café coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux + 1, nombreCafésFinaux);

        // ET 40cts sont encaissés
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial + 0.40, argentEncaisséFinal);
    }

    @Test
    @DisplayName("ETANT DONNE une machine reliée à un module sans-contact répondant toujours ko" +
            "QUAND on appuie sur le bouton CB " +
            "ALORS le module est interrogé " +
            "ET aucun café ne coule" +
            "ET aucun argent n'est encaissé")
    public void TestCaféCB_Ko(){
        // ETANT DONNE une machine reliée à un module sans-contact répondant toujours ko
        var modulePaiement = new ModulePaiementSpy(new ModulePaiementMock(false));

        var machine = new MachineBuilder()
                .AyantUnModuleDePaiement(modulePaiement)
                .Build();

        double argentEncaisséInitial = machine.GetArgentEncaissé();
        int nombreCaféInitiaux = machine.GetNombreCafésServis();

        // QUAND on appuie sur le bouton CB
        machine.PayerEnCB();

        // ALORS le module est interrogé
        assertTrue(modulePaiement.AEteAppeleAvecLeMontant(0.4));

        // ET aucun café ne coule
        int nombreCafésFinaux = machine.GetNombreCafésServis();
        assertEquals(nombreCaféInitiaux, nombreCafésFinaux);

        // ET aucun argent n'est encaissé
        double argentEncaisséFinal = machine.GetArgentEncaissé();
        assertEquals(argentEncaisséInitial, argentEncaisséFinal);
    }
}