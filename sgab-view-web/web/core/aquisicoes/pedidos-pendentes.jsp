<%-- 
    Document   : pedidos-pendentes
    Created on : 23 de jan. de 2022, 19:49:26
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sgab.model.dto.Aquisicao" %>
<%@page import="java.util.List" %>

<%@include file="/core/header.jsp" %>
<style>
                #escolha{
                    display: flex;
                    position: relative;
                    justify-content: space-around;

                }

                #escolha::after{
                    content: "";
                    background: black;
                    position: absolute;
                    bottom: 0;
                    left: 50%;
                    height: 100%;
                    width: 1px;
                }
</style>
<center>
                <h3>Pedidos Pendentes</h3>
                <a href="/sgab/core/aquisicoes/pedir-passo1.jsp">Fazer nova Aquisição</a>
                <form name="frmAquisicao" method="post">
                      <input type="hidden" name="aquisicaoId" value="">
                      <table id="usuario" style="width: 50%;">
                          <tr>
                            <th class="hpesquisa"></th>
                            <th>ID</th>
                            <th>OBRA</th>
                            <th style="width: 50%;"></th>
                          </tr>
                          <% 
                            List<Aquisicao> aquisicoes = (List<Aquisicao>) request.getAttribute("listAquisicoesPendentes");
                            for(Aquisicao aquisicao : aquisicoes){ %>
                          <tr>
                            <td><a href="/sgab/main?acao=ConfereAquisicao&aquisicaoId=<%=aquisicao.getId()%>">&#128270</a></td>
                            <td><%= aquisicao.getId() %></td>
                            <td><%= aquisicao.getObra().getTitulo() %></td>
                            <td id="escolha" style="background-color: #aaaaaa; ">
                                <input type="button" class="button" style="
                                    display: inline;  
                                    height: 26px; 
                                    border-color: #aaaaaa; 
                                    background-color: #aaaaaa;
                                    color: black;
                                    border-radius: 0;
                                    
                                " value="Recusar" onclick="recusar(<%=aquisicao.getId()%>,document.frmAquisicao)">
                                <input type="button" class="button" style="
                                    display: inline; 
                                    height: 26px; 
                                    border-color: #aaaaaa; 
                                    background-color: #aaaaaa;
                                    color: black;
                                    border-radius: 0;
                                " value="Pedir" onclick="pedir(<%=aquisicao.getId()%>,document.frmAquisicao)"></td>
                          </tr>
                          <% } %>
              </table>
              </form>
              
          </center>
<script>
    function recusar(id, frm){
        frm.aquisicaoId.value = id;
        frm.action = "/sgab/main?acao=RecusarAquisicao";
        frm.submit();
    }
    function pedir(id, frm){
        frm.aquisicaoId.value = id;
        frm.action = "/sgab/main?acao=AceitarAquisicao";
        frm.submit();
    }
</script>
<%@include file="/core/footer.jsp" %>