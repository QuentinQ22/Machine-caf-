package machineacafe;

class RessourceInfinie implements Ressource {
    private final boolean _estPrésente;

    public boolean EstPrésente(){
        return _estPrésente;
    }

    public void Consommer(){
    }

    public RessourceInfinie(boolean estPrésente){

        _estPrésente = estPrésente;
    }
}
