/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controledeestoque.controller;

import controledeestoque.dao.ProdutosDAO;
import controledeestoque.view.BaixarProdutosView;
import controledeestoque.view.InserirProdutosView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




/**
 *
 * @author Joao
 */
public class ProdutoController {
   public void Inserir (int id, int qtde){
    try{
       ProdutosDAO produtosDAO = new ProdutosDAO();
       Integer quantidadeDeEstoque;
       quantidadeDeEstoque = produtosDAO.buscarQuantidadeDeEstoque(id);
       if (quantidadeDeEstoque != null){
           produtosDAO.atualizar(id, qtde + quantidadeDeEstoque);
           JOptionPane.showMessageDialog(null,"Estoque Atualizado", "operaçao Confirmada", JOptionPane.INFORMATION_MESSAGE);
           
       }
       else{
           JOptionPane.showConfirmDialog(null, "Codigo Invalido", "Operaçao Incorreta", JOptionPane.ERROR);
           
       }
      } 
      catch(SQLException ex){
         Logger.getLogger(InserirProdutosView.class.getName()).log(Level.SEVERE, null, ex); 
         JOptionPane.showConfirmDialog(null, "Codigo Invalido", "Operaçao Incorreta", JOptionPane.ERROR);
     }
   }

   public void Baixar (int id, int qtde){
    try{
       ProdutosDAO produtosDAO = new ProdutosDAO();
       Integer quantidadeDeEstoque;
       quantidadeDeEstoque = produtosDAO.buscarQuantidadeDeEstoque(id);
       if (quantidadeDeEstoque != null){
           produtosDAO.atualizar(id, qtde - quantidadeDeEstoque);
           JOptionPane.showMessageDialog(null,"Estoque Atualizado", "Sucesso", JOptionPane.OK_OPTION);
           
       }
       else{
           JOptionPane.showConfirmDialog(null, "Codigo Invalido", "Erro", JOptionPane.ERROR);
           
       }
    } 
   
      catch(SQLException ex){
         Logger.getLogger(BaixarProdutosView.class.getName()).log(Level.SEVERE, null, ex);  
         JOptionPane.showMessageDialog(null, "Digite os valores numericos", "Erro", JOptionPane.ERROR);
     }
  }
}  
  
   
   

