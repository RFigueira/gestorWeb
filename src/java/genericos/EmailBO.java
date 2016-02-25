package genericos;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailBO {

    private String cCO;
    private String mensagem;
    private String cc;
    private String emailFrom;
    private String emailTo;
    private String nomeTo;
    private String nomeFrom;

    public EmailBO() {
    }

    public EmailBO(String cCO, String mensagem, String cc, String emailTo, String emailFrom, String nomeTo, String nomeFrom) {
        this.setCc(cc);
        this.setEmailFrom(emailFrom);
        this.setEmailTo(emailTo);
        this.setMensagem(mensagem);
        this.setcCO(cCO);
        this.setNomeFrom(nomeFrom);
        this.setNomeTo(nomeTo);

    }

    public String getcCO() {
        return cCO;
    }

    public void setcCO(String cCO) {
        this.cCO = cCO;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getNomeTo() {
        return nomeTo;
    }

    public void setNomeTo(String nomeTo) {
        this.nomeTo = nomeTo;
    }

    public String getNomeFrom() {
        return nomeFrom;
    }

    public void setNomeFrom(String nomeFrom) {
        this.nomeFrom = nomeFrom;
    }

    public static Email conectaEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(ConfigurationBO.HOSTNAME);
        email.setSmtpPort(587);
        email.setAuthentication(ConfigurationBO.USERNAME, ConfigurationBO.PASSWORD);
        email.setTLS(false);
        email.setSSL(true);   
        email.setFrom(ConfigurationBO.EMAILORIGEM);
        return email;
    }

    public static void enviaEmail(EmailBO mensagem) throws EmailException {
        Email email = conectaEmail();
        email.setSubject("teste") ;//(mensagem.getNomeTo());
        email.setMsg(mensagem.getMensagem());
        email.addTo("foolha@gmail.com") ;//("foolha@gmail.com");
        String resposta = email.send();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " + mensagem.getEmailTo(), resposta));
    }
    
    public static void enviaEmailT() throws EmailException {
        Email email = conectaEmail();
        email.setSubject("teste") ;//(mensagem.getNomeTo());
        email.setMsg(" teste teste teste teste teste");
        email.addTo("foolha@gmail.com") ;//("foolha@gmail.com");
        String resposta = email.send();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: ", resposta));
    }

}

//Ainda não consegue enviar e-mails?
//Se você tentou configurar o servidor SMTP na porta 465 (com SSL) e
//587 (com TLS), mas ainda está tendo problemas para enviar e-mails, tente configurar seu SMTP para usar a porta 25 (com SSL).
