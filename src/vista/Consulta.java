package vista;

import dao.PeliculaDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Pelicula;


/**
 *
 * @author Jorge Orellana <00103717@uca.edu.sv>
 */
public class Consulta extends JFrame{
    public JLabel lblNombre, lblDirector,lblPais,lblAnnio, lblClasificacion,lblExistencia;
    public JTextField nombre, director, stock,pais,annioT;
    public JComboBox clasificacion;
    
    ButtonGroup existencia=new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    private static final int ANCHOC=130, ALTOC=30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Cinepolix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container =getContentPane();
        container.add(lblNombre);
        container.add(lblAnnio);
        container.add(lblClasificacion);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblExistencia);
        container.add(nombre);
        container.add(clasificacion);
        container.add(director);
        container.add(annioT);
        container.add(pais);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,400);
        eventos();
    }

    private void agregarLabels() {
        lblNombre = new JLabel("Nombre");
        lblAnnio = new JLabel("Año");
        lblClasificacion = new JLabel("Clasificacion");
        lblDirector = new JLabel("Director");
        lblPais = new JLabel("Pais");
        lblExistencia = new JLabel("En existencia");
        lblNombre.setBounds(10,10,ANCHOC, ALTOC);
        lblAnnio.setBounds(300,60,ANCHOC, ALTOC);
        lblDirector.setBounds(10,60,ANCHOC, ALTOC);
        lblPais.setBounds(10, 100, ANCHOC, ALTOC);
        lblClasificacion.setBounds(300, 10, ANCHOC, ALTOC);
        lblExistencia.setBounds(300, 100, ANCHOC, ALTOC);
    }

    private void formulario() {
        nombre = new JTextField();
        clasificacion = new JComboBox();
        director = new JTextField();
        stock = new JTextField();
        pais = new JTextField();
        annioT = new JTextField();
        si=new JRadioButton("si",true);
        no=new JRadioButton("no");
        resultados=new JTable();
        buscar=new JButton("Buscar");
        insertar=new JButton("Insertar");
        eliminar=new JButton("Eliminar");
        actualizar=new JButton("Actualizar");
        limpiar=new JButton("Limpiar");
        
        table=new JPanel();
        
        clasificacion.addItem("G");
        clasificacion.addItem("PG");
        clasificacion.addItem("14A");
        clasificacion.addItem("18A");
        clasificacion.addItem("R");
        clasificacion.addItem("A");
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        nombre.setBounds(100, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        eliminar.setBounds(150, 210, ANCHOC, ALTOC);
        buscar.setBounds(440, 210, ANCHOC, ALTOC);
        actualizar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(4500, 210, ANCHOC, ALTOC);
        clasificacion.setBounds(400, 10, ANCHOC, ALTOC);
        director.setBounds(100, 60, ANCHOC, ALTOC);
        pais.setBounds(100, 100, ANCHOC, ALTOC);
        annioT.setBounds(350, 60, ANCHOC, ALTOC);
        //G.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        si.setBounds(400, 100, 50, ALTOC);
        no.setBounds(450, 100, 50, ALTOC);
        resultados=new JTable();
        table.setBounds(10,250,500,200);
        table.add(new JScrollPane(resultados));
    }

    private void llenarTabla() {
        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;                        
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En proyección");
        
        PeliculaDao fd =new PeliculaDao();
        ArrayList<Pelicula> peliculas= fd.readAll();
        
        for(Pelicula fil: peliculas){
            tm.addRow(new Object[]{fil.getNombre(),fil.getDirector(),fil.getPais(),fil.getClasificacion(), 
                fil.getAnnio(),fil.isExistencia()});
        
        }
        resultados.setModel(tm);
    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao fd =new PeliculaDao();
                Pelicula f = new Pelicula(nombre.getText(), director.getText(),pais.getText(),
                        clasificacion.getSelectedItem().toString(),annioT.getText(),true);
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro.");
                }
            }
        });
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao fd= new PeliculaDao();
                Pelicula f=new Pelicula(nombre.getText(), director.getText(),pais.getText(),
                        clasificacion.getSelectedItem().toString(),annioT.getText(),true);
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro.");
                }
            }
            
        });
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao fd= new PeliculaDao();
                if(fd.delete(nombre.getText())){
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro.");
                }
            }
        });
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao fd= new PeliculaDao();
                Pelicula f=fd.read(nombre.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");
                }
                else{
                    nombre.setText(f.getNombre());////////////////////////////
                    director.setText(f.getDirector());
                    pais.setText(f.getPais());
                    clasificacion.setSelectedItem(f.getClasificacion());
                    annioT.setText(f.getAnnio());
                    if(f.isExistencia()){
                        si.setSelected(true);
                    }
                    else{
                        no.setSelected(true);
                    }
                }
            }
            
        });
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        }); 
    }
    public void limpiarCampos(){
        nombre.setText("");
        director.setText("");
        pais.setText("");
        clasificacion.setSelectedItem("G");
        annioT.setText("");
    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }    
}
