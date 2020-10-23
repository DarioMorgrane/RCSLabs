package testRCS.DataAccess;

import testRCS.Models.RowModel;

import java.util.List;

/**
 * DAO interface
 *
 * @author  Daniil Mikhailenko
 */
public interface PivotTableDAO {

    List<RowModel> findAll(String row, String col);

}
