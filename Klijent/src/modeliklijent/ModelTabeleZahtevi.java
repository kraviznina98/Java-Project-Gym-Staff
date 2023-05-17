/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliklijent;

import domenskeKlase.Obaveza;
import domenskeKlase.StavkaZahteva;
import domenskeKlase.ZahtevKlasa;
import domenskeKlase.Zaposleni;
import formezahtev.DetaljiZahteva;
import formeobaveza.PretragaObaveza;
import formezaposleni.PretragaZaposlenih;
import java.text.SimpleDateFormat;
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
public class ModelTabeleZahtevi extends AbstractTableModel {

    private ArrayList<ZahtevKlasa> lista;
    private String[] kolone = {"Datum zahteva", "Rok za izvrsenje", "Zaposleni kreiaro", "Zaposleni koji izvrsava"};

    public ModelTabeleZahtevi() {
        try {
            lista = KlijentKontroler.getInstance().getAllZahtev();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleZahtevi.class.getName()).log(Level.SEVERE, null, ex);
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
        ZahtevKlasa z = lista.get(row); //izmeniti
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return sdf.format(z.getDatum());
            case 1:
                return z.getRokZaIzvrsenje();
            case 2:
                return z.getZaposleniKreator().toString();
            case 3:
                return z.getZaposleniIzvrsitelj().toString();
            default:
                return null;
        }
    }

    public ZahtevKlasa getSelectedZahtev(int row) {
        return lista.get(row);
    }

    public ArrayList<ZahtevKlasa> getLista() {
        return lista;
    }

}
