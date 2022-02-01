package sgab.controller;

import sgab.model.dto.Exemplar;
import sgab.model.dto.Obra;
import sgab.model.dto.Pessoa;
import sgab.model.exception.PersistenciaException;
import sgab.model.service.GestaoObras;
import sgab.model.service.GestaoPessoasService;
import sgab.model.service.GestaoAcervo;
import sgab.model.service.GestaoEmprestimo;
import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

import sgab.model.dao.AcervoDAO;
import sgab.model.dto.Emprestimo;



public class EmprestimoController {
    
   public static String listar(HttpServletRequest request) {
        String jsp = "";
        try {
            GestaoEmprestimo gestaoEmprestimo = new GestaoEmprestimo();
            List<Emprestimo> emprestimos = gestaoEmprestimo.listarEmprestimo();
            request.setAttribute("listEmprestimo",emprestimos);
            jsp = "/sgab/core/emprestimo/listar.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
 public static String gravarEmprestimo(HttpServletRequest request) {
        String jsp = "";
        try {
            GestaoObras gestaoObras = new GestaoObras();
            GestaoPessoasService gestaoPessoas = new GestaoPessoasService();
            GestaoEmprestimo gestaoEmprestimo = new GestaoEmprestimo();
            GestaoAcervo gestaoAcervo = new GestaoAcervo();

            List<Obra> obras = new LinkedList<>();
            String[] nomeObras = request.getParameter("obras").split("::");
            for(String obra: nomeObras){
                Obra alvo = (Obra) gestaoObras.pesquisarObraNome(obra);
                obras.add(alvo);
            }
            String login = request.getParameter("loginLeitor");
            Pessoa pessoa = gestaoPessoas.pesquisarPorLogin(login);
            Long Id = Long.parseLong(request.getParameter("id"));
            
            Exemplar exemplar = gestaoAcervo.pesquisarExemplar(Id);
        
            gestaoEmprestimo.realizarEmprestimo(exemplar, pessoa);
            
            jsp = "/core/autores/certo.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;

    }
}