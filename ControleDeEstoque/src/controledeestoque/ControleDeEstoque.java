/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controledeestoque;

import controledeestoque.view.PrincipalView;

/**
 *
 * @author Joao
 */
public class ControleDeEstoque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PrincipalView principalView = new PrincipalView();
        principalView.validate();
        principalView.pack();
        principalView.setVisible(true);
    }
    
}
