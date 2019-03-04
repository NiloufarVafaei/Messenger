/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author niloufar
 */
public class ResponseServer implements Serializable {
    int resAction;
    boolean hasData;
    String message;
    String communicateUserName;
    String IPSender;

    public String getIPSender() {
        return IPSender;
    }

    public void setIPSender(String IPSender) {
        this.IPSender = IPSender;
    }

    public String getRequestUserName() {
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName) {
        this.requestUserName = requestUserName;
    }
    String requestUserName;
    public int getResAction() {
        return resAction;
    }

    public void setResAction(int resAction) {
        this.resAction = resAction;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommunicateUserName() {
        return communicateUserName;
    }

    public void setCommunicateUserName(String communicateUserName) {
        this.communicateUserName = communicateUserName;
    }

}
