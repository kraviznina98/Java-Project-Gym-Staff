/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zahtev;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.StavkaZahteva;
import java.sql.SQLException;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOUpdateZahtev extends AbstraktnaSistemOperacija {

    private boolean uspesno;

    public boolean isUspesno() {
        return uspesno;
    }
    

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaZahteva)) {
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().update(ado);
        uspesno = true;
    }

}
