package com.yarm.jettyandjerseyjdk20example;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlElement;

public class Users {

    @XmlElement(name = "user")
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
