public class Machine {
    private int _nombreCafesServis = 0;

    private double _argentEncaisse = 0;

    public void Inserer(double somme){
        if(somme >= 0.4)
        {
            _argentEncaisse += somme;
            _nombreCafesServis ++;
        }
    }

    public int GetNombreCafesServis() {
        return _nombreCafesServis;
    }

    public double GetArgentEncaisse() {
        return _argentEncaisse;
    }
}