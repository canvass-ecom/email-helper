/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper.sendgrid;

import javax.mail.PasswordAuthentication;

/**
 *
 * @author Owner
 */
public class SendgridAuthenticator extends javax.mail.Authenticator {

    private SendgridCredentials credentials;

    public SendgridAuthenticator(SendgridCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(credentials.getUserName(), credentials.getPassword());
    }
}
