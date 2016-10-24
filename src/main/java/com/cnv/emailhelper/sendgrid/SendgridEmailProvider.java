/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.emailhelper.sendgrid;

import com.cnv.emailhelper.EmailHelper;
import com.cnv.emailhelper.EmailMessage;
import com.cnv.emailhelper.Receiver;
import com.cnv.emailhelper.Sender;
import com.cnv.emailhelper.Utill;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Owner
 */
public class SendgridEmailProvider implements EmailHelper {

    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";

    private SendgridCredentials credentials;

    public SendgridEmailProvider(SendgridCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     *
     * @param sender
     * @param receiver
     * @param emailMessage
     */
    @Override
    public void sendEmail(Sender sender, Receiver receiver, EmailMessage emailMessage) {
        boolean send = false;
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new SendgridAuthenticator(credentials);
            Session mailSession = Session.getDefaultInstance(props, auth);
            // uncomment for debugging infos to stdout
            mailSession.setDebug(false);

            System.out.print("\n##############################################");
            //System.out.print("\n**********************************");
            System.out.print("\nSending Mail from: " + sender);
            System.out.print("\nto: " + receiver);
            //System.out.print("\n**********************************");
            Transport transport = mailSession.getTransport();

            String htmlText = emailMessage.getContent();
            MimeMessage message = new MimeMessage(mailSession);

            Multipart multipart = new MimeMultipart("alternative");

            BodyPart textPart = new MimeBodyPart();
            BodyPart htmlPart = new MimeBodyPart();

            textPart.setText(Utill.getPlainTextVersion(htmlText));
            textPart.setHeader("Content-Transfer-Encoding", "7bit");

            htmlPart.setContent(htmlText, "text/html; charset=utf-8");
            //htmlPart.setHeader("Content-Transfer-Encoding", "7bit");

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);
            message.setHeader("Content-Transfer-Encoding", "7bit");
            message.setFrom(sender.create());

            message.setSubject(emailMessage.getSubject());
            message.setReplyTo(message.getFrom());
            for (String toAddress : receiver.getTo()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }

            // adding the bcc
            List<String> bcc = receiver.getBcc();
            if (bcc != null && !bcc.isEmpty()) {
                for (String toAddress : bcc) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddress));
                }
            }

            System.out.print("\n##############################################");
            System.out.print("\nSendgrid Account details using :" + credentials);
            System.out.print("\n##############################################");

            //transport.connect();
            //Changed By naga for proper user authentication
            transport.connect(credentials.getUserName(), credentials.getPassword());
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            Address[] res = message.getRecipients(Message.RecipientType.TO);
            System.out.print("Total Recipients Are : " + res.length);
            // adding the bcc
            if (bcc != null && !bcc.isEmpty()) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
            }

            transport.close();
            send = true; // marking as send
        } catch (MessagingException e) {
            send = false; // marking as send
            System.out.print("\nError While Sending the E-Mail : " + e);
        } catch (UnsupportedEncodingException e) {
            send = false; // marking as send
            System.out.print("\nError While Sending the E-Mail : " + e);
        }
    }
}
