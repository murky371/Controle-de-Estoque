/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controledeestoque.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controledeestoque.dao.ProdutosDAO;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Date;


/**
 *
 * @author Joao
 */
public class RelatorioDeEstoque {
    private  String local; 
    private  PdfPTable table;
    
    public RelatorioDeEstoque(String local){
        this.local = local;
        this.table = new PdfPTable(3);
    }
    public void imprimeTitulo(String linha){
        PdfPCell cellcodigo, cellestoque, celldescricao, celltitulo, cellbranco;
        cellbranco = new PdfPCell(new Paragraph(" "));
        celltitulo = new PdfPCell(new Paragraph(linha, new Font(Font.FontFamily.UNDEFINED, 14, Font.BOLD)));
        cellcodigo = new PdfPCell(new Paragraph("Codigo", new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD)));
        cellestoque = new PdfPCell(new Paragraph("Estoque", new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD)));
        celldescricao = new PdfPCell(new Paragraph("Descriçao", new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD)));
        celltitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celltitulo.setColspan(3);
        celltitulo.setBorder(0);
        cellcodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellestoque.setHorizontalAlignment(Element.ALIGN_CENTER);
        celldescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellbranco.setColspan(3);
        cellbranco.setBorder(0);
        table.addCell(cellbranco);
        table.addCell(cellbranco);
        table.addCell(celltitulo);
        table.addCell(cellcodigo);
        table.addCell(cellestoque);
        table.addCell(celldescricao);
    }
    public void imprimeCorpo(int codigo, int quantidade, String descricao){
        PdfPCell cellcodigo, cellestoque, celldescricao;
        cellcodigo = new PdfPCell(new Paragraph(Integer.toString(codigo)));
        cellestoque = new PdfPCell(new Paragraph(Integer.toString(quantidade)));
        celldescricao = new PdfPCell(new Paragraph(descricao));
        cellcodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellestoque.setHorizontalAlignment(Element.ALIGN_CENTER);
        celldescricao.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellcodigo);
        table.addCell(cellestoque);
        table.addCell(celldescricao);
    }
    public boolean geraRelatorio()throws Exception{
        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream(local));
            document.open();
            int headerwidths[] = {12, 12, 76};
            table.setWidths(headerwidths);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(1);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            Paragraph t;
            document.add(new Paragraph(""));
            document.addTitle("Relatorio de Produtos");
            document.add(new Paragraph(""));
            t = new Paragraph("RELATORIO DE ESTOQUE", new Font(Font.FontFamily.UNDEFINED, 16, Font.BOLD));
            t.setAlignment(Element.ALIGN_CENTER);
            document.add(t);
            document.add(new Paragraph(
            "____________________________________" +
            "____________________________________"
            ));
            t = new Paragraph(new Date().toLocaleString());
            t.setAlignment(Element.ALIGN_RIGHT);
            document.add(t);
            ProdutosDAO produtosDAO = new ProdutosDAO();
            ResultSet rs = produtosDAO.buscarTodosProdutos();
            if(rs != null){
              while(rs.next()){
                int codigo = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                int quantidade = rs.getInt("quantidade");
                imprimeTitulo(nome);
                imprimeCorpo(codigo, quantidade, descricao);
             }
            }
            document.add(table);
            document.add(new Paragraph(
            "__________________________" +
            "__________________________"
            ));
          document.add(new Paragraph(new Date().toLocaleString()));
          document.close();
          return true;
        }
    
     catch (Exception e){
    System.err.println(e.getMessage());
}
document.close();
return false;
    }
}
