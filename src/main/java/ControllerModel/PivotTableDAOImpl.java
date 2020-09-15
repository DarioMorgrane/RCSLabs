package ControllerModel;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of DAO interface for local SQlite
 *
 * @author  Daniil Mikhailenko
 */
@Component("DAO")
public class PivotTableDAOImpl implements PivotTableDAO {
    public static final String dataBaseName = "source_data";
    public static final String DB_URL = "jdbc:sqlite:data.sqlite";
    public static final String DB_Driver = "org.sqlite.JDBC";

    @Override
    public List<RowModel> findAll(String row, String col) {
        final ArrayList<RowModel> pivotTable = new ArrayList<>();
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("DB Driver is not found!");
        }

        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            Statement statement = connection.createStatement();
            String query = "SELECT " + row + ", " + col + ", " + "v " + "FROM " + dataBaseName + ";";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                pivotTable.add(new RowModel(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pivotTable;
    }
}
