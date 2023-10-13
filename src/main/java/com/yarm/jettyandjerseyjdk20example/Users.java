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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append("{FirstName=\"").append(user.getFirstName()).append("\",")
                    .append(" LastName=\"").append(user.getLastName()).append("\"}");
        }
        return sb.toString();
    }
}
