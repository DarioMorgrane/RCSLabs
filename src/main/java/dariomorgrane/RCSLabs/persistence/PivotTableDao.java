package dariomorgrane.RCSLabs.persistence;

import dariomorgrane.RCSLabs.model.RowModel;

import java.util.List;

public interface PivotTableDao {

    List<RowModel> findAll(String row, String col);

}
