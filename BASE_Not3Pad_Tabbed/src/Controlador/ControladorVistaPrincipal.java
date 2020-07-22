/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MetodosPrincipal;
import Vistas.PanelMenuBar;
import Vistas.PanelTextArea;
import Vistas.Panel_Pestanias;

import Vistas.VistaPrincipal;
import static Vistas.VistaPrincipal.panelBase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author davidf
 */
public class ControladorVistaPrincipal {

    //Declarando OBJETOS de VistaPrincipal(el marco de la aplicación) y los Metodos(modelo)
    public VistaPrincipal vistaPrincipal;
    private final MetodosPrincipal metodosPrincipal;
    //Declarando OBJETOS de PANELES QUE CONFORMA LA VistaPrincipal
    public PanelMenuBar panelMenuBar; //Panel que contiene el JMenuBar
    public static Panel_Pestanias panelPestanias; //Panel que contiene el JTabbedPane
    public PanelTextArea panelTA; //este pane lo utilizaremos para recuperar el PANELTEXTAREA (que está contenida en algunas pestañas) que corresponda a la pestaña que hemos seleccionado

    //Declarando objetos y variables necesarias para poder cambiar el estilo y formato de los TextArea
    Font fuente;
    Color color;
    int tamanio_fuente;
    String nombre_fuente;
    String[] fontNames;//Estos métodos rellenarán el Array con los estilos de texto disponibles en nuestro sistema
    Color colorBackground;
    Color colorTexto;
    Color colorSeleccion;
    Color colorTextoSeleccionado;

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal, MetodosPrincipal metodosPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.metodosPrincipal = metodosPrincipal;
        this.panelMenuBar = new PanelMenuBar();
        this.panelPestanias = new Panel_Pestanias();
        this.fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //Estos métodos rellenarán el Array con los estilos de texto disponibles en nuestro sistema

        //Confiduramos el layout del panelMenuBar a Border, y agregamos los paneles
        vistaPrincipal.panelBase.setLayout(new BorderLayout());

        //Seteamos el tamaño del panel que contiene el MENU (ya que si no no se muestra correctamente el componente)
        //, dandole de ancho el mismo que tenga de X el panelBase 
        panelMenuBar.setPreferredSize(new Dimension(panelBase.getX(), 20));
        //Agregamos el panel que contiene el MENU
        vistaPrincipal.panelBase.add(panelMenuBar, BorderLayout.PAGE_START);
        //Agregamos el panel que contiene el TextArea
        vistaPrincipal.panelBase.add(panelPestanias.TP, BorderLayout.CENTER);
        //Agregamos a vista principal su propio panelBase

        vistaPrincipal.getContentPane().add(vistaPrincipal.panelBase);

        vistaPrincipal.pack();

        //Ejecutamos el método iniciar que iniciará todos los componentes
        Iniciar();
    }

    private void Iniciar() {

        panelMenuBar.nuevo.addActionListener(new OyenteNuevo());
        panelMenuBar.abrir.addActionListener(new OyenteAbrir());
        panelMenuBar.guardar.addActionListener(new OyenteGuardar());
        panelMenuBar.guardarComo.addActionListener(new OyenteGuardarComo());
        panelMenuBar.renombrar.addActionListener(new OyenteRenombrar());
        panelMenuBar.ir_A.addActionListener(new OyenteIrA());
        panelMenuBar.deshacer.addActionListener(new OyenteDeshacer());
        panelMenuBar.rehacer.addActionListener(new OyenteRehacer());
        panelMenuBar.copiar.addActionListener(new OyenteCopiar());
        panelMenuBar.pegar.addActionListener(new OyentePegar());
        panelMenuBar.cortar.addActionListener(new OyenteCortar());
        panelMenuBar.insertarFecha.addActionListener(new OyenteInsertarFecha());
        panelMenuBar.buscar.addActionListener(new OyenteBuscar());
        panelMenuBar.buscarYreemplazar.addActionListener(new OyenteBuscarYReemplazar());
        panelMenuBar.imprimir_configurando.addActionListener(new OyenteImprimirConfigurando());
        panelMenuBar.imprimir_directo.addActionListener(new OyenteImprimirDirecto());
        panelMenuBar.acercaDe.addActionListener(new OyenteAcercaDe());
        panelMenuBar.salir.addActionListener(new OyenteSalir());
        panelMenuBar.colorBackground.addActionListener(new OyenteColorBackground());
        panelMenuBar.colorFuente.addActionListener(new OyenteColorFuente());
        panelMenuBar.colorSeleccion.addActionListener(new OyenteColorSeleccion());
        panelMenuBar.colorTextoSeleccionado.addActionListener(new OyenteColorTextoSeleccionado());
        //Agregamos OYENTE al JTabbedPane, para saber a qué pestaña ha cambiado (y poder recuperar su componente para trabajar con el o lo que queramos)
        panelPestanias.TP.addChangeListener(new OyenteCambioPestana());

        //Añadiendo y cargando ComboBox de TAMAÑO LETRA
        for (int i = 0; i < 100; i++) {
            panelMenuBar.comboBox.addItem(i);
        }
        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
        panelMenuBar.comboBox.addActionListener(new OyenteComboTamanio());

        ///AÑADIENDO COMBOBOX PARA ESTILO DE LETRA
        //Mostrar un listado con las fuentes DISPONIBLES
        //Recorremos el array de FontNames para rellenar el comboBox con todos los estilos de letra disponibles 
        for (String fontName : fontNames) {
            panelMenuBar.comboBoxStyle.addItem(fontName);
        }
        panelMenuBar.comboBoxStyle.addActionListener(new OyenteComboStyle());

        //Desactivamos los botones porque no Existirá ninguna pestaña abierta
        panelMenuBar.guardar.setEnabled(false);
        panelMenuBar.guardarComo.setEnabled(false);
        panelMenuBar.renombrar.setEnabled(false);
        panelMenuBar.edicion.setEnabled(false);
        panelMenuBar.imprimir.setEnabled(false);
        panelMenuBar.personalizar.setEnabled(false);
        panelMenuBar.comboBox.setEnabled(false);
        panelMenuBar.comboBoxStyle.setEnabled(false);

        //Le assignamos el tamaño a la Vista Principal
        vistaPrincipal.setBounds(0, 0, 600, 500);

        //Con este método haremos que la pantalla salga JUSTO EN EL CENTRO
        vistaPrincipal.setLocationRelativeTo(null);
        //Hacemos visible la vista Principal      
        vistaPrincipal.setVisible(true);
    }//Fin de iniciar

    ////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * ESTE ES EL Oyente para DETECTAR en QUÉ PESTAÑA se encuentra nuestro
     * "puntero" cada vez que detecte un cambio de pestaña, recuperará el
     * TextArea de la pestaña correspondiente para poder trabajar en este y
     * aplicarle su configuración correspondiente a los combobox, etc
     */
    class OyenteCambioPestana implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            //Creando objeto de PanelTextAREA QUE APUNTA  al PanelTextArea que tenemos en LA PESTAÑA ACTUAL
            //Por lo tanto estaremos actuando directamente en la pestaña seleccionada
            panelTA = (PanelTextArea) panelPestanias.TP.getSelectedComponent();

            Recargar();
        }

    }

    /**
     * Este metodo, se ejecutará cada vez que el OyentePestana detecte un cambio
     * en este, se comprobará si existe alguna pestaña en caso que no existe, se
     * limitarán algunos botones en caso de que exista almenos una, se
     * desbloquearan los botones y se cargará en PanelTA de esta clase pasandole
     * el PanelTexArea de la pestaña que está seleccionada actualmente para
     * poder trabajar con esta, con todas sus propiedades etc..
     */
    private void Recargar() {

        //Comprobamos si existe alguna pestaña
        if (Panel_Pestanias.TP.getTabCount() > 0) {

            //Recuperamos LOS COLORES de Texto,Background,seleccion, texto seleccionado Correspondientes al TextArea
            //Color del Background
            Color recuperando_color;//Variable en la que obtendremos el RGB y lo transformaremos a COLOR

            recuperando_color = new Color(panelTA.textArea.getBackground().getRGB());
            colorBackground = recuperando_color;
            //Color del Foreground
            recuperando_color = new Color(panelTA.textArea.getForeground().getRGB());
            colorTexto = recuperando_color;
            //Color del TextoSeleccionado
            recuperando_color = new Color(panelTA.textArea.getSelectedTextColor().getRGB());
            colorTextoSeleccionado = recuperando_color;
            //Color dela Seleccion
            recuperando_color = new Color(panelTA.textArea.getSelectionColor().getRGB());
            colorSeleccion = recuperando_color;

            //Recuperamos la fuente correspondiente al TextArea de nuestro objeto PanelTextArea
            fuente = panelTA.textArea.getFont();
            //Extraemos el nombre de la fuente y lo almacenamos en la variable que tenemos instanciada
            nombre_fuente = fuente.getName();
            //Y extraemos el tamaño de la fuente y lo almacenamos en la variable que tenemos instanciada
            tamanio_fuente = fuente.getSize();

            //Ahora setearemos los comboBox con los datos Extraidos
            panelMenuBar.comboBox.setSelectedItem(tamanio_fuente); //Agregaremos este primer elemento con el valor inicial que tendrá
            panelMenuBar.comboBoxStyle.setSelectedItem(nombre_fuente); //Agregaremos este primer elemento con el valor inicial que tendrá

            //ACTIVAMOS los botones porque no Existirá ninguna pestaña abierta
            panelMenuBar.guardar.setEnabled(true);
            panelMenuBar.guardarComo.setEnabled(true);
            panelMenuBar.renombrar.setEnabled(true);
            panelMenuBar.edicion.setEnabled(true);
            panelMenuBar.imprimir.setEnabled(true);
            panelMenuBar.personalizar.setEnabled(true);
            panelMenuBar.comboBox.setEnabled(true);
            panelMenuBar.comboBoxStyle.setEnabled(true);

        } else {
            //Desactivamos los botones porque no Existirá ninguna pestaña abierta
            panelMenuBar.guardar.setEnabled(false);
            panelMenuBar.guardarComo.setEnabled(false);
            panelMenuBar.renombrar.setEnabled(false);
            panelMenuBar.edicion.setEnabled(false);
            panelMenuBar.imprimir.setEnabled(false);
            panelMenuBar.personalizar.setEnabled(false);
            panelMenuBar.comboBox.setEnabled(false);
            panelMenuBar.comboBoxStyle.setEnabled(false);

        }//Find el ifelse

    }//Fin del metodo Rescargar

    ////////////////////////////////////////////////////////////////////////////////   
    class OyenteComboTamanio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            //Almacenamos los datos del combobox en la variable instanciada, para utilizarla cuando sea necesario
            tamanio_fuente = Integer.parseInt(panelMenuBar.comboBox.getSelectedItem().toString());

            fuente = new Font(nombre_fuente, Font.PLAIN, tamanio_fuente);
            panelTA.textArea.setFont(fuente); // Coneste metodo realizamos el cambio de estilo a nuestro textarea del objeto de PanelTextArea correspondiente a la pestaña

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteComboStyle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            //Creando objeto de PanelTextAREA QUE APUNTA  al PanelTextArea que tenemos en LA PESTAÑA ACTUAL
            //Por lo tanto estaremos actuando directamente en la pestaña seleccionada
            //  PanelTextArea panelTA = (PanelTextArea) panelPestanias.TP.getSelectedComponent();
            //Almacenamos los datos del combobox en la variable instanciada, para utilizarla cuando sea necesario
            nombre_fuente = panelMenuBar.comboBoxStyle.getSelectedItem().toString();

            fuente = new Font(nombre_fuente, Font.PLAIN, tamanio_fuente);
            panelTA.textArea.setFont(fuente); // sólo va a cambiar el tamaño a 12 puntos

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteNuevo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            panelPestanias = panelPestanias.PestaniaTextoNueva();
            //JPOPUPMENU Agregamos los botones el JPopupMenu y les pasamos su correspondiente Listener

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteAbrir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
        
              panelPestanias = panelPestanias.PestaniaTextoNueva();
              
            
            
            
            
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            ;
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteGuardarComo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteRenombrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteIrA implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteDeshacer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteRehacer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteCopiar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyentePegar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteCortar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteInsertarFecha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteBuscarYReemplazar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteImprimirConfigurando implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteImprimirDirecto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteAcercaDe implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteSalir implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            System.exit(0);
        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorBackground implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            //Solicitamos que se seleccione el color
            color = JColorChooser.showDialog(panelTA.textArea, "Elige un color", colorBackground);

            //Comprobamos si se SELECCIONÓ algúncolor
            if (color != null) {
                colorBackground = new Color(color.getRGB());
                panelTA.textArea.setBackground(colorBackground);
            }//Find el if

        }//Fin action performed
    }//Fin del OyenteCOPIAR

////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorFuente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            //Creando objeto de PanelTextAREA QUE APUNTA  al PanelTextArea que tenemos en LA PESTAÑA ACTUAL
            //Por lo tanto estaremos actuando directamente en la pestaña seleccionada
            //CAMBIAR EL COLOR DE LA "LETRA"
            color = JColorChooser.showDialog(panelTA.textArea, "Elige un color", colorTexto);

            //Comprobamos si se SELECCIONÓ algúncolor
            if (color != null) {
                colorTexto = new Color(color.getRGB());
                //Seteando COLOR de TEXTO   
                panelTA.textArea.setForeground(colorTexto);
                //Color del "caret" (puntero)
                panelTA.textArea.setCaretColor(colorTexto);
            }//Find el if

        }//Fin action performed
    }//Fin del OyenteCOPIAR

    ////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorSeleccion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ

            //CAMBIAR EL COLOR DE LA "LETRA"
            color = JColorChooser.showDialog(panelTA.textArea, "Elige un color", colorSeleccion);

            if (color != null) {
                colorSeleccion = new Color(color.getRGB());
                //Color de SLECCION
                panelTA.textArea.setSelectionColor(colorSeleccion);
            }

        }//Fin action performed
    }//Fin del OyenteCOPIAR

    ////////////////////////////////////////////////////////////////////////////////   
    class OyenteColorTextoSeleccionado implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //ESCRIBIR CÓDIGO DEL BOTÓN AQUÍ
            //CAMBIAR EL COLOR DE LA "LETRA"
            color = JColorChooser.showDialog(panelTA.textArea, "Elige un color", colorTextoSeleccionado);
            if (color != null) {
                colorTextoSeleccionado = new Color(color.getRGB());
                //Color de TEXTO Seleccionado
                panelTA.textArea.setSelectedTextColor(colorTextoSeleccionado);
            }

        }//Fin action performed
    }//Fin del OyenteCOPIAR
}

/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
//////CODIGOS MODELO PARA UTILIZAR EN EL DESARROLLO///////////////////////
////////////////////////////////////////////////////////////////////////
//   JOptionPane.showMessageDialog(panelPestanias.textArea, "que diseeeee", "dialogo TEST", JOptionPane.INFORMATION_MESSAGE);

