/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.obaveza;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.Obaveza;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOGetAllObaveza extends AbstraktnaSistemOperacija {

    private ArrayList<Obaveza> lista;

    public ArrayList<Obaveza> getLista() {
        return lista;
    }
    

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Obaveza)) {
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> obaveze = DBBroker.getInstance().selectBezUslova(ado);
        lista = (ArrayList<Obaveza>) (ArrayList<?>) obaveze;
    }

}
