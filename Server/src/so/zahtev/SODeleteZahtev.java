/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zahtev;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.ZahtevKlasa;
import java.sql.SQLException;
import so.AbstraktnaSistemOperacija;
import transfer.Zahtev;

/**
 *
 * @author PC
 */
public class SODeleteZahtev extends AbstraktnaSistemOperacija {

    private boolean uspesno;

    public boolean isUspesno() {
        return uspesno;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof ZahtevKlasa)) {
            uspesno = false;
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ZahtevKlasa z = (ZahtevKlasa) ado;
        DBBroker.getInstance().delete(z.getStavke().get(0));
        DBBroker.getInstance().delete(ado);
        uspesno = true;
    }
}
