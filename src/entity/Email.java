package entity;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    private static final String ENCODE = "ISO-2022-JP";


    public void send(YearMonth ym, AccountList al, String email) {
    	final Properties props = new Properties();

        // SMTPサーバーの設定。ここではgooglemailのsmtpサーバーを設定。
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // SSL用にポート番号を変更。
        props.setProperty("mail.smtp.port", "465");

        // タイムアウト設定
        props.setProperty("mail.smtp.connectiontimeout", "60000");
        props.setProperty("mail.smtp.timeout", "60000");

        // 認証
        props.setProperty("mail.smtp.auth", "true");

        // SSLを使用するとこはこの設定が必要。
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");

       //propsに設定した情報を使用して、sessionの作成
        final Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("メールアドレス", "パスワード");
            }
        });



        // ここからメッセージ内容の設定。上記で作成したsessionを引数に渡す。
        final MimeMessage message = new MimeMessage(session);

        try {
            final Address addrFrom = new InternetAddress(
                    "メールアドレス", "Kakeibo-Kingdom", ENCODE);
            message.setFrom(addrFrom);

            final Address addrTo = new InternetAddress(email,
                    "Kakeibo Kingdomのユーザー様", ENCODE);
            message.addRecipient(Message.RecipientType.TO, addrTo);

            // メールのSubject
            message.setSubject("Kakeibo-Kingdomより" + ym.getYear() + "/" + ym.getMonth() + "の出費です。", ENCODE);

            // メール本文。
            message.setText(ym.getYear() + "/" + ym.getMonth() + "の出費は、" + al.getTotal() + "円です。", ENCODE);


            // その他の付加情報。
            message.addHeader("X-Mailer", "blancoMail 0.1");
            message.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // メール送信。
        try {
            Transport.send(message);
        } catch (AuthenticationFailedException e) {
            // 認証失敗
                 e.printStackTrace();
        } catch (MessagingException e) {
            // smtpサーバへの接続失敗
           e.printStackTrace();

        }
    }

}
