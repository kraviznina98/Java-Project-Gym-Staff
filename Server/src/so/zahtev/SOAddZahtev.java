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
import transfer.Zahtev;
import domenskeKlase.ZahtevKlasa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class SOAddZahtev extends AbstraktnaSistemOperacija {
    private boolean uspesno=false;

    public boolean isUspesno() {
        return uspesno;
    }
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof ZahtevKlasa)) {
            throw new Exception("Nevalidan objekat!");
        }

        ZahtevKlasa z = (ZahtevKlasa) ado;

        if (z.getStavke().size() < 1) {
            uspesno=false;
            throw new Exception("Zahtev mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        ZahtevKlasa z = (ZahtevKlasa) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        long zahtevID = keys.getLong(1);
        z.setZahtevID(zahtevID);

        for (StavkaZahteva stavka : z.getStavke()) {
            stavka.setZahtev(z);
            DBBroker.getInstance().insert(stavka);
        }
        uspesno=true;

    }

}
