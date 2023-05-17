/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domenskeKlase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Obaveza extends AbstractDomainObject implements Serializable {

    private long obavezaID;
    private String nazivObaveze;
    private String predvidjenoVremeIzvrsavanja;
    private String tipObaveze;

    public Obaveza() {
    }

    public Obaveza(long obavezaID, String nazivObaveze, String predvidjenoVremeIzvrsavanja, String tipObaveze) {
        this.obavezaID = obavezaID;
        this.nazivObaveze = nazivObaveze;
        this.predvidjenoVremeIzvrsavanja = predvidjenoVremeIzvrsavanja;
        this.tipObaveze = tipObaveze;
    }

    @Override
    public String toString() {
        return nazivObaveze;
    }

    @Override
    public String nazivTabele() {
        return "obaveza";
    }

    @Override
    public String alijas() {
        return "ob";
    }

    @Override
    public String spajanje() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> selectLista(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Obaveza o = new Obaveza(rs.getLong("ObavezaID"), rs.getString("NazivObaveze"), rs.getString("PredvidjenoVremeIzvrsavanja"), rs.getString("TipObaveze"));
            lista.add(o);
        }
        rs.close();
        return lista;

    }

    @Override
    public String koloneZaInsert() {
        return "(NazivObaveze, PredvidjenoVremeIzvrsavanja, TipObaveze)";
    }

    @Override
    public String primarniKljuc() {
        return "ObavezaID=" + obavezaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivObaveze + "','" + predvidjenoVremeIzvrsavanja + "','" + tipObaveze + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "PredvidjenoVremeIzvrsavanja= '" + predvidjenoVremeIzvrsavanja + "', TipObaveze='" + tipObaveze + "'";
    }

    @Override
    public String id() {
        return "ObavezaID=" + obavezaID;
    }

    public long getObavezaID() {
        return obavezaID;
    }

    public void setObavezaID(long obavezaID) {
        this.obavezaID = obavezaID;
    }

    public String getNazivObaveze() {
        return nazivObaveze;
    }

    public void setNazivObaveze(String nazivObaveze) {
        this.nazivObaveze = nazivObaveze;
    }

    public String getPredvidjenoVremeIzvrsavanja() {
        return predvidjenoVremeIzvrsavanja;
    }

    public void setPredvidjenoVremeIzvrsavanja(String predvidjenoVremeIzvrsavanja) {
        this.predvidjenoVremeIzvrsavanja = predvidjenoVremeIzvrsavanja;
    }

    public String getTipObaveze() {
        return tipObaveze;
    }

    public void setTipObaveze(String tipObaveze) {
        this.tipObaveze = tipObaveze;
    }

}
