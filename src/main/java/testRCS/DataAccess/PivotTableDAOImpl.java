package testRCS.DataAccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import testRCS.Models.RowModel;

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
    @Value("${tableName}")
    private String tableName;
    @Value("${DB_URL}")
    private String DB_URL;
    @Value("${DB_Driver}")
    private String DB_Driver;

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
            String query = "SELECT " + row + ", " + col + ", " + "v " + "FROM " + tableName + ";";
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
