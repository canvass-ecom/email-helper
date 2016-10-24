/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper;

import com.cnv.emailhelper.sendgrid.SendgridCredentials;
import com.cnv.emailhelper.sendgrid.SendgridEmailProvider;

/**
 *
 * @author Owner
 */
public final class EmailHelperBuilder {

    public static EmailHelper buildProvider(Credentials credentials) throws Exception {
        if (credentials instanceof SendgridCredentials) {
            return new SendgridEmailProvider((SendgridCredentials) credentials);
        }
        throw new Exception("No Implemention available for the given provider");
    }

    public static EmailSender buildSender(Credentials credentials) throws Exception {
        return buildProvider(credentials);
    }

}
