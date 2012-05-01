
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
        Email.send("teste", "recebeu este e-mail?", "janaina.magalhaes@aorta.com.br", true);
        System.out.println("email enviado com sucesso!");
    }
}
