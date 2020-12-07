package dariomorgrane.RCSLabs.service;

import dariomorgrane.RCSLabs.model.RowModel;

import java.util.List;

public interface PivotTableService {
    List<RowModel> getPivotTable(String row, String col);
}
