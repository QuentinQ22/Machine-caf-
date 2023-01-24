package machineacafe;

class RessourceStockée implements Ressource {
    private int _stock;

    public RessourceStockée(int stockInitial) {
        _stock = stockInitial;
    }

    public void Consommer() {
        _stock--;
    }

    void Réapprovisionner() {
        _stock++;
    }

    public boolean EstPrésente() {
        return _stock > 0;
    }

    int GetStock() {
        return _stock;
    }
}