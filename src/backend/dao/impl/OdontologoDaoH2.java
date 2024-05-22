package backend.dao.impl;

import backend.dao.IDao;
import backend.db.H2Connection;
import backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES(DEFAULT,?,?,?)";
    private static String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    // Enrique
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getNumMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(id, odontologo.getNumMatricula(), odontologo.getNombre(),odontologo.getApellido());
            }
            LOGGER.info("Odontologo guardado: "+ odontologoARetornar);

            connection.commit();
            connection.setAutoCommit(true);

        }catch(Exception e){
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.info(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologoARetornar;
    }




    //Juan
    @Override
    public List<Odontologo> listar() {
     return null;
    }
}


