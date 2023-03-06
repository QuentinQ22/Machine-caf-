package machineacafe;

import lib.net.bank.interop.ModulePrelevementAutomatique;

public class Machine {
    private int _nombreCafésServis = 0;
    private double _argentEncaissé = 0;
    private int _nombreChocoServis = 0;
    private Ressource _eauRes;
    private final RessourceStockée _gobelets;
    private final RessourceStockée _café;
    private final RessourceStockée _sucre;
    private final RessourceStockée _touillette;
    private final RessourceStockée _lait;
    private final RessourceStockée _choco;
    private boolean _boutonSucreAppuyé = false;
    private boolean _boutonLattéAppuyé = false;
    private boolean _boutonChocoAppuyé = false;
    private boolean _boutonChocoLattéAppuyé = false;
    private boolean _boutonCappucinoAppuyé = false;
    private int _doseSucre;

    private ModulePrelevementAutomatique _modulePrelevementAutomatique = null;

    public Machine() {
        _gobelets = new RessourceStockée(1);
        _café = new RessourceStockée(1);
        _sucre = new RessourceStockée(10);
        _eauRes = new RessourceInfinie(true);
        _touillette = new RessourceStockée(1);
        _lait = new RessourceStockée(1);
        _choco = new RessourceStockée(1);
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

    private  boolean PeutFaireUnCaféLatté(double somme) {
        return somme >= 0.45 && _eauRes.EstPrésente() && _gobelets.EstPrésente() && _café.EstPrésente() && _lait.EstPrésente();
    }

    private boolean PeutFaireUnChoco(double somme) {
        return somme >= 0.4 && _eauRes.EstPrésente() && _gobelets.EstPrésente() && _choco.EstPrésente();
    }

    private boolean PeutFaireUnChocoLatté(double somme) {
        return somme >= 0.45 && _gobelets.EstPrésente() && _lait.EstPrésente() && _choco.EstPrésente();
    }

    private boolean PeutFaireUnCappucino(double somme) {
        return somme >= 0.50 && _eauRes.EstPrésente() && _gobelets.EstPrésente() && _lait.EstPrésente() && _choco.EstPrésente() && _café.EstPrésente();
    }

    public void Insérer(double somme) {
        if (_boutonChocoAppuyé){
            if (PeutFaireUnChoco(somme)){
                _argentEncaissé += somme;
                _nombreChocoServis ++;
                _gobelets.Consommer();
                _choco.Consommer();
            }
            _boutonChocoAppuyé = false;
            return;
        }

        if (_boutonChocoLattéAppuyé) {
            if (PeutFaireUnChocoLatté(somme)){
                _argentEncaissé += somme;
                _nombreChocoServis ++;
                _gobelets.Consommer();
                _choco.Consommer();
                _lait.Consommer();
            }

            _boutonChocoLattéAppuyé = false;
            return;
        }

        if (_boutonCappucinoAppuyé) {
            if (PeutFaireUnCappucino(somme)) {
                _argentEncaissé += somme;
                _nombreChocoServis ++;
                _nombreCafésServis++;
                _gobelets.Consommer();
                _choco.Consommer();
                _café.Consommer();
                _lait.Consommer();
            }

            _boutonCappucinoAppuyé = false;
            return;
        }

        if(_boutonLattéAppuyé){
            if (PeutFaireUnCaféLatté(somme)){
                _argentEncaissé += somme;
                _nombreCafésServis ++;
                _gobelets.Consommer();
                _café.Consommer();
                _lait.Consommer();
            }

            _boutonLattéAppuyé = false;
            return;
        }

        if(_boutonSucreAppuyé ? PeutFaireUnCaféSucré(somme) : PeutFaireUnCaféSimple(somme)){
            _argentEncaissé += somme;
            _nombreCafésServis ++;
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

    public int GetNombreChocoServis(){ return _nombreChocoServis; }

    public double GetArgentEncaissé() {
        return _argentEncaissé;
    }

    public int GetStockSucre() {
        return _sucre.GetStock();
    }

    public int GetStockTouillette() {
        return _touillette.GetStock();
    }

    public int GetStockLait() { return _lait.GetStock(); }

    public int GetStockChoco() { return _choco.GetStock(); }

    public void CouperEau() {
        _eauRes = new RessourceInfinie(false);
    }

    public void SucrerCafé(int doseSucre) {
        _boutonSucreAppuyé = true;
        _doseSucre = doseSucre;
    }

    public void LattéCafé() { _boutonLattéAppuyé = true; }

    public void Choco() { _boutonChocoAppuyé = true; }

    public void ChocoLatté() { _boutonChocoLattéAppuyé = true; }

    public void Cappucino() { _boutonCappucinoAppuyé = true; }

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

    public void RéapprovisionnerLait() { _lait.Réapprovisionner(); }

    public void RéapprovisionnerChoco() { _choco.Réapprovisionner(); }

    public void PayerEnCB() {
        var somme = 0.40;
        var paiementRéussi = _modulePrelevementAutomatique.Prelever(somme);
        if (paiementRéussi)
            this.Insérer(somme);
    }
}