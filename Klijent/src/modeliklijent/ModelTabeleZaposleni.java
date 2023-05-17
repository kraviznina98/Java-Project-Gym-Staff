/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.Obaveza;
import domenskeKlase.StavkaZahteva;
import domenskeKlase.Zaposleni;
import formezahtev.DetaljiZahteva;
import formeobaveza.PretragaObaveza;
import formezaposleni.PretragaZaposlenih;
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
public class ModelTabeleZaposleni extends AbstractTableModel {

    private ArrayList<Zaposleni> lista;
    private String[] kolone = {"Zaposleni ID", "Ime i prezime", "Korisnicko ime"};
    private String parametar = "";

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public ModelTabeleZaposleni() {
        try {
            lista = KlijentKontroler.getInstance().getAllZaposleni();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModelTabeleZaposleni(ArrayList<Zaposleni> lista) {
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
        Zaposleni z = lista.get(row); //izmeniti

        switch (column) {
            case 0:
                return z.getZaposleniID();
            case 1:
                return z.toString();
            case 2:
                return z.getKorisnickoIme();
            default:
                return null;
        }
    }

    public Zaposleni getSelectedZaposleni(int row) {
        return lista.get(row);
    }

    public ArrayList<Zaposleni> getLista() {
        return lista;
    }

    public void osveziTabelu() {
        ArrayList<Zaposleni> novaLista=new ArrayList<>();
        for (Zaposleni zaposleni : lista) {
            if(zaposleni.getImeZaposlenog().toLowerCase().contains(parametar.toLowerCase())
                    || zaposleni.getPrezimeZaposlenog().toLowerCase().contains(parametar.toLowerCase())){
                novaLista.add(zaposleni);
            }
        }
        if(novaLista.size()>=1){
            lista=novaLista;
            PretragaZaposlenih.resetuj();
            fireTableDataChanged();
        }
        else {
            PretragaZaposlenih.vrati(1);
        }
    }

}
