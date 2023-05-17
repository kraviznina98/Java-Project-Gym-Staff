/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.opstina;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.Opstina;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOGetAllOpstina extends AbstraktnaSistemOperacija {

    private ArrayList<Opstina> lista;

    public ArrayList<Opstina> getLista() {
        return lista;
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Opstina)) {
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> opstine = DBBroker.getInstance().selectBezUslova(ado);
        lista = (ArrayList<Opstina>) (ArrayList<?>) opstine;
    }

}
