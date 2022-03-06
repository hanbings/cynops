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

package io.hanbings.cynops.tool.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

@SuppressWarnings("unused")
public class SmtpMailUtils {

    /**
     * 使用 SMTP 发送邮件
     *
     * @param host     主机
     * @param port     端口
     * @param isAuth   是否进行验证
     * @param isSSL    是否开启 SSL
     * @param isDebug  是否开启调试 调试模式拥有更详细的日志
     * @param display  显示的发件人昵称
     * @param from     发件人邮箱
     * @param to       收件人邮箱 可填写多个收件人 用 " , " 分隔
     * @param title    邮件标题
     * @param content  邮件内容
     * @param mail     发件邮箱
     * @param password 发件邮箱密码
     * @param isHTML   是否对邮件进行 HTML 渲染
     */
    public static void sendMail(String host, int port,
                                boolean isAuth, boolean isSSL, boolean isDebug,
                                String display, String from, String to,
                                String title, String content, String mail,
                                String password, boolean isHTML) {
        Properties properties = getSmtpProperties(host, port, mail, password, isAuth, isSSL);
        Session session = getSession(properties, isDebug);
        sendMail(properties, display, from, to, title, content, session, isHTML);
    }

    /**
     * 使用 SMTP 发送邮件
     *
     * @param properties 配置
     * @param display    显示的发件人昵称
     * @param from       发件人邮箱
     * @param to         收件人邮箱 可填写多个收件人 用 " , " 分隔
     * @param title      邮件标题
     * @param content    邮件内容
     * @param session    邮件 Session
     * @param isHTML     是否对邮件进行 HTML 渲染
     */
    public static void sendMail(Properties properties,
                                String display, String from, String to,
                                String title, String content,
                                Session session, boolean isHTML) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from, display, "UTF-8"));
            message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(to));
            message.setSubject(title);
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
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param properties 配置
     * @param isDebug    是否开启调试 调试模式拥有更详细的日志
     * @return 构造完成的 Session
     */
    public static Session getSession(Properties properties, boolean isDebug) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.user")
                        , properties.getProperty("mail.password"));
            }
        });
        session.setDebug(isDebug);
        return session;
    }

    /**
     * @param host     邮件主机
     * @param port     端口
     * @param mail     邮箱账号
     * @param password 邮箱密码
     * @param isAuth   是否需要验证
     * @param isSSL    是否使用 SSL
     * @return 构造完成的 Properties
     */

    public static Properties getSmtpProperties(String host, int port, String mail, String password, boolean isAuth, boolean isSSL) {
        Properties properties = new Properties();
        // 设置基本信息
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.port", String.valueOf(port));

        // 验证
        properties.put("mail.smtp.auth", String.valueOf(isAuth));
        properties.setProperty("mail.user", mail);
        properties.setProperty("mail.password", password);

        // 设置 SSL
        try {
            MailSSLSocketFactory sslSocketFactory = new MailSSLSocketFactory();
            sslSocketFactory.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", String.valueOf(isSSL));
            properties.put("mail.smtp.ssl.socketFactory", sslSocketFactory);
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.port", String.valueOf(port));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        return properties;
    }
}