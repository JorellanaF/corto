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
 * @author LN710Q
 */
public class Consulta extends JFrame{
    
    public JLabel lblNombre, lblDirector,lblPais,lblAnnio, lblClasificacion,lblExistencia;
    public JTextField nombre, director, pais,annioT;
    public JComboBox annio;
    
    ButtonGroup clasificacion=new ButtonGroup();
    ButtonGroup existencia=new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    public JRadioButton G;
    public JRadioButton PG;
    public JRadioButton A14;
    public JRadioButton A18;
    public JRadioButton R;
    public JRadioButton A;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    private static final int ANCHOC=130, ALTOC=30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inventario");
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
        container.add(nombre);
        container.add(annio);
        container.add(director);
        container.add(annioT);
        container.add(pais);
        container.add(si);
        container.add(no);
        container.add(G);
        container.add(PG);
        container.add(A14);
        container.add(A18);
        container.add(R);
        container.add(A);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,600);
        eventos();
    }

    private void agregarLabels() {
        lblNombre = new JLabel("Nombre");
        lblAnnio = new JLabel("Año");
        lblClasificacion = new JLabel("Clasificacion");
        lblDirector = new JLabel("Director");
        lblPais = new JLabel("Pais");
        lblNombre.setBounds(10,10,ANCHOC, ALTOC);
        lblAnnio.setBounds(300,100,ANCHOC, ALTOC);
        lblDirector.setBounds(10,60,ANCHOC, ALTOC);
        lblPais.setBounds(10, 100, ANCHOC, ALTOC);
        lblClasificacion.setBounds(300, 10, ANCHOC, ALTOC);
    }

    private void formulario() {
        nombre = new JTextField();
        annio = new JComboBox();
        director = new JTextField();
        pais = new JTextField();
        annioT = new JTextField();
        si=new JRadioButton("si",true);
        no=new JRadioButton("no");
        G=new JRadioButton("G");
        PG=new JRadioButton("PG");
        A14=new JRadioButton("A14");
        A18=new JRadioButton("A18");
        R=new JRadioButton("R");
        A=new JRadioButton("A");
        resultados=new JTable();
        buscar=new JButton("Buscar");
        insertar=new JButton("Insertar");
        eliminar=new JButton("Eliminar");
        actualizar=new JButton("Actualizar");
        limpiar=new JButton("Limpiar");
        
        table=new JPanel();
        
        annio.addItem("FRAM");
        annio.addItem("WIX");
        annio.addItem("Luber Finer");
        annio.addItem("OSK");
        
        clasificacion = new ButtonGroup();
        clasificacion.add(G);
        clasificacion.add(PG);
        clasificacion.add(A14);
        clasificacion.add(A18);
        clasificacion.add(R);
        clasificacion.add(A);
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        nombre.setBounds(100, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        eliminar.setBounds(150, 210, ANCHOC, ALTOC);
        buscar.setBounds(440, 210, ANCHOC, ALTOC);
        actualizar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(4500, 210, ANCHOC, ALTOC);
        director.setBounds(100, 60, ANCHOC, ALTOC);
        pais.setBounds(100, 100, ANCHOC, ALTOC);
        annioT.setBounds(350, 100, ANCHOC, ALTOC);
        //G.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        resultados=new JTable();
        table.setBounds(10,250,500,200);
        table.add(new JScrollPane(resultados));
    }

    private void llenarTabla() {
        tm=new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                    case 1:
                        return String.class;
                    case 2:
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
        tm.addColumn("Stock");
        tm.addColumn("Stock em sucursal");
        
        PeliculaDao fd =new PeliculaDao();
        ArrayList<Pelicula> filtros= fd.readAll();
        
        for(Pelicula fil: filtros){
            tm.addRow(new Object[]{fil.getNombre(),fil.getDirector(),fil.getPaisp(),fil.getClasificacion(), fil.getAnnio(), fil.getStock(),fil.getExistenciap()});
        
    }
        resultados.setModel(tm);
    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PeliculaDao fd =new PeliculaDao();
                Pelicula f=new Pelicula(nombre.getText(), annio.getSelectedItem().toString(), Integer.parseInt(director.getText()),clasificacion.getSelection().getActionCommand(),true);
                if(no.isSelected()){
                    f.setExistenciap(false);
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
                Pelicula f=new Pelicula(nombre.getText(), annio.getSelectedItem().toString(), Integer.parseInt(director.getText()),clasificacion.getSelection().getActionCommand(),true);
                if(no.isSelected()){
                    f.setExistenciap(false);
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
                    annio.setSelectedItem(f.getAnnio());
                    director.setText(Integer.toString(f.getStock()));
                    if(f.getExistenciap()){
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
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); 
    }
    public void limpiarCampos(){
        nombre.setText("");
        annio.setSelectedItem("FRAM");
        director.setText("");
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
