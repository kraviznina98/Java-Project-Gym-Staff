/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.Obaveza;
import domenskeKlase.StavkaZahteva;
import formezahtev.DetaljiZahteva;
import formeobaveza.PretragaObaveza;
import klijentkontroler.KlijentKontroler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class ModelTabeleObaveze extends AbstractTableModel {

    private ArrayList<Obaveza> lista;
    private String[] kolone = {"Obaveza ID", "Naziv obaveze", "Vreme izvrsavanja", "Tip obaveze"};
    private String parametar = "";

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public ModelTabeleObaveze() {
        try {
            lista = KlijentKontroler.getInstance().getAllObaveza();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleObaveze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModelTabeleObaveze(ArrayList<Obaveza> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Obaveza o = lista.get(row); //izmeniti

        switch (column) {
            case 0:
                return o.getObavezaID();
            case 1:
                return o.getNazivObaveze();
            case 2:
                return o.getPredvidjenoVremeIzvrsavanja();
            case 3:
                return o.getTipObaveze();
            default:
                return null;
        }
    }

    public Obaveza getSelectedObaveza(int row) {
        return lista.get(row);
    }

    public ArrayList<Obaveza> getLista() {
        return lista;
    }

    public void osveziTabelu() {
        ArrayList<Obaveza> novaLista=new ArrayList<>();
        for (Obaveza obaveza : lista) {
            if(obaveza.getNazivObaveze().toLowerCase().contains(parametar.toLowerCase())){
                novaLista.add(obaveza);
            }
        }
        if(novaLista.size()>=1){
            lista=novaLista;
            PretragaObaveza.resetuj();
            fireTableDataChanged();
        }
        else {
            PretragaObaveza.vrati(1);
        }
    }

}
