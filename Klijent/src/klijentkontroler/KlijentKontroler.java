/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijentkontroler;

import domenskeKlase.Obaveza;
import domenskeKlase.Opstina;
import domenskeKlase.PromenaObaveze;
import domenskeKlase.StavkaZahteva;
import domenskeKlase.ZahtevKlasa;
import domenskeKlase.Zaposleni;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.Zahtev;
import transfer.Odgovor;
import transfer_operacije.StatusOdgovora;
import transfer_operacije.Operacije;

/**
 *
 * @author PC
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;

    public KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Zahtev req = new Zahtev(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        Odgovor res = (Odgovor) in.readObject();
        if (res.getResponseStatus().equals(StatusOdgovora.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        return (Zaposleni) sendRequest(Operacije.LOGIN, z);
    }

    public ArrayList<Opstina> getAllOpstina() throws Exception {
        return (ArrayList<Opstina>) sendRequest(Operacije.GET_ALL_OPSTINA, null);
    }

    public boolean addZaposleni(Zaposleni z) throws Exception {
        return (boolean) sendRequest(Operacije.ADD_ZAPOSLENI, z);
    }

    public boolean addObaveza(Obaveza o) throws Exception {
        return (boolean) sendRequest(Operacije.ADD_OBAVEZA, o);
    }

    public ArrayList<Zaposleni> getAllZaposleni() throws Exception {
        return (ArrayList<Zaposleni>) sendRequest(Operacije.GET_ALL_ZAPOSLENI, null);

    }

    public ArrayList<Obaveza> getAllObaveza() throws Exception {
        return (ArrayList<Obaveza>) sendRequest(Operacije.GET_ALL_OBAVEZA, null);
    }

    public boolean addZahtev(domenskeKlase.ZahtevKlasa zah) throws Exception {
        return (boolean) sendRequest(Operacije.ADD_ZAHTEV, zah);
    }

    public boolean updateObaveza(Obaveza o) throws Exception {
        return (boolean) sendRequest(Operacije.UPDATE_OBAVEZA, o);
    }

    public void addPromenaObaveze(PromenaObaveze po) throws Exception {
        sendRequest(Operacije.ADD_PROMENA_OBAVEZE, po);
    }

    public boolean updateZaposleni(Zaposleni z) throws Exception {
        return (boolean) sendRequest(Operacije.UPDATE_ZAPOSLENI, z);
    }

    public ArrayList<ZahtevKlasa> getAllZahtev() throws Exception {
        return (ArrayList<ZahtevKlasa>) sendRequest(Operacije.GET_ALL_ZAHTEV, null);
    }

    public ArrayList<StavkaZahteva> getAllStavkaZahteva(ZahtevKlasa z) throws Exception {
        return (ArrayList<StavkaZahteva>) sendRequest(Operacije.GET_ALL_STAVKA, z);
    }

    public boolean updateZahtev(StavkaZahteva sz) throws Exception {
        return (boolean) sendRequest(Operacije.UPDATE_ZAHTEV, sz);
    }

    public boolean deleteZahtev(ZahtevKlasa z) throws Exception {
        return (boolean) sendRequest(Operacije.DELETE_ZAHTEV, z);
    }
}
