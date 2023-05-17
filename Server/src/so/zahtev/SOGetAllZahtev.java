/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zahtev;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.ZahtevKlasa;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOGetAllZahtev extends AbstraktnaSistemOperacija {

    private ArrayList<ZahtevKlasa> lista;

    public ArrayList<ZahtevKlasa> getLista() {
        return lista;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof ZahtevKlasa)) {
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        ArrayList<AbstractDomainObject> zahtevi = DBBroker.getInstance().selectBezUslova(ado);
        lista = (ArrayList<ZahtevKlasa>) (ArrayList<?>) zahtevi;

    }

}
