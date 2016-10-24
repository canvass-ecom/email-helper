/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper;

/**
 *
 * @author Owner
 */
public interface EmailSender {

    void sendEmail(Sender sender, Receiver receiver, EmailMessage emailMessage);
}
