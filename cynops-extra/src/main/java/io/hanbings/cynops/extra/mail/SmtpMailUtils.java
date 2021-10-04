/*
 * Copyright (c) 2021 Hanbings / hanbings Cynops Toolbox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.hanbings.cynops.extra.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class SmtpMailUtils {
    public static void sendMail(String host, String display, String from, String to
            , String title, String content
            , String mail, String password
            , boolean isHTML, boolean isSSL, boolean isDebug, boolean isDate, boolean isAuth) {
        Properties properties = getSmtpProperties(host, mail, password, isSSL, isAuth);
        sendMail(properties
                , display, from, to
                , title, content
                , getAuth(properties, mail, password)
                , isHTML, isDebug, isDate);
    }

    public static void sendMail(Properties properties, String display, String from, String to
            , String title, String content
            , Session session
            , boolean isHTML, boolean isDebug, boolean isDate) {
        session.setDebug(isDebug);
        Message message = new MimeMessage(session);
        try {
            message.addFrom(InternetAddress.parse(from));
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(to));
            message.setSubject(title);
            if (isDate) message.setSentDate(new Date());
            if (isHTML) {
                Multipart multipart = new MimeMultipart();
                MimeBodyPart body = new MimeBodyPart();
                //HTML正文
                body.setContent(content, "text/html; charset=UTF-8");
                multipart.addBodyPart(body);
                message.setContent(multipart);
            } else {
                message.setContent(content, "text/html;charset=UTF-8");
            }
            message.saveChanges();
            // 发送
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static Session getAuth(Properties properties, String mail, String password) {
        return Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });
    }

    public static Properties getSmtpProperties(String host, String mail, String password, boolean isSSL, boolean isAuth) {
        Properties properties = new Properties();
        // 设置基本信息
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.transport.protocol", "smtp");

        // 验证
        properties.put("mail.smtp.auth", String.valueOf(isAuth));
        if (isAuth) properties.setProperty("mail.user", mail);
        if (isAuth) properties.setProperty("mail.password", password);

        // 设置 SSL
        try {
            MailSSLSocketFactory sslSocketFactory = new MailSSLSocketFactory();
            properties.put("mail.smtp.ssl.enable", String.valueOf(isSSL));
            properties.put("mail.smtp.ssl.socketFactory", sslSocketFactory);
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            if (isSSL) properties.setProperty("mail.smtp.port", "465");
            if (isSSL) properties.setProperty("mail.smtp.socketFactory.port", "465");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return properties;
    }
}