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
public class SOUpdateZaposleni extends AbstraktnaSistemOperacija {

    private boolean uspesno;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Nevalidan objekat");
        }
        Zaposleni z = (Zaposleni) ado;

        ArrayList<AbstractDomainObject> zaposleni = DBBroker.getInstance().selectBezUslova(ado);
        ArrayList<Zaposleni> lista = (ArrayList<Zaposleni>) (ArrayList<?>) zaposleni;

        for (Zaposleni zaposleni1 : lista) {
            if (zaposleni1.getKorisnickoIme().equals(z.getKorisnickoIme())) {
                uspesno = false;
                throw new Exception("Vec postoji zaposleni sa ovim korisnickim imenom!");
            }
        }

        if (z.getLozinka().length() < 8) {
            throw new Exception("Lozinka mora imati najmanje 8 znakova!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().update(ado);
        uspesno = true;
    }

    public boolean isUspesno() {
        return uspesno;
    }
    

}
