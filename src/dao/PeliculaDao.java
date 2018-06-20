package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pelicula;
import interfaces.MetodosInterf;

/**
 *
 * @author LN710Q
 */
public class PeliculaDao implements MetodosInterf<Pelicula> {

    private static final String SQL_INSERT = "INSERT INTO filtros_aceite(codP,nombre,annio,marca,pais de procedencia,clasificacion,stock,existencia) VALUES(?,?,?,?,?,?,?,?)";
    ;
    private static final String SQL_UPDATE = "UPDATE filtros_pelicula SET annio =?, stock =?, existencia =? WHERE codP=?";
    private static final String SQL_DELETE = "DLETE FROM filtros_pelicula WHERE codP=?";
    private static final String SQL_READ = "SELECT * FROM filtros_pelicula WHERE codP=?";
    private static final String SQL_READALL = "SELECT * FROM filtros_pelicula";

    public static final Conexion conex=Conexion.conectar();
    @Override
    public boolean create(Pelicula g) {
        PreparedStatement ps;
        try {
            ps = conex.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getAnnio());
            ps.setString(3, g.getDirector());
            ps.setString(4, g.getPaisp());
            ps.setString(5, g.getClasificacion());
            ps.setInt(6, g.getStock());
            ps.setBoolean(7, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = conex.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Pelicula c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getNombre());
            ps = conex.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getAnnio());
            ps.setInt(2, c.getStock());
            ps.setBoolean(3, c.getExistenciap());
            ps.setString(4, c.getNombre());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.cerrarConexion();
        }
        return false;
    }

    @Override
    public Pelicula read(Object key) {
        Pelicula f = null;

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conex.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                f = new Pelicula(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getBoolean(8));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Pelicula> readAll() {
        ArrayList<Pelicula> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = conex.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while (rs.next()) {
                all.add(new Pelicula(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getBoolean(8)));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;

    }
}
