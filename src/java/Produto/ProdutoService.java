/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

import java.util.List;

/**
 *
 * @author guana
 */
public class ProdutoService {

    ProdutoBean ProdutoBean = new ProdutoBean();
    ProdutoDAO ProdutoDAO = new ProdutoDAO();

    private Integer tela = 0;

    public List<ProdutoBean> lista(String pesquisa) {
        String clausula = " where nome like '%" + pesquisa + "%';";
        return ProdutoDAO.lista(clausula);
    }

    public ProdutoBean salvar(ProdutoBean p) {

        if (p.getId() == null || p.getId().equals("") || p.getId().equals("0")) {
            return ProdutoDAO.inserir(p);
        } else {
            return ProdutoDAO.alterar(p);
        }
    }

    public ProdutoBean deletar(ProdutoBean p) {
        return ProdutoDAO.deletar(p);
    }
}
