package controller;

import dao.CocheDao;
import dao.PasajeroDao;
import model.Coche;
import model.Pasajero;
import java.sql.SQLException;
import java.util.ArrayList;

public class Concesionario {
    private CocheDao cocheDao;
    private PasajeroDao pasajeroDao;

    public Concesionario() throws SQLException {
        cocheDao = new CocheDao();
        pasajeroDao = new PasajeroDao();
    }

    // Gestión de los métodos de la clase Coche

    // Añadir coche
    public void añadirCoche(Coche coche) throws SQLException {
        cocheDao.añadirCoche(coche);
    }

    // Borrar coche por ID
    public void borrarCoche(int id) throws SQLException {
        cocheDao.borrarCoche(id);
    }

    // Consultar coche por ID
    public Coche consultarCochePorId(int id) throws SQLException {
        ArrayList<Coche> coches = cocheDao.consultarCocheId(id);
        if(coches.isEmpty()){
            return null;
        }
        else{
            return coches.get(0);
        }
    }

    // Modificar coche
    public void modificarCoche(int id, Coche coche) throws SQLException {
        cocheDao.modificarCoche(id, coche);
    }

    // Listar todos los coches
    public ArrayList<Coche> listarCoches() throws SQLException {
        return cocheDao.listaCoches();
    }

    // Gestión de los métodos de la clase Pasajero

    // Añadir pasajero
    public void añadirPasajero(Pasajero pasajero) throws SQLException {
        pasajeroDao.añadirPasajero(pasajero);
    }

    // Borrar pasajero por ID
    public void borrarPasajero(int id) throws SQLException {
        pasajeroDao.borrarPasajero(id);
    }

    // Consultar pasajero por ID
    public ArrayList<Pasajero> consultarPasajeroId(int id) throws SQLException {
        return pasajeroDao.consultarPasajeroId(id);
    }

    // Listar todos los pasajeros
    public ArrayList<Pasajero> listarPasajeros() throws SQLException {
        return pasajeroDao.listaPasajeros();
    }

    // Métodos que relacionan la clase Pasajero con la clase Coche

    // Añadir pasajero a coche
    public void añadirPasajeroACoche(int idCoche, int idPasajero) throws SQLException {
        pasajeroDao.añadirPasajeroCoche(idCoche, idPasajero);
    }

    // Eliminar pasajero de coche
    public void eliminarPasajeroDeCoche(int idPasajero) throws SQLException {
        pasajeroDao.eliminarPasajeroDeCoche(idPasajero);
    }

    // Listar todos los pasajeros de un coche
    public ArrayList<Pasajero> listarPasajerosDeCoche(int idCoche) throws SQLException {
        return pasajeroDao.listarPasajerosDeCoche(idCoche);
    }
}

