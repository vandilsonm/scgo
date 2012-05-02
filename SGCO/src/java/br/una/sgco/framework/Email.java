package br.una.sgco.framework;

//package br.una.sgco.framework;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//
///**
// *
// * @author Dirceu
// */
//public class Email extends Thread {
//
//    private String subject;
//    private String message;
//    private String sendTo;
//    private boolean critical;
//
//    public Email(String subject, String message, String sendTo, boolean critical) {
//        this.subject = subject;
//        this.message = message;
//        this.sendTo = sendTo;
//        this.critical = critical;
//    }
//
//    @Override
//    public void run() {
//        try {
//            send(subject, message, sendTo, critical);
//        } catch (Exception e) {
//        }
//    }
//
//    public static void sendParallels(String subject, String message, String sendTo, boolean critical) throws Exception {
//        Email e = new Email(subject, message, sendTo, critical);
//        e.start();
//    }
//
//    public static void send(String subject, String message, String sendTo, boolean critical) throws Exception {
//        send(subject, message, sendTo, false, false, critical);
//    }
//
//    public static void send(String subject, String message, String sendTo, boolean email2, boolean resumo, boolean critical) throws Exception {
//        if (critical) {
//            try {
//
//                if (!sendTo.trim().equals("")) {
//                    String host = "smtp.gmail.com";
//                    String username = "pqlouback@gmail.com";
//                    String password = "";
//
//                    Properties props = new Properties();
//                    props.put("mail.smtps.auth", "true");
//                    props.put("mail.starttls.enable", "true");
//                    props.put("mail.smtp.port", "587");
//                    Session session = Session.getDefaultInstance(props, null);
//                    MimeMessage msg = new MimeMessage(session);
//                    String[] v = sendTo.split("\\;");
//                    for (String email : v) {
//                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//                    }
//                    msg.setSubject(subject);
//
//                    msg.setContent(message, "text/html; charset=ISO-8859-1");
//                     set the message content here
//                    Transport t = session.getTransport("smtp");
//                    try {
//                        t.connect(host, username, password);
//                        t.sendMessage(msg, msg.getAllRecipients());
//                    } finally {
//                        t.close();
//                    }
//                } else {
//                    throw new Exception("Erro ao enviar e-mail");
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//}

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp  
public class Email {

    private String mailSMTPServer;
    private String mailSMTPServerPort;

    /*
     * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL e
     * a porta usada por ele
     */

    /*
     * caso queira mudar o servidor e a porta, so enviar para o contrutor os
     * valor como string
     */

    Email(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor  
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
    }

    public Email() {
        mailSMTPServer = "smtp.gmail.com";
        mailSMTPServerPort = "465";
    }

    public void sendMail(String from, String to, String subject, String message) {

        Properties props = new Properties();

        // quem estiver utilizando um SERVIDOR PROXY descomente essa parte e atribua as propriedades do SERVIDOR PROXY utilizado  
                /*
         * props.setProperty("proxySet","true");
         * props.setProperty("socksProxyHost","192.168.155.1"); // IP do
         * Servidor Proxy props.setProperty("socksProxyPort","1080"); // Porta
         * do servidor Proxy
         */

        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL  
        props.put("mail.smtp.auth", "true"); //ativa autenticacao  
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)  
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta  
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Cria um autenticador que sera usado a seguir  
        SimpleAuth auth = null;
        auth = new SimpleAuth("sgc.golaco", "golaco123");

        //Session - objeto que ira realizar a conexão com o servidor  
        /*
         * Como há necessidade de autenticação é criada uma autenticacao que é
         * responsavel por solicitar e retornar o usuário e senha para
         * autenticação
         */
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email  

        //Objeto que contém a mensagem  
        Message msg = new MimeMessage(session);

        try {
            //Setando o destinatário  
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //Setando a origem do email  
            msg.setFrom(new InternetAddress(from));
            //Setando o assunto  
            msg.setSubject(subject);
            //Setando o conteúdo/corpo do email  
            msg.setContent(message, "text/plain");

        } catch (Exception e) {
            System.out.println(">> Erro: Completar Mensagem");
            e.printStackTrace();
        }

        //Objeto encarregado de enviar os dados para o email  
        Transport tr;
        try {
            tr = session.getTransport("smtp"); //define smtp para transporte  
            /*
             * 1 - define o servidor smtp 2 - seu nome de usuario do gmail 3 -
             * sua senha do gmail
             */
            tr.connect(mailSMTPServer, "sgc.golaco", "golaco123");
            msg.saveChanges(); // don't forget this  
            //envio da mensagem  
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block  
            System.out.println(">> Erro: Envio Mensagem");
            e.printStackTrace();
        }

    }
}
class SimpleAuth extends Authenticator {

    public String username = null;
    public String password = null;

    public SimpleAuth(String user, String pwd) {
        username = user;
        password = pwd;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
