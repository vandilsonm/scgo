package br.una.sgco.framework;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Dirceu
 */
public class Email extends Thread {

    private String subject;
    private String message;
    private String sendTo;
    private boolean critical;

    public Email(String subject, String message, String sendTo, boolean critical) {
        this.subject = subject;
        this.message = message;
        this.sendTo = sendTo;
        this.critical = critical;
    }

    @Override
    public void run() {
        try {
            send(subject, message, sendTo, critical);
        } catch (Exception e) {
        }
    }

    public static void sendParallels(String subject, String message, String sendTo, boolean critical) throws Exception {
        Email e = new Email(subject, message, sendTo, critical);
        e.start();
    }

    public static void send(String subject, String message, String sendTo, boolean critical) throws Exception {
        send(subject, message, sendTo, false, false, critical);
    }

    public static void send(String subject, String message, String sendTo, boolean email2, boolean resumo, boolean critical) throws Exception {
        if (critical) {
            try {

                if (!sendTo.trim().equals("")) {
                    String host = "smtp.gmail.com";
                    String username = "mail@inteligencia.mobi";
                    String password = "M4m4m14001";

                    Properties props = new Properties();
                    props.put("mail.smtps.auth", "true");
                    props.put("mail.starttls.enable", "true");
                    props.put("mail.smtp.port", "587");
                    Session session = Session.getDefaultInstance(props, null);
                    MimeMessage msg = new MimeMessage(session);
                    String[] v = sendTo.split("\\;");
                    for (String email : v) {
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                    }
                    msg.setSubject(subject);

                    msg.setContent(message, "text/html; charset=ISO-8859-1");
                    // set the message content here
                    Transport t = session.getTransport("smtps");
                    try {
                        t.connect(host, username, password);
                        t.sendMessage(msg, msg.getAllRecipients());
                    } finally {
                        t.close();
                    }
                } else {
                    throw new Exception("Erro ao enviar e-mail");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
