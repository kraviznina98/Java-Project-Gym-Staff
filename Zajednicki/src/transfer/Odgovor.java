/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer_operacije.StatusOdgovora;

/**
 *
 * @author PC
 */
public class Odgovor implements Serializable {

    private Object data;
    private Exception error;
    private StatusOdgovora responseStatus;

    public Odgovor() {
    }

    public Odgovor(Object data, Exception error, StatusOdgovora responseStatus) {
        this.data = data;
        this.error = error;
        this.responseStatus = responseStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public StatusOdgovora getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(StatusOdgovora responseStatus) {
        this.responseStatus = responseStatus;
    }

}
