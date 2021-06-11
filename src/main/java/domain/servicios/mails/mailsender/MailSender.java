package domain.servicios.mails.mailsender;

public interface MailSender {
  void send(String address, String message);
}
