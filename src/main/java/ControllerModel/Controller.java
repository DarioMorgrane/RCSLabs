package ControllerModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller, that is provide REST API
 *
 * @author  Daniil Mikhailenko
 */
@RestController
public class Controller {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/")
    public String response(@RequestParam("row") String row, @RequestParam("col") String col) {

        if (checkIfParameterIsInvalid(row) || checkIfParameterIsInvalid(col)) {
            return "Wrong request";
        }

        PivotTableDAO pivotTableDAO = applicationContext.getBean("DAO", PivotTableDAOImpl.class);
        List<RowModel> pivotTable = pivotTableDAO.findAll(row, col);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pivotTable);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private boolean checkIfParameterIsInvalid(String parameter) {
        return (!parameter.equals("a") && !parameter.equals("b") &&
                !parameter.equals("c") && !parameter.equals("d") &&
                !parameter.equals("y"));
    }

}