/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkazahteva;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.StavkaZahteva;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOGetAllStavkaZahteva extends AbstraktnaSistemOperacija {

    private ArrayList<StavkaZahteva> lista;

    public ArrayList<StavkaZahteva> getLista() {
        return lista;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaZahteva)) {
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> stavke = DBBroker.getInstance().selectBezUslova(ado);
        lista = (ArrayList<StavkaZahteva>) (ArrayList<?>) stavke;

    }

}
