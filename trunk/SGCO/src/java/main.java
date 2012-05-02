
import br.una.sgco.framework.Email;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Jana Louback
 */
public class main {

    public static void main(String args[]) throws Exception {
        Email semail = new Email();
                
        semail.sendMail("sgc.golaco", "pqlouback@gmail.com", "Confirmação de presença", "testando");
        System.out.println("email enviado com sucesso!");
    }
}
