package dariomorgrane.RCSLabs.service;

import dariomorgrane.RCSLabs.model.RowModel;
import dariomorgrane.RCSLabs.persistence.PivotTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PivotTableServiceImplementation implements PivotTableService {

    private PivotTableDao dao;

    @Autowired
    public PivotTableServiceImplementation(PivotTableDao dao) {
        this.dao = dao;
    }

    @Override
    public List<RowModel> getPivotTable(String row, String col) {
        return dao.findAll(row, col);
    }
}
