/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Controlador.ControladorVistaPrincipal;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTabbedPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


/**
 *
 * @author davidf
 */
public class Panel_Pestanias extends JPanel {

    public static JTabbedPane TP;
    int i_aux=0;
    
    
    public Panel_Pestanias() {

        TP = new JTabbedPane();

     }


    
    ////////Metodo para crear pestañas NUEVAS con TextArea
    public Panel_Pestanias PestaniaTextoNueva() {
        
        //Creando objeto de PanelTextArea para pasarselo a la PESTAÑA
        PanelTextArea textArea1 = new PanelTextArea();
        //Poniendole titulo a la pestaña
      //  String title=("Pestana " + (TP.getTabCount() + 1));
        String title=("Pestana " + (i_aux + 1));
         TP.addTab(title, textArea1);
        
        /////Incrementamos i_aux para que lleve un contador de las pestaña nuevas que se han creado,
        i_aux++;
        
        //Colocando TITULO y BOTON de CERRAR a la pestaña
        for (int i=0; i < TP.getTabCount(); i++) {
            TP.setTabComponentAt(i, new Cross(TP.getTitleAt(i))); //agrega titulo y boton X.
     
        }// //Fin del for
        return this;
    }//Fin del metodo crear pestaña nueva

    
    /////////////clase que coloca el TITULO y el BOTON DE CERRAR a la pestaña
        public class Cross extends JPanel {

        private JLabel L;
        private JButton B;
        private int size = 10;

        public Cross(String title) {

            setOpaque(false);
            setLayout(new java.awt.GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            L = new JLabel(title + " ");
            B = new JButton();
            B.setPreferredSize(new java.awt.Dimension(10, 10));
            B.setIcon(getImage());
            //Listener para cierre de tabs con acceso estatico al `JTabbedPane`
            B.addActionListener(e -> Panel_Pestanias.TP.removeTabAt(Panel_Pestanias.TP.indexOfTab(title)));
     //       B.addActionListener(new ControladorVistaPrincipal.OyenteXX());
         //   B.addActionListener(new OyenteXX());
            add(L, gbc);
            gbc.gridx++;
            gbc.weightx = 0;
            add(B, gbc);
        }
        //Creando y escalando el ICONO de CERRAR la PEsTAÑA
        private ImageIcon getImage() {
            java.awt.Image IMG = null;
            try {
                IMG = new ImageIcon(getClass().getResource("/Vistas/cross.png")).getImage();
                IMG = IMG.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ImageIcon(IMG);
        }
    }


    /*
    
    class OyenteXX implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
      //      TP.removeTabAt(TP.getSelectedIndex());
            System.out.println(TP.getTabPlacement());
         TP.removeTabAt(TP.getTabPlacement());
        }
    }
      
*/
}
