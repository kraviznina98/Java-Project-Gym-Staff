/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Opstina extends AbstractDomainObject implements Serializable {

    private long opstinaID;
    private String pttBroj;
    private String naziv;

    public Opstina() {
    }

    public Opstina(long opstinaID, String pttBroj, String naziv) {
        this.opstinaID = opstinaID;
        this.pttBroj = pttBroj;
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String nazivTabele() {
        return "opstina";
    }

    @Override
    public String alijas() {
        return "o";
    }

    @Override
    public String spajanje() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Opstina o = new Opstina(rs.getLong("OpstinaID"), rs.getString("PttBroj"), rs.getString("Naziv"));
            lista.add(o);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String primarniKljuc() {
        return "OpstinaID =" + opstinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String id() {
        return "OpstinaID=" + opstinaID;
    }

    public long getOpstinaID() {
        return opstinaID;
    }

    public void setOpstinaID(long opstinaID) {
        this.opstinaID = opstinaID;
    }

    public String getPttBroj() {
        return pttBroj;
    }

    public void setPttBroj(String pttBroj) {
        this.pttBroj = pttBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
