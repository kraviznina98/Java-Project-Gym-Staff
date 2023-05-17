/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domenskeKlase.Obaveza;
import domenskeKlase.Opstina;
import domenskeKlase.PromenaObaveze;
import domenskeKlase.StavkaZahteva;
import domenskeKlase.ZahtevKlasa;
import domenskeKlase.Zaposleni;
import serverkontroler.ServerKontroler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Zahtev;
import transfer.Odgovor;
import transfer_operacije.StatusOdgovora;
import transfer_operacije.Operacije;

/**
 *
 * @author PC
 */
public class ObradaZahteva extends Thread {

    private Socket socket;

    ObradaZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Zahtev req = (Zahtev) in.readObject();
                Odgovor res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Odgovor handleRequest(Zahtev req) {
        Odgovor res = new Odgovor(null, null, StatusOdgovora.Success);

        try {
            switch (req.getOperation()) {
                case Operacije.LOGIN:
                    Zaposleni z = (Zaposleni) req.getData();
                    Zaposleni z1 = ServerKontroler.getInstance().login(z);//zap ili null
                    res.setData(z1);
                    if (res.getData() == null) {
                        throw new Exception("Ne postoji zaposleni sa ovim kredencijalima!");
                    } else {
                        break;
                    }
                case Operacije.GET_ALL_OPSTINA:
                    ArrayList<Opstina> opstine = ServerKontroler.getInstance().getAllOpstina();
                    res.setData(opstine);
                    break;
                case Operacije.ADD_ZAPOSLENI:
                    Zaposleni z2 = (Zaposleni) req.getData();
                    boolean uspesno = ServerKontroler.getInstance().addZaposleni(z2);
                    res.setData(uspesno);
                    break;
                case Operacije.ADD_OBAVEZA:
                    Obaveza o = (Obaveza) req.getData();
                    boolean uspesno2 = ServerKontroler.getInstance().addObaveza(o);
                    res.setData(uspesno2);
                    break;
                case Operacije.GET_ALL_ZAPOSLENI:
                    ArrayList<Zaposleni> zaposleni2 = ServerKontroler.getInstance().getAllZaposleni();
                    res.setData(zaposleni2);
                    break;
                case Operacije.GET_ALL_OBAVEZA:
                    ArrayList<Obaveza> obaveze = ServerKontroler.getInstance().getAllObaveza();
                    res.setData(obaveze);
                    break;
                case Operacije.ADD_ZAHTEV:
                    ZahtevKlasa zah = (ZahtevKlasa) req.getData();
                    boolean uspesno3 = ServerKontroler.getInstance().addZahtev(zah);
                    res.setData(uspesno3);
                    break;
                case Operacije.UPDATE_OBAVEZA:
                    Obaveza ob = (Obaveza) req.getData();
                    boolean uspesno4 = ServerKontroler.getInstance().updateObaveza(ob);
                    res.setData(uspesno4);
                    break;
                case Operacije.ADD_PROMENA_OBAVEZE:
                    PromenaObaveze po = (PromenaObaveze) req.getData();
                    ServerKontroler.getInstance().addPromenaObaveze(po);
                    break;
                case Operacije.UPDATE_ZAPOSLENI:
                    Zaposleni zap = (Zaposleni) req.getData();
                    boolean uspesno5 = ServerKontroler.getInstance().updateZaposleni(zap);
                    res.setData(uspesno5);
                    break;
                case Operacije.GET_ALL_ZAHTEV:
                    ArrayList<ZahtevKlasa> zahtevi = ServerKontroler.getInstance().getAllZahtev();
                    res.setData(zahtevi);
                    if (res.getData() == null) {
                        throw new Exception("Lista zahteva je prazna!");
                    } else {
                        break;
                    }
                case Operacije.GET_ALL_STAVKA:
                    ZahtevKlasa zahtev_stavke = (ZahtevKlasa) req.getData();
                    ArrayList<StavkaZahteva> stavke = ServerKontroler.getInstance().getAllStavka();
                    ArrayList<StavkaZahteva> stavke_odgovor = new ArrayList<>();

                    if (stavke.size() < 1) {
                        throw new Exception("Ne postoji nijedan zahtev!");
                    } else {
                        for (StavkaZahteva stavkaZahteva : stavke) {
                            if (stavkaZahteva.getZahtev().getZahtevID() == zahtev_stavke.getZahtevID()) {
                                stavke_odgovor.add(stavkaZahteva);
                            }
                        }
                        res.setData(stavke_odgovor);
                        break;
                    }
                case Operacije.UPDATE_ZAHTEV:
                    StavkaZahteva sz=(StavkaZahteva) req.getData();
                    boolean uspesno6=ServerKontroler.getInstance().updateZahtev(sz);
                    res.setData(uspesno6);
                    break;
                case Operacije.DELETE_ZAHTEV:
                    ZahtevKlasa zah2=(ZahtevKlasa) req.getData();
                    boolean uspesno7=ServerKontroler.getInstance().deleteZahtev(zah2);
                    res.setData(uspesno7);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(StatusOdgovora.Error);
        }
        return res;
    }
}
