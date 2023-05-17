/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverkontroler;

import domenskeKlase.Obaveza;
import domenskeKlase.Opstina;
import domenskeKlase.PromenaObaveze;
import domenskeKlase.StavkaZahteva;
import domenskeKlase.ZahtevKlasa;
import domenskeKlase.Zaposleni;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;
import so.obaveza.SOAddObaveza;
import so.obaveza.SOGetAllObaveza;
import so.obaveza.SOUpdateObaveza;
import so.opstina.SOGetAllOpstina;
import so.promenaobaveze.SOAddPromenaObaveze;
import so.stavkazahteva.SOGetAllStavkaZahteva;
import so.zahtev.SOAddZahtev;
import so.zahtev.SODeleteZahtev;
import so.zahtev.SOGetAllZahtev;
import so.zahtev.SOUpdateZahtev;
import so.zaposleni.SOAddZaposleni;
import so.zaposleni.SOGetAllZaposleni;
import so.zaposleni.SOLogin;
import so.zaposleni.SOUpdateZaposleni;
import transfer.Zahtev;

/**
 *
 * @author PC
 */
public class ServerKontroler {

    private static ServerKontroler instance;

    public ServerKontroler() {
    }

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(z);
        return so.getZ();
    }

    public ArrayList<Opstina> getAllOpstina() throws Exception {
        SOGetAllOpstina so = new SOGetAllOpstina();
        so.templateExecute(new Opstina());
        return so.getLista();
    }

    public boolean addZaposleni(Zaposleni z2) throws Exception {
        SOAddZaposleni so = new SOAddZaposleni();
        so.templateExecute(z2);
        return so.isUspesno();
    }

    public boolean addObaveza(Obaveza o) throws Exception {
        SOAddObaveza so = new SOAddObaveza();
        so.templateExecute(o);
        return so.isUspesno();
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        SOGetAllZaposleni so = new SOGetAllZaposleni();
        so.templateExecute(new Zaposleni());
        return so.getLista();
    }

    public ArrayList<Obaveza> getAllObaveza() throws Exception {
        SOGetAllObaveza so = new SOGetAllObaveza();
        so.templateExecute(new Obaveza());
        return so.getLista();
    }

    public boolean addZahtev(ZahtevKlasa zah) throws Exception {
        SOAddZahtev so = new SOAddZahtev();
        so.templateExecute(zah);
        return so.isUspesno();
    }

    public boolean updateObaveza(Obaveza ob) throws Exception {
        SOUpdateObaveza so = new SOUpdateObaveza();
        so.templateExecute(ob);
        return so.isUspesno();
    }

    public void addPromenaObaveze(PromenaObaveze po) throws Exception {
        SOAddPromenaObaveze so = new SOAddPromenaObaveze();
        so.templateExecute(po);
    }

    public boolean updateZaposleni(Zaposleni zap) throws Exception {
        SOUpdateZaposleni so = new SOUpdateZaposleni();
        so.templateExecute(zap);
        return so.isUspesno();
    }

    public ArrayList<ZahtevKlasa> getAllZahtev() throws Exception {
        SOGetAllZahtev so = new SOGetAllZahtev();
        so.templateExecute(new ZahtevKlasa());
        return so.getLista();
    }

    public ArrayList<StavkaZahteva> getAllStavka() throws Exception {
        SOGetAllStavkaZahteva so = new SOGetAllStavkaZahteva();
        so.templateExecute(new StavkaZahteva());
        return so.getLista();
    }

    public boolean updateZahtev(StavkaZahteva sz) throws Exception {
        SOUpdateZahtev so = new SOUpdateZahtev();
        so.templateExecute(sz);
        return so.isUspesno();
    }

    public boolean deleteZahtev(ZahtevKlasa zah2) throws Exception {
        SODeleteZahtev so = new SODeleteZahtev();
        so.templateExecute(zah2);
        return so.isUspesno();
    }
}
