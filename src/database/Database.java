package database;

import java.sql.*;



/**

 *

 * @author User

 */

public interface Database {

    Connection openConnection();

    void closeConnection(Connection conn);

    ResultSet runQuery(Connection conn, String query);

    int excecuteUpdate(Connection conn, String query);

}