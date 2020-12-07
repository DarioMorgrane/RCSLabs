package dariomorgrane.RCSLabs.persistence;

import dariomorgrane.RCSLabs.model.RowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PivotTableDaoImplementation implements PivotTableDao {

    private JdbcTemplate jdbcTemplate;
    @Value("${tableName}")
    private String tableName;

    @Autowired
    public PivotTableDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RowModel> findAll(String row, String col) {
        String sql = "select rowName, colName, v from " + tableName;
        sql = sql.replace("rowName", row).replace("colName", col);
        return jdbcTemplate.query(sql,
                (resultSet, i) -> new RowModel(resultSet.getString(row), resultSet.getString(col), resultSet.getLong("v")));
    }
}
