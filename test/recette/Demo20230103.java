package recette;

import machineacafe.Machine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Demo20230103 {
    @Test
    public void DemoCafesAssortis() {
        var machine = new Machine();
        machine.RéapprovisionnerGobelet();
        machine.RéapprovisionnerGobelet();
        machine.RéapprovisionnerGobelet();

        machine.RéapprovisionnerSucre();

        machine.RéapprovisionnerCafé();
        machine.RéapprovisionnerCafé();
        machine.RéapprovisionnerCafé();

        //Café simple

        machine.Insérer(0.40);

        //Cafés sucrés
        machine.SucrerCafé(1);
        machine.Insérer(0.40);

        machine.SucrerCafé(1);
        machine.Insérer(0.40);

        //Café simple
        machine.Insérer(0.40);

        //Café en échec
        machine.Insérer(0.40);

        assertEquals(4, machine.GetNombreCafésServis());
        assertEquals(9, machine.GetStockSucre());
        assertEquals(1.6, machine.GetArgentEncaissé());
    }
}
