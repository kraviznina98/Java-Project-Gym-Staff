/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.StavkaZahteva;
import domenskeKlase.ZahtevKlasa;
import klijentkontroler.KlijentKontroler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class ModelTabeleStavkeKlijentska extends AbstractTableModel {

    private ArrayList<StavkaZahteva> lista;
    private String[] kolone = {"Rb", "Naziv obaveze", "Status"};
    int rb = 1;

    public ModelTabeleStavkeKlijentska() {
        try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavkeKlijentska.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModelTabeleStavkeKlijentska(ArrayList<StavkaZahteva> lista) {
        this.lista = lista;
    }
    
    public ModelTabeleStavkeKlijentska(ZahtevKlasa z){
        try {
            lista=KlijentKontroler.getInstance().getAllStavkaZahteva(z);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavkeKlijentska.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        StavkaZahteva sz = lista.get(row); //izmeniti

        switch (column) {
            case 0:
                return sz.getRb();
            case 1:
                return sz.getObaveza().getNazivObaveze();
            case 2:
                return sz.getStatus();
            default:
                return null;
        }
    }

    public StavkaZahteva getSelectedStavka(int row) {
        return lista.get(row);
    }

    public boolean postoji(StavkaZahteva sz) {
        for (StavkaZahteva stavkaZahteva : lista) {
            if (stavkaZahteva.getObaveza().getObavezaID() == sz.getObaveza().getObavezaID()) {
                return true;
            }
        }
        return false;
    }

    public void dodaj(StavkaZahteva sz) {
        sz.setRb(rb++);
        lista.add(sz);
        fireTableDataChanged();
    }

    public void obrisi(int row) {
        lista.remove(row);
        rb = 1;
        for (StavkaZahteva stavkaZahteva : lista) {
            stavkaZahteva.setRb(rb++);
        }
        fireTableDataChanged();
    }

    public ArrayList<StavkaZahteva> getLista() {
        return lista;
    }

    public void izmeni(StavkaZahteva sz) {
        fireTableDataChanged();
    }
}
