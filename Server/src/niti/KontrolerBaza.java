/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author PC
 */
public class KontrolerBaza extends Thread {

    private ServerSocket serverSocket;

    public KontrolerBaza() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ObradaZahteva oz = new ObradaZahteva(socket);
                oz.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
