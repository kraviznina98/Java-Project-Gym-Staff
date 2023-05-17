/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PromenaObaveze extends AbstractDomainObject implements Serializable {

    private long promenaID;
    private java.util.Date datum;
    private Zaposleni zaposleni;
    private Obaveza obaveza;

    public PromenaObaveze() {
    }

    public PromenaObaveze(long promenaID, java.util.Date datum, Zaposleni zaposleni, Obaveza obaveza) {
        this.promenaID = promenaID;
        this.datum = datum;
        this.zaposleni = zaposleni;
        this.obaveza = obaveza;
    }

    @Override
    public String toString() {
        return "Ime i Prezime zaposlenog: " + zaposleni.toString() + " Obaveza: " + obaveza.getNazivObaveze();
    }

    @Override
    public String nazivTabele() {
        return "promenaobaveze";
    }

    @Override
    public String alijas() {
        return "po";
    }

    @Override
    public String spajanje() {
        return "JOIN zaposleni zap on(zap.zaposleniid=po.zaposleniid)"
                + "JOIN obaveza ob on(ob.obavezaid=po.obavezaid)"
                + "JOIN opstina o on(o.opstinaid=zap.opstinaid)";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Opstina o = new Opstina(rs.getLong("OpstinaID"), rs.getString("PttBroj"), rs.getString("Naziv"));
            Zaposleni z = new Zaposleni(rs.getLong("ZaposleniID"), rs.getString("ImeZaposlenog"), rs.getString("PrezimeZaposlenog"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), o);
            Obaveza ob = new Obaveza(rs.getLong("ObavezaID"), rs.getString("NazivObaveze"), rs.getString("PredvidjenoVremeIzvrsavanja"), rs.getString("TipObaveze"));
            PromenaObaveze po = new PromenaObaveze(rs.getLong("PromenaID"), rs.getDate("DatumPromene"), z, ob);
            lista.add(po);
        }
        rs.close();
        return lista;

    }

    @Override
    public String koloneZaInsert() {
        return "(DatumPromene, ZaposleniID, ObavezaID)";
    }

    @Override
    public String primarniKljuc() {
        return "";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Date(datum.getTime()) + "'," + zaposleni.getZaposleniID() + "," + obaveza.getObavezaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String id() {
        return "";
    }

    public long getPromenaID() {
        return promenaID;
    }

    public void setPromenaID(long promenaID) {
        this.promenaID = promenaID;
    }

    public java.util.Date getDatum() {
        return datum;
    }

    public void setDatum(java.util.Date datum) {
        this.datum = datum;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Obaveza getObaveza() {
        return obaveza;
    }

    public void setObaveza(Obaveza obaveza) {
        this.obaveza = obaveza;
    }

}
