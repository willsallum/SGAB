package sgab.model.dto.util;

import sgab.model.dao.PessoasDAO;
import sgab.model.dto.Pessoa;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdministradoresHelper {
        public static List<String> validarPessoa(Pessoa pessoa, PessoasDAO pessoas) {
        List<String> exMsgs = new LinkedList<>();

        if(!validarEmail(pessoa.getEmail())){
            exMsgs.add("O email da pessoa não é válido.");
        }
        
        if(!validarSenha(pessoa.getSenha())){
            exMsgs.add("A senha da pessoa precisa ter 8 caracteres, pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
        }
        
        if(pessoas.pesquisarLogin(pessoa.getLogin()) != null){
            exMsgs.add("O Login precisa ser único.");
        }
        
        if(pessoas.pesquisarCpf(pessoa.getCpf()) != null) {
            exMsgs.add("O CPF inserido já foi cadastrado.");
        }
        
        if(!validarNome(pessoa.getNome())){
            exMsgs.add("O nome da pessoa não é válido.");
        }

        if(!validarCpf(pessoa.getCpf())){
            exMsgs.add("O CPF inserido não é válido");
        }

        return exMsgs;
    }
    
    public static List<String> validarAlteracao(Pessoa pessoa, PessoasDAO pessoas){
        List<String> exMsgs = new LinkedList<>();
        
        if(!validarEmail(pessoa.getEmail())){
            exMsgs.add("O email da pessoa não é válido.");
        }
        
        if(!pessoa.getSenha().equals(pessoas.pesquisar(pessoa.getId()).getSenha())){
            if(!validarSenha(pessoa.getSenha())){
                exMsgs.add("A senha da pessoa precisa ter 8 caracteres, pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
            }
        }
        
        if(!validarNome(pessoa.getNome())){
            exMsgs.add("O nome da pessoa não é válido.");
        }

        return exMsgs;
    }

    public static boolean validarCpf(Long cpf){
        if(cpf == null){
            return false;
        }
        
        String c = Long.toString(cpf);

        String regexCpf = "^[0-9]{11}$";
        Pattern validarCpf = Pattern.compile(regexCpf);
        Matcher matcher = validarCpf.matcher(c);

        if(matcher.matches() == false){
            return false;
        }

        return true;
    }

    public static boolean validarNome(String nome){
        if(nome == null || nome.trim().equals("")){
            return false;
        }
        
        String regexNomeCompleto = "^[A-z]+([ ][A-z]+)+";
        Pattern validarNome = Pattern.compile(regexNomeCompleto);
        Matcher matcher = validarNome.matcher(nome);

        if(matcher.matches() == false){
            return false;
        }

        return true;
    }

    public static boolean validarSenha(String senha) {
        String regexSenha = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
        Pattern validaSenha = Pattern.compile(regexSenha);
        Matcher matcher = validaSenha.matcher(senha);

        return matcher.matches();
    }

    public static boolean validarEmail(String email){
        String regexEmail = "^[^0-9][A-z0-9_]+([.][A-z0-9_]+)*[@][A-z0-9_]+([.][A-z0-9_]+)*[.][A-z]{2,4}$";
        Pattern validaEmail = Pattern.compile(regexEmail);
        Matcher matcher = validaEmail.matcher(email);
        
        return matcher.matches();
    }
    public static List<String> validarAdministrador(Pessoa pessoa, PessoasDAO pessoas) {
        List<String> mensagens = validarPessoa(pessoa,pessoas);
        if(pessoa.getTipo()!=PessoaTipo.ADMINISTRADOR){
            mensagens.add("Pessoa não é um administrador!");
        }
        return mensagens;
    }
    public static List<String> validarAdministradorAlteracao(Pessoa pessoa, PessoasDAO pessoas) {
        List<String> mensagens = validarAlteracao(pessoa,pessoas);
        if(pessoa.getTipo()!=PessoaTipo.ADMINISTRADOR){
            mensagens.add("Pessoa não é um administrador!");
        }
        return mensagens;
    }
    public static List<String> validarGestor(Pessoa pessoa, PessoasDAO pessoas) {
        List<String> mensagens = validarPessoa(pessoa,pessoas);
        if(pessoa.getTipo()!=PessoaTipo.GESTOR){
            mensagens.add("Pessoa não é um gestor!");
        }
        return mensagens;
    }
    public static List<String> validarGestorAlteracao(Pessoa pessoa, PessoasDAO pessoas) {
        List<String> mensagens = validarAlteracao(pessoa,pessoas);
        if(pessoa.getTipo()!=PessoaTipo.GESTOR){
            mensagens.add("Pessoa não é um gestor!");
        }
        return mensagens;
    }
}
