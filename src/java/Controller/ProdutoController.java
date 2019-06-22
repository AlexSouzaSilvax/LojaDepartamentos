/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Produto.ProdutoBean;
import Produto.ProdutoService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author guana
 */
@ManagedBean
@ViewScoped
public class ProdutoController {

    ProdutoService ProdutoService = new ProdutoService();
    ProdutoBean Produto = new ProdutoBean();
    List<ProdutoBean> listaProdutos = new ArrayList<>();

    private String pesquisa;
    private Integer tela = 0;

    public void mudarTela(Integer pTela) {
        tela = pTela;
    }

    public void voltar() {
        setPesquisa("");
        listaProdutos.clear();
        tela = 0;
    }

    public void novo() {
        Produto = new ProdutoBean();
        tela = 1;
    }

    public void lista() {
        listaProdutos = ProdutoService.lista(pesquisa);
    }

    public void seleciona(ProdutoBean p) {
        Produto = p;
        tela = 1;
    }

    public void salvar() {

        ProdutoBean p = new ProdutoBean();

        if (p.getId() == null) {
            p.setId("");
        } else {
            p.setId(p.getId());
        }

        p = Produto;
        ProdutoService.salvar(p);

        ProdutoBean newProduto = new ProdutoBean();
        voltar();
    }

    public void deletar() {

        ProdutoBean user = new ProdutoBean();

        user.setId(Produto.getId());
        ProdutoService.deletar(user);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário deletado com sucesso!", ""));
        System.out.println("Deletado com sucesso. ID: " + user.getId());

        ProdutoBean newUser = new ProdutoBean();
        voltar();
    }

    public ProdutoService getProdutoService() {
        return ProdutoService;
    }

    public void setProdutoService(ProdutoService ProdutoService) {
        this.ProdutoService = ProdutoService;
    }

    public ProdutoBean getProduto() {
        return Produto;
    }

    public void setProduto(ProdutoBean Produto) {
        this.Produto = Produto;
    }

    public List<ProdutoBean> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<ProdutoBean> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Integer getTela() {
        return tela;
    }

    public void setTela(Integer tela) {
        this.tela = tela;
    }

}
