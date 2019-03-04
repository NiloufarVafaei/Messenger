/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author niloufar
 */
public class Contact {

    private String Name;
    private Long Mobile;
    private Long HomeNumber;
    private String IP;

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Long getMobile() {
        return Mobile;
    }

    public void setMobile(Long Mobile) {
        this.Mobile = Mobile;
    }

    public Long getHomeNumber() {
        return HomeNumber;
    }

    public void setHomeNumber(Long HomeNumber) {
        this.HomeNumber = HomeNumber;
    }

}
