/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StavkaZahteva extends AbstractDomainObject implements Serializable {

    private int rb;
    private ZahtevKlasa zahtev;
    private String opisStavke;
    private String status;
    private Obaveza Obaveza;

    public StavkaZahteva() {
    }

    public StavkaZahteva(int rb, ZahtevKlasa zahtev, String opisStavke, String status, Obaveza Obaveza) {
        this.rb = rb;
        this.zahtev = zahtev;
        this.opisStavke = opisStavke;
        this.status = status;
        this.Obaveza = Obaveza;
    }

    @Override
    public String nazivTabele() {
        return "stavkazahteva";
    }

    @Override
    public String alijas() {
        return "sz";
    }

    @Override
    public String spajanje() {
        return "JOIN zahtev z on(z.zahtevid=sz.zahtevid)"
                + "JOIN zaposleni zap1 ON(zap1.zaposleniid=z.zaposlenikreatorid ) "
                + "JOIN zaposleni zap2 ON(zap2.zaposleniid=z.zaposleniizvrsiteljid)"
                + "JOIN opstina o1 on(zap1.opstinaid=o1.opstinaid)"
                + "JOIN obaveza ob on(ob.obavezaid=sz.obavezaid)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Opstina o1 = new Opstina(rs.getLong("OpstinaID"), rs.getString("PttBroj"), rs.getString("Naziv"));
            Zaposleni z = new Zaposleni(rs.getLong("ZaposleniID"), rs.getString("ImeZaposlenog"), rs.getString("PrezimeZaposlenog"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), o1);
            Zaposleni z1 = new Zaposleni(rs.getLong("ZaposleniID"), rs.getString("ImeZaposlenog"), rs.getString("PrezimeZaposlenog"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), o1);
            ZahtevKlasa zah = new ZahtevKlasa(rs.getLong("ZahtevID"), rs.getDate("Datum"), rs.getString("RokZaIzvrsenje"), z, z1, null);
            Obaveza ob = new Obaveza(rs.getLong("ObavezaID"), rs.getString(27), rs.getString("PredvidjenoVremeIzvrsavanja"), rs.getString("TipObaveze"));
            StavkaZahteva sz = new StavkaZahteva(rs.getInt("Rb"), zah, rs.getString("OpisStavke"), rs.getString(4), ob);
            lista.add(sz);
        }
        rs.close();
        return lista;

    }

    @Override
    public String koloneZaInsert() {
        return "(Rb, ZahtevID, OpisStavke, Status, ObavezaID)";
    }

    @Override
    public String primarniKljuc() {
        return "ZahtevID=" + zahtev.getZahtevID();
    }

    @Override
    public String vrednostiZaInsert() {
        return rb + "," + zahtev.getZahtevID() + ",'" + opisStavke + "','" + status + "'," + Obaveza.getObavezaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "Status='" + status + "'";
    }

    @Override
    public String id() {
        return "ZahtevID=" + zahtev.getZahtevID() + " AND Rb=" + rb;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public ZahtevKlasa getZahtev() {
        return zahtev;
    }

    public void setZahtev(ZahtevKlasa zahtev) {
        this.zahtev = zahtev;
    }

    public String getOpisStavke() {
        return opisStavke;
    }

    public void setOpisStavke(String opisStavke) {
        this.opisStavke = opisStavke;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Obaveza getObaveza() {
        return Obaveza;
    }

    public void setObaveza(Obaveza Obaveza) {
        this.Obaveza = Obaveza;
    }

}
