package machineacafe;

public class Machine {
    private int _nombreCafésServis = 0;
    private double _argentEncaissé = 0;
    private boolean _eauDisponible = true;
    private boolean _gobeletsDisponibles = true;
    private boolean _caféEnStock = true;

    public Machine(int nombreGobeletsInitial, int dosesCaféInitiales) {
        _gobeletsDisponibles = nombreGobeletsInitial > 0; // TODO : contradiction à résoudre avec le client
        _caféEnStock = dosesCaféInitiales > 0;
    }

    public void Insérer(double somme) {
        if(somme >= 0.4 && _eauDisponible && _gobeletsDisponibles && _caféEnStock){
            _argentEncaissé += somme;
            _nombreCafésServis ++;
        }
    }

    public int GetNombreCafésServis() {
        return _nombreCafésServis;
    }

    public double GetArgentEncaissé() {
        return _argentEncaissé;
    }

    public void CouperEau() {
        _eauDisponible = false;
    }
}
