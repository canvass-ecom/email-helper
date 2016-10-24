/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper;

import com.cnv.emailhelper.model.Receiver;
import com.cnv.emailhelper.model.Sender;
import com.cnv.emailhelper.model.EmailMessage;

/**
 *
 * @author Owner
 */
public interface EmailSender {

    void sendEmail(Sender sender, Receiver receiver, EmailMessage emailMessage);
}
