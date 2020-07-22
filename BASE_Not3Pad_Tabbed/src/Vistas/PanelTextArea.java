/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author davidf
 */
public class PanelTextArea extends JPanel{

    public JTextArea textArea;
    protected JScrollPane scrollPane;
    public JPopupMenu popMenu;
    public JMenuItem pop_Boton1;
    public JMenuItem pop_Boton2;
    public JMenuItem pop_Boton3;

    public PanelTextArea() {

        //Se utilizará BORDERLAYOUT, para situar el TextArea en este panel
        this.setLayout(new BorderLayout());

        //Instanciamos el TextArea y lo agregamos al ScrollPane
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        //Instanciamos el Popup y los menus
        popMenu = new JPopupMenu();
        pop_Boton1 = new JMenuItem("BOTON1");
        pop_Boton2 = new JMenuItem("BOTON2");
        pop_Boton3 = new JMenuItem("BOTON3");

             //Activamos el textArea para que se muestren los colores
             textArea.setOpaque(true);
        
        //Agregamos TextArea a la capa.
        add(scrollPane, BorderLayout.CENTER);

        //Menú POP-UP AGREGANDO Botones al PopupMenu
        popMenu.add(pop_Boton1);
        popMenu.add(pop_Boton2);
        popMenu.addSeparator();
        popMenu.add(pop_Boton3);

       
        
        //Agregamos el PopUpMenu al TextArea
        textArea.setComponentPopupMenu(popMenu);
        textArea.setInheritsPopupMenu(true);
     
        Iniciar();
    }

    private void Iniciar(){
    
    
                    
        //JPOPUPMENU Agregamos los botones el JPopupMenu y les pasamos su correspondiente Listener
      pop_Boton1.addActionListener(new OyentePopBoton1());
       pop_Boton2.addActionListener(new OyentePopBoton2());
       pop_Boton3.addActionListener(new OyentePopBoton3());

        // Se añade el JPopupMenu al J-ITEM donde queremos que se muestre 
        //Debemos implementar todos los metodos de MouseListener porque
        // no sabemos a priori que evento usara el sistema operativo para
        // mostrar los menus.
 //      panelTextArea.panelTextArea.addMouseListener(new OyenteRatonPopupMenu());
//*/
    }


    
////////////////////////////////////////////////////////////////////////////////    
///////////////////  POP-UP MENU  //////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////   
    class OyentePopBoton1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            System.out.println("bont1 click");
            textArea.append("waaaaaaaaaaaaaaaaaaaaa");
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyentePopBoton2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            textArea.cut();
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyentePopBoton3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            textArea.paste();
        }//Fin action performed
    }//Fin del OyenteCOPIAR



    
    
    
    
  /*  //METODO la FONT del textArea
    public Font setTextArea() {
        return textArea.getFont();
     }  
    
    */
    
    
 /*   //METODO PARA PODER DEVOLVER EL TEXTAREA
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
     }
   */ 
    
   /*   //METODO PARA PODER DEVOLVER EL TEXTAREA
    public JTextArea getTextArea() {
        return textArea ;
     }
    
    
    
     public String getTexto() {
        
         return textArea.getText();
     }
    */
    
}
