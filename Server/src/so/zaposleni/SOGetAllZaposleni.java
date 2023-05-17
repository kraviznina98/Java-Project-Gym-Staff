/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zaposleni;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.Zaposleni;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOGetAllZaposleni extends AbstraktnaSistemOperacija {

    private ArrayList<Zaposleni> lista;

    public ArrayList<Zaposleni> getLista() {
        return lista;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> zaposleni = DBBroker.getInstance().selectBezUslova(ado);
        lista = (ArrayList<Zaposleni>) (ArrayList<?>) zaposleni;
    }

}
