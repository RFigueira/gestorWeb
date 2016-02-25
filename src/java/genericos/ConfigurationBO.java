/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rodrigo
 */
public class ConfigurationBO {

    public static final String pathArquivo = "/resource/arquivo/";
    public static final String HOSTNAME = "smtpi.kinghost.net";
    public static final String USERNAME = "gestorwebifsul@codepampa.kinghost.net";
    public static final String PASSWORD = "gestorweb88";
    public static final String EMAILORIGEM = "gestorwebifsul@codepampa.kinghost.net";

    public static final String mensagemCadastroSucesso = "Dados cadastrados com sucesso";
    public static final String mensagemCadastroErro = "Erro ao cadastrar dados.";
    public static final String mensagemAlteracaoSucesso = "Dados alterados com sucesso";
    public static final String mensagemAlteracaoErro = "Erro ao alterar dados.";
    public static final String mensagemExcluidoSucesso = "Dados excluido com sucesso";
    public static final String mensagemExcluidoErro = "Erro ao excluir";
    public static final String mensagemLoginOuSenhaIvalido = "Login ou senha iválidos, tenta novamento.";

    public static Date stringParaData(String data) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(data);
        return date;
    }

    public static String DataParaString(Date data) throws ParseException {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        String result = out.format(data);
        return result;
    }

}
