/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class ZahtevKlasa extends AbstractDomainObject implements Serializable {

    private long zahtevID;
    private Date datum;
    private String rokZaIzvrsenje;
    private Zaposleni zaposleniKreator;
    private Zaposleni zaposleniIzvrsitelj;
    private ArrayList<StavkaZahteva> stavke;

    public ZahtevKlasa() {
    }

    public ZahtevKlasa(long zahtevID, Date datum, String rokZaIzvrsenje, Zaposleni zaposleniKreator, 
            Zaposleni zaposleniIzvrsitelj, ArrayList<StavkaZahteva> stavke) {
        this.zahtevID = zahtevID;
        this.datum = datum;
        this.rokZaIzvrsenje = rokZaIzvrsenje;
        this.zaposleniKreator = zaposleniKreator;
        this.zaposleniIzvrsitelj = zaposleniIzvrsitelj;
        this.stavke = stavke;
    }

    @Override
    public String nazivTabele() {
        return "zahtev";
    }

    @Override
    public String alijas() {
        return "z";
    }

    @Override
    public String spajanje() {
        return "JOIN zaposleni zap1 ON(zap1.zaposleniid=z.zaposlenikreatorid ) "
                + "JOIN zaposleni zap2 ON(zap2.zaposleniid=z.zaposleniizvrsiteljid)"
                + "JOIN opstina o1 on(zap1.opstinaid=o1.opstinaid)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Opstina o1 = new Opstina(rs.getLong("OpstinaID"), rs.getString("PttBroj"), rs.getString("Naziv"));
            Zaposleni z = new Zaposleni(rs.getLong(4), rs.getString("ImeZaposlenog"), rs.getString("PrezimeZaposlenog"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), o1);
            Zaposleni z1 = new Zaposleni(rs.getLong(5), rs.getString(13), rs.getString(14), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), o1);
            ZahtevKlasa zah = new ZahtevKlasa(rs.getLong("ZahtevID"), rs.getDate("Datum"), rs.getString("RokZaIzvrsenje"), z, z1, null);
            lista.add(zah);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(Datum, RokZaIzvrsenje, ZaposleniKreatorID, ZaposleniIzvrsiteljID)";
    }

    @Override
    public String primarniKljuc() {
        return "ZahtevID=" + zahtevID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datum.getTime()) + "','" + rokZaIzvrsenje + "'," + zaposleniKreator.getZaposleniID() + "," + zaposleniIzvrsitelj.getZaposleniID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String id() {
        return "";
    }

    public long getZahtevID() {
        return zahtevID;
    }

    public void setZahtevID(long zahtevID) {
        this.zahtevID = zahtevID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getRokZaIzvrsenje() {
        return rokZaIzvrsenje;
    }

    public void setRokZaIzvrsenje(String rokZaIzvrsenje) {
        this.rokZaIzvrsenje = rokZaIzvrsenje;
    }

    public ArrayList<StavkaZahteva> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaZahteva> stavke) {
        this.stavke = stavke;
    }

    public void setZaposleniKreator(Zaposleni zaposleniKreator) {
        this.zaposleniKreator = zaposleniKreator;
    }

    public void setZaposleniIzvrsitelj(Zaposleni zaposleniIzvrsitelj) {
        this.zaposleniIzvrsitelj = zaposleniIzvrsitelj;
    }

    public Zaposleni getZaposleniIzvrsitelj() {
        return zaposleniIzvrsitelj;
    }

    public Zaposleni getZaposleniKreator() {
        return zaposleniKreator;
    }

}
