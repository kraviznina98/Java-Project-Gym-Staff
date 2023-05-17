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
public class SOLogin extends AbstraktnaSistemOperacija {

    Zaposleni z;

    public Zaposleni getZ() {
        return z;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Nevalidan objekat");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        Zaposleni zap=(Zaposleni) ado;
        
        ArrayList<AbstractDomainObject> lista = DBBroker.getInstance().selectBezUslova(ado);
        ArrayList<Zaposleni> zaposleni = (ArrayList<Zaposleni>) (ArrayList<?>) lista;
        
        for (Zaposleni zaposleni1 : zaposleni) {
            if(zaposleni1.getKorisnickoIme().equals(zap.getKorisnickoIme())
                    && zaposleni1.getLozinka().equals(zap.getLozinka())){
                z=zaposleni1;
            }
        }

    }

}
