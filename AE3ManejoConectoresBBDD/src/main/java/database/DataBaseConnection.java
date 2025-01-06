package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static Connection connection;

    //Método para pedir la conexión
    public Connection getConnection() throws SQLException {
        //si no está
        if(connection==null){
            //se crea llamando la respectivo método
            createConnection();
        }
        //si está, se la devuelve
        return connection;
    }

    //Método para crear la conexion
    private void createConnection() throws SQLException {
        //url de conexion
        String url = String.format("jdbc:mysql://%s:%s/%s", SchemaDB.HOST, SchemaDB.PORT, SchemaDB.DATABASE);
        connection = DriverManager.getConnection(url, "root", "");
    }

    //Método para cerrar la conexión
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("error al cerrar la conexión");;
        }finally {
            connection=null;
        }
    }
}
