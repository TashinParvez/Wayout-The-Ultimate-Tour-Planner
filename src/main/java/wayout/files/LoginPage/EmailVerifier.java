package wayout.files.LoginPage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.json.JSONObject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailVerifier extends TemporaryData {
    private static final String API_KEY ="516ab40faaec419288e3d2390d74ccf4";
    private static final String VERIFY_API_URL = "https://api.zerobounce.net/v2/validate?api_key=" + API_KEY;

    public void verifyEmail(String U_email) {
        String verifyUrl = VERIFY_API_URL + "&email=" + U_email;

        try {
            URL url = new URL(verifyUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            String status = jsonResponse.getString("status");

            if (status.equals("valid")) {
                // send mail


                String email = "applicationwayout@gmail.com";
                String password ="ejntpurrhwoplqcq";
                String to = email_z;
                String subject = "Verify your email";
                String message = "Here is your verification code: \n\n"+generated_code+"\n\nPlease enter this verification code to confirm your account\n";

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl.protocols", "TLSv1.2");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl.trust", "*");


                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

                try {
                    Message mimeMessage = new MimeMessage(session);
                    mimeMessage.setFrom(new InternetAddress(email));
                    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    mimeMessage.setSubject(subject);
                    mimeMessage.setText(message);

                    Transport.send(mimeMessage);

                    System.out.println("Email sent successfully!");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }



            } else {
                Alert alert = new Alert(AlertType.WARNING, "Email address is not valid.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Failed to verify email address.");
            alert.showAndWait();
        }
    }
}