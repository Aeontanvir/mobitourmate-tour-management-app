package aeontanvir.com.mobitourmate.pojo;

/**
 * Created by aeon on 13 Nov, 2016.
 */

public class User {
    private int id;
    private String fullname;
    private String username;
    private String password;
    private String contactno;
    private String address;

    public User() {}

    public User(String fullname, String username, String password, String contactno, String address) {
        setFullname(fullname);
        setUsername(username);
        setPassword(password);
        setContactno(contactno);
        setAddress(address);
    }
    public User(int id, String fullname, String username, String password, String contactno, String address) {
        setId(id);
        setFullname(fullname);
        setUsername(username);
        setPassword(password);
        setContactno(contactno);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
