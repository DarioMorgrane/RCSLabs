package dariomorgrane.RCSLabs.web;

import dariomorgrane.RCSLabs.exception.WebLayerException;
import dariomorgrane.RCSLabs.model.RowModel;
import dariomorgrane.RCSLabs.service.PivotTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PivotTableController {

    private PivotTableService service;

    @Autowired
    public PivotTableController(PivotTableService service) {
        this.service = service;
    }

    @GetMapping
    public List<RowModel> getPivotTable(@RequestParam("row") String row, @RequestParam("col") String col) {
        if (checkIsParameterInvalid(row)) {
            throw new WebLayerException("Unsupported argument for parameter 'row' - " + row);
        }
        if (checkIsParameterInvalid(col)) {
            throw new WebLayerException("Unsupported argument for parameter 'col' - " + col);
        }
        return service.getPivotTable(row,col);
    }

    private boolean checkIsParameterInvalid(String parameter) {
        return (!parameter.equals("a") && !parameter.equals("b") &&
                !parameter.equals("c") && !parameter.equals("d") &&
                !parameter.equals("y"));
    }



}
