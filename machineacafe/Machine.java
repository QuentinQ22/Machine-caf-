package machineacafe;

import net.bank.interop.ModulePrelevementAutomatique;

public class Machine {
    private int _nombreCafésServis = 0;
    private double _argentEncaissé = 0;
    private Ressource _eauRes;
    private final RessourceStockée _gobelets;
    private final RessourceStockée _café;
    private final RessourceStockée _sucre;
    private final RessourceStockée _touillette;
    private boolean _boutonSucreAppuyé = false;
    private int _doseSucre;

    private ModulePrelevementAutomatique _modulePrelevementAutomatique = null;

    public Machine() {
        _gobelets = new RessourceStockée(1);
        _café = new RessourceStockée(1);
        _sucre = new RessourceStockée(10);
        _eauRes = new RessourceInfinie(true);
        _touillette = new RessourceStockée(1);
    }

    private boolean PeutFaireUnCaféSimple(double somme) {
        return somme >= 0.4 && _eauRes.EstPrésente() && _gobelets.EstPrésente() && _café.EstPrésente();
    }

    public Machine(ModulePrelevementAutomatique module) {
        this();
        _modulePrelevementAutomatique = module;
    }

    private boolean PeutFaireUnCaféSucré(double somme) {
        return PeutFaireUnCaféSimple(somme) && _sucre.EstPrésente();
    }

    public void Insérer(double somme) {
        if (_boutonSucreAppuyé ? PeutFaireUnCaféSucré(somme) : PeutFaireUnCaféSimple(somme)) {
            _argentEncaissé += somme;
            _nombreCafésServis++;
            _gobelets.Consommer();
            _café.Consommer();

            if (_boutonSucreAppuyé && _doseSucre <= GetStockSucre()) {
                for (int i = 0; i < _doseSucre; i++) {
                    _sucre.Consommer();
                }
                _touillette.Consommer();
            }
        }

        _boutonSucreAppuyé = false;
    }

    public int GetNombreCafésServis() {
        return _nombreCafésServis;
    }

    public double GetArgentEncaissé() {
        return _argentEncaissé;
    }

    public int GetStockSucre() {
        return _sucre.GetStock();
    }

    public int GetStockTouillette() {
        return _touillette.GetStock();
    }

    public void CouperEau() {
        _eauRes = new RessourceInfinie(false);
    }

    public void SucrerCafé(int doseSucre) {
        _boutonSucreAppuyé = true;
        _doseSucre = doseSucre;
    }

    public void RéapprovisionnerCafé() {
        _café.Réapprovisionner();
    }

    public void RéapprovisionnerGobelet() {
        _gobelets.Réapprovisionner();
    }

    public void RéapprovisionnerSucre() {
        _sucre.Réapprovisionner();
    }

    public void RéapprovisionnerTouillette() {
        _touillette.Réapprovisionner();
    }

    public void PayerEnCB() {
        var somme = 0.40;
        var paiementRéussi = _modulePrelevementAutomatique.Prelever(somme);
        if (paiementRéussi)
            this.Insérer(somme);
    }
}