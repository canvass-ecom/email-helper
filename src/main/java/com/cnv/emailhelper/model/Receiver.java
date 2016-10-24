/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Owner
 */
public class Receiver {

    private List<String> to = new ArrayList<String>();
    private List<String> bcc = new ArrayList<String>();

    public Receiver(String... receivers) {
        to.addAll(Arrays.asList(receivers));
    }

    public Receiver add(String email) {
        to.add(email);
        return this;
    }

    public Receiver addBcc(String... emails) {
        bcc.addAll(Arrays.asList(emails));
        return this;
    }

    public List<String> getTo() {
        return to;
    }

    public List<String> getBcc() {
        return bcc;
    }

    @Override
    public String toString() {
        return "Receiver{" + "emails=" + to + '}';
    }

}
