

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MachineACafeTest {
    @Test
    @DisplayName("QUAND on met 40cts ALORS un café coule ET l'argent est encaissé")
    public void Test_Cafe_Coule() {

        //ETANT DONNE  une machine
        Machine machine = new Machine();
        int nombreCafeInitiaux = machine.GetNombreCafesServis();
        double argentEncaisseInitial = machine.GetArgentEncaisse();
        double sommeInseree = 0.40;

        //QUAND on met 40cts
        machine.Inserer(sommeInseree);

        //ALORS un café coule
        int nombreCafesFinaux = machine.GetNombreCafesServis();
        Assertions.assertEquals(nombreCafeInitiaux +1, nombreCafesFinaux);

        //ET l'argent est encaissé
        double argentEncaisseFinal = machine.GetArgentEncaisse();
        Assertions.assertEquals(argentEncaisseInitial + sommeInseree, argentEncaisseFinal);
    }

    @Test
    @DisplayName("QUAND on met moins de 40cts ALORS aucun café ne coule ET l'argent est rendu")
    public void Test_Pas_Assez_Argent() {

        //ETANT DONNE  une machine
        Machine machine = new Machine();
        int nombreCafeInitiaux = machine.GetNombreCafesServis();
        double argentEncaisseInitial = machine.GetArgentEncaisse();
        double sommeInsuffisante = 0.39;

        //QUAND on met moins de 40cts
        machine.Inserer(sommeInsuffisante);

        //ALORS aucun café ne coule
        int nombreCafesFinaux = machine.GetNombreCafesServis();
        Assertions.assertEquals(nombreCafeInitiaux, nombreCafesFinaux);

        //ET l'argent est rendu
        double argentEncaisseFinal = machine.GetArgentEncaisse();
        Assertions.assertEquals(argentEncaisseInitial, argentEncaisseFinal);
    }

    @Test
    @DisplayName("QUAND on met plus de 40cts ALORS le café coule ET l'argent est encaissé")
    public void Test_Somme_Inseree_Superieur_Au_Prix() {

        //ETANT DONNE  une machine
        Machine machine = new Machine();
        int nombreCafeInitiaux = machine.GetNombreCafesServis();
        double argentEncaisseInitial = machine.GetArgentEncaisse();
        double sommeInseree = 0.41;

        //QUAND on met plus de 40cts
        machine.Inserer(sommeInseree);

        //ALORS un café coule
        int nombreCafesFinaux = machine.GetNombreCafesServis();
        assertEquals(nombreCafeInitiaux +1, nombreCafesFinaux);

        //ET l'argent est encaissé
        double argentEncaisseFinal = machine.GetArgentEncaisse();
        assertEquals(argentEncaisseInitial + sommeInseree, argentEncaisseFinal);
    }
}
