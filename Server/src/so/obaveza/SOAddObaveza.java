/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.obaveza;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.Obaveza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOAddObaveza extends AbstraktnaSistemOperacija{
    private boolean uspesno;

    public boolean isUspesno() {
        return uspesno;
    }
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Obaveza)){
            throw new Exception("Nevalidan objekat");
        }
        Obaveza o=(Obaveza) ado;
        ArrayList<AbstractDomainObject> obaveze=DBBroker.getInstance().selectBezUslova(ado);
        ArrayList<Obaveza> lista=(ArrayList<Obaveza>)(ArrayList<?>) obaveze;
        
        for (Obaveza obaveza : lista) {
            if(obaveza.getNazivObaveze().toLowerCase().equals(o.getNazivObaveze().toLowerCase())){
                uspesno=false;
                throw new Exception("Vec postoji obaveza sa ovim nazivom!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        Obaveza o=(Obaveza) ado;
        PreparedStatement ps=DBBroker.getInstance().insert(ado);
        uspesno=true;
        
        ResultSet keys=ps.getGeneratedKeys();
        keys.next();
        long obavezaID=keys.getLong(1);
        o.setObavezaID(obavezaID);
    }
    
}
