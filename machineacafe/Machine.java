package machineacafe;

public class Machine {
    private int _nombreCafésServis = 0;
    private double _argentEncaissé = 0;
    private boolean _eauDisponible = true;
    private int _nombreGobelets;
    private int _caféEnStock;

    public Machine(int nombreGobeletsInitial, int dosesCaféInitiales) {
        _nombreGobelets = nombreGobeletsInitial;
        _caféEnStock = dosesCaféInitiales;
    }

    public void Insérer(double somme) {
        if(somme >= 0.4 && _eauDisponible && _nombreGobelets > 0 && _caféEnStock > 0){
            _argentEncaissé += somme;
            _nombreCafésServis ++;
            _nombreGobelets --;
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