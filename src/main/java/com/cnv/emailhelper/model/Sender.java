/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper.model;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Owner
 */
public class Sender {

    private String email;
    private String name;

    public Sender(String email) {
        this.email = email;
    }

    public Sender(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InternetAddress create() throws UnsupportedEncodingException, AddressException {
        if (name != null && !name.isEmpty()) {
            return new InternetAddress(email, name);
        } else {
            return new InternetAddress(email);
        }
    }

    @Override
    public String toString() {
        return "Sender{" + "email=" + email + ", name=" + name + '}';
    }

}
