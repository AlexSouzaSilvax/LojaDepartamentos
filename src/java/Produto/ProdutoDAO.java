package Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import util.Conexao;

public class ProdutoDAO {

    public ProdutoBean inserir(ProdutoBean p) {

        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps;

            ps = conexao.prepareStatement("INSERT INTO `produto` (`nome`,`valorVenda`,`valorCompra` ) VALUES (?,?,?)");

            ps.setString(1, p.getNome());
            //ps.setString(2, p.getQtdEstoque());
            ps.setDouble(2, p.getValorVenda());
            ps.setDouble(3, p.getValorCompra());

            ps.execute();
            Conexao.fecharConexao();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto salvo com sucesso!", ""));
            System.out.println("Salvo com sucesso. ID: " + p.getId());
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar DAO " + ex.getMessage());
        }
        return p;
    }

    public ProdutoBean alterar(ProdutoBean p) {
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps;
            ps = conexao.prepareStatement("update produto set nome = ?, valorVenda = ?, valorCompra = ? where id = ?");

            ps.setString(1, p.getNome());
            // ps.setString(2, p.getQtdEstoque());
            ps.setDouble(2, p.getValorVenda());
            ps.setDouble(3, p.getValorCompra());

            ps.setString(4, p.getId());

            ps.execute();
            Conexao.fecharConexao();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto alterado com sucesso!", ""));
            System.out.println("Alterado com sucesso. ID: " + p.getId());
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar DAO " + ex.getMessage());
        }
        return p;
    }

    public List<ProdutoBean> lista(String clausula) {

        try {

            Connection conexao = Conexao.getConexao();
            List<ProdutoBean> listaProdutos = new ArrayList<>();
            PreparedStatement ps = conexao.prepareStatement("select * from produto " + clausula);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProdutoBean p = new ProdutoBean();

                p.setId(rs.getString("id"));
                p.setNome(rs.getString("nome"));
                p.setQtdEstoque(rs.getString("qtdEstoque"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setValorCompra(rs.getDouble("valorCompra"));

                listaProdutos.add(p);

            }
            ps.execute();
            ps.close();
            Conexao.fecharConexao();

            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar DAO " + ex.getMessage());
            return null;
        }
    }

    public ProdutoBean deletar(ProdutoBean p) {

        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps;
            ps = conexao.prepareStatement("delete from `produto` where id = ?");

            ps.setString(1, p.getId());

            ps.execute();
            Conexao.fecharConexao();

        } catch (SQLException ex) {
            System.out.println("Erro ao deletar DAO " + ex.getMessage());
        }
        return p;
    }

}
