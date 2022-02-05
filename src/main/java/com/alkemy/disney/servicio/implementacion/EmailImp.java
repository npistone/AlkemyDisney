package com.alkemy.disney.servicio.implementacion;

import com.alkemy.disney.servicio.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailImp implements EmailService {
    @Autowired
    private Environment env;
    /*Aca va el api key*/

    @Value("${com.alkemy.disney.email.sender}")
    private String emailSender;

    /*@Value("${com.alkemy.disney.email.enabled}")
    private boolean enabled;
*/

    @Override
    public void SendWelcomeEmailTo(String to) {
       /* if (!enabled){
            return;
        }*/
        String apiKey = env.getProperty("EMAIL_API_KEY");
        /*Obtenida desde Sender y lo asignamos a variable*/

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(/*Cuerpo del msj, tipo texto plano y contenido*/
                "text/plain",
                "Bienvenido/a a Disney Aplication"
        );

        String subject = "Disney aplication";

        Mail mail = new Mail(fromEmail, subject, toEmail, content); /*Creo un mail del tipo Send*/
        SendGrid sg = new SendGrid(apiKey);/*Envio mi cuenta y mi acceso*/
        Request request = new Request();
        /*Tanto mail sendgrid y request son de la dependencia sengrid*/

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());/*Agarra el mail y lo construye con el build*/
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            System.out.println("Error al enviar el mail");
        }

    }
}
