/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.promenaobaveze;

import broker.DBBroker;
import domenskeKlase.AbstractDomainObject;
import domenskeKlase.PromenaObaveze;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import so.AbstraktnaSistemOperacija;

/**
 *
 * @author PC
 */
public class SOAddPromenaObaveze extends AbstraktnaSistemOperacija{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof PromenaObaveze)){
            throw new Exception("Nevalidan objekat!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        PromenaObaveze po=(PromenaObaveze) ado;
        PreparedStatement ps=DBBroker.getInstance().insert(ado);
        
        ResultSet keys=ps.getGeneratedKeys();
        keys.next();
        long promenaID=keys.getLong(1);
        po.setPromenaID(promenaID);
        
    }
    
}
