package dariomorgrane.RCSLabs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.RCSLabs.model.RowModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PivotTableControllerTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private RowModel expectedRowModel;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @BeforeEach
    void setupTestingDatabase() {
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        jdbcTemplate.execute("drop table source_data if exists ");
        jdbcTemplate.execute("create table source_data " +
                "( a varchar not null, " +
                "b varchar not null, " +
                "c varchar not null, " +
                "d varchar not null, " +
                "y varchar not null, " +
                "v bigint not null )");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("a", "Налоги на имущество")
                .addValue("b", "Транспортный налог ")
                .addValue("c", "ЮЖНЫЙ ФЕДЕРАЛЬНЫЙ ОКРУГ")
                .addValue("d", "Краснодарский край")
                .addValue("y", "2015")
                .addValue("v", "6391859");
        expectedRowModel = new RowModel((String) parameters.getValue("b"), (String) parameters.getValue("d"), 6391859);
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("source_data");
        simpleJdbcInsert.execute(parameters);
    }

    @Test
    void test() throws JsonProcessingException {
        List<RowModel> expectedResponseList = new ArrayList<>();
        expectedResponseList.add(expectedRowModel);
        String expectedResponseBody = new ObjectMapper().writeValueAsString(expectedResponseList);
        String actuallyResponseBody = (restTemplate.getForObject("http://localhost:" + port + "/?row=b&col=d", String.class));
        Assertions.assertEquals(expectedResponseBody, actuallyResponseBody);
    }


}
