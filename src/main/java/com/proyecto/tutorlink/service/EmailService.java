
package com.proyecto.tutorlink.service;
import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import com.proyecto.tutorlink.entity.Reservation;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private Resend resend = new Resend("re_boRdhn1C_QBFSki1ovSTQzY4dS95jQVS4");

    public void sendReservationConfirmationEmail(Reservation reservation) {
        //String recipientEmail = reservation.getUser().getEmail();
        String recipientEmail = "manuel.herrera.m@gmail.com";
        String subject = "Confirmación de Reserva";
        String content = buildEmailContent(reservation);

        CreateEmailOptions options = CreateEmailOptions.builder()
                .from("TutorLink <onboarding@resend.dev>")
                .to(recipientEmail)
                .subject(subject)
                .html(content)
                .build();

        try {
            CreateEmailResponse response = resend.emails().send(options);
            System.out.println("Correo enviado exitosamente: " + response);
        } catch (ResendException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String buildEmailContent(Reservation reservation) {
        return "<html><head><style>" +
                "@media only screen and (max-width: 620px) {" +
                "  body { padding: 10px; }" +
                "  ul { padding: 10px; }" +
                "}" +
                "body { font-family: Arial, sans-serif; color: #333; background-color: #f4f4f4; margin: 0 auto; max-width: 600px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }" +
                "h1 { color: #0275d8; }" +
                "p { line-height: 1.6; }" +
                "ul { background: #fff; padding: 20px; border: 1px solid #ddd; list-style-type: none; }" +
                "li { margin-bottom: 10px; color: #555; }" +
                "</style></head><body>" +
                "<h1>Confirmación de Reserva</h1>" +
                "<p>Hola " + reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName() +
                ", gracias por reservar con nosotros. Aquí están los detalles de tu reserva con el profesor " +
                reservation.getTeacher().getName() + ":</p>" +
                "<ul>" +
                "<li>Fecha: " + reservation.getStartTime().toLocalDate() + "</li>" +
                "<li>Hora: " + reservation.getStartTime().toLocalTime() + " - " + reservation.getEndTime().toLocalTime() + "</li>" +
                "</ul>" +
                "<p>Por favor conéctate a tiempo y si no puedes asistir, anula con anticipación.</p>" +
                "<p>Saludos,<br>Tu Equipo de TutorLink</p>" +
                "</body></html>"
                ;
    }
}
