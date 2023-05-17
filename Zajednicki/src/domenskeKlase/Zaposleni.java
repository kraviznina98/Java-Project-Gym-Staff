/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Zaposleni extends AbstractDomainObject implements Serializable {

    private long zaposleniID;
    private String imeZaposlenog;
    private String prezimeZaposlenog;
    private String korisnickoIme;
    private String lozinka;
    private Opstina opstina;

    public Zaposleni() {
    }

    public Zaposleni(long zaposleniID, String imeZaposlenog, String prezimeZaposlenog, String korisnickoIme, String lozinka, Opstina opstina) {
        this.zaposleniID = zaposleniID;
        this.imeZaposlenog = imeZaposlenog;
        this.prezimeZaposlenog = prezimeZaposlenog;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.opstina = opstina;
    }

    @Override
    public String toString() {
        return imeZaposlenog + " " + prezimeZaposlenog;
    }

    @Override
    public String nazivTabele() {
        return "zaposleni";
    }

    @Override
    public String alijas() {
        return "zap";
    }

    @Override
    public String spajanje() {
        return "JOIN opstina o on(o.opstinaid=zap.opstinaid)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Opstina o = new Opstina(rs.getLong("OpstinaID"), rs.getString("PttBroj"), rs.getString("Naziv"));
            Zaposleni z = new Zaposleni(rs.getLong("ZaposleniID"), rs.getString("ImeZaposlenog"), rs.getString("PrezimeZaposlenog"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), o);
            lista.add(z);
        }
        rs.close();
        return lista;

    }

    @Override
    public String koloneZaInsert() {
        return "(ImeZaposlenog, PrezimeZaposlenog, KorisnickoIme, Lozinka, OpstinaID)";
    }

    @Override
    public String primarniKljuc() {
        return "ZaposleniID=" + zaposleniID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imeZaposlenog + "','" + prezimeZaposlenog + "','" + korisnickoIme + "','" + lozinka + "'," + opstina.getOpstinaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "KorisnickoIme='" + korisnickoIme + "',Lozinka='" + lozinka + "',OpstinaID=" + opstina.getOpstinaID();
    }

    @Override
    public String id() {
        return "ZaposleniID=" + zaposleniID;
    }

    public long getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(long zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getImeZaposlenog() {
        return imeZaposlenog;
    }

    public void setImeZaposlenog(String imeZaposlenog) {
        this.imeZaposlenog = imeZaposlenog;
    }

    public String getPrezimeZaposlenog() {
        return prezimeZaposlenog;
    }

    public void setPrezimeZaposlenog(String prezimeZaposlenog) {
        this.prezimeZaposlenog = prezimeZaposlenog;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Opstina getOpstina() {
        return opstina;
    }

    public void setOpstina(Opstina opstina) {
        this.opstina = opstina;
    }

}
