package dao;
import database.DataBaseConnection;
import database.SchemaDB;
import model.Coche;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CocheDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //Inicialización de la conexión
    public CocheDao() throws SQLException {
        connection = new DataBaseConnection().getConnection();
    }

    //Métodos CRUD
    //Añadir coche
    public void añadirCoche(Coche coche) throws SQLException {

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CO, SchemaDB.COL_CO_MAT, SchemaDB.COL_CO_MAR, SchemaDB.COL_CO_MOD, SchemaDB.COL_CO_COL);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.execute();
    }

    //Borrar coche por id
    public void borrarCoche(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_CO, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    //Consultar coche por id
    public ArrayList<Coche> consultarCocheId(int id) throws SQLException {
        ArrayList<Coche> resultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_CO, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }

    //Modificar coche
    public void modificarCoche(int id,Coche coche) throws SQLException {
        String query = String.format("UPDATE %s SET %s=?, %S=?, %S=?, %S=? WHERE %s = ?",
                SchemaDB.TAB_CO, SchemaDB.COL_CO_MAT, SchemaDB.COL_CO_MAR, SchemaDB.COL_CO_MOD, SchemaDB.COL_CO_COL, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getMatricula());
        preparedStatement.setInt(5,id);
        preparedStatement.executeUpdate();

    }


    //Lista de coches
    public ArrayList<Coche>listaCoches() throws SQLException {
        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_CO);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();{
            return getResultados(resultSet);
        }
    }



    //Método que devuelve una lista con los resultados
    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche>listaResultado = new ArrayList<>();
        while(datosResultantes.next()){
            String matricula = resultSet.getString(SchemaDB.COL_CO_MAT);
            String marca = resultSet.getString(SchemaDB.COL_CO_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CO_MOD);
            String color = resultSet.getString(SchemaDB.COL_CO_COL);
            listaResultado.add(mapearCoche(matricula, marca, modelo, color));
        }
        return listaResultado;
    }

    //Método para mapear
    private Coche mapearCoche(String matricula, String marca, String modelo, String color){
        return new Coche(matricula,marca,modelo,color);
    }

}
