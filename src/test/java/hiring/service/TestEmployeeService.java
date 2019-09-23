package hiring.service;

import com.google.gson.Gson;
import hiring.daointerface.DaoErrorCode;
import hiring.database.DataBaseErrorCode;
import hiring.dto.RegisterEmployeeDtoResponse;
import hiring.model.Employee;
import hiring.server.Server;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TestEmployeeService {
    private Server server = new Server();
    private UUID token;

    private Server getServer() {
        return server;
    }

    private void setToken(UUID token) {
        this.token = token;
    }

    private UUID getToken() {
        return token;
    }

    private void setServer(Server server) {
        this.server = server;
    }

    @Test
    public void testRegisterEmployee() {
        Gson gson = new Gson();
        getServer().startServer(null);
        Employee employee = new Employee("Ktuluhu", "gGYDu635%#&", "Ktuluhu@g.com", "Jak", "Fresko");
        String jsonRequest = gson.toJson(employee);
        setToken(UUID.fromString(getServer().registerEmployee(jsonRequest)));

        Employee employee2 = new Employee("Paganini", "11234r5%aSF", "Paganini", "Mary", "Doris");
        jsonRequest = gson.toJson(employee2);
        assertEquals("{\"error\":\"Wrong email\"}", getServer().registerEmployee(jsonRequest));

        Employee employee3 = new Employee("Java", "12345", "Java@m.r", "Nikkolo", "Tesla");
        jsonRequest = gson.toJson(employee3);

        assertEquals("{\"error\":\"The password must contain more 8 characters\"}", getServer().registerEmployee(jsonRequest));
        Employee employee4 = new Employee("", "gGYDu635%#&", "Python@kz.kz", "Robert", "Buh");
        jsonRequest = gson.toJson(employee4);
        assertEquals("{\"error\":\"Wrong login\"}", getServer().registerEmployee(jsonRequest));

        Employee employee5 = new Employee("C++", "gGYDu635%#&", "Cpp@rambler.co", "", "Dippel");
        jsonRequest = gson.toJson(employee5);
        assertEquals("{\"error\":\"Wrong first name\"}", getServer().registerEmployee(jsonRequest));

        Employee employee6 = new Employee("Ktuluhu", "gGYDu635%#&", "ia@u", "Alex", "Dulin");
        jsonRequest = gson.toJson(employee6);
        assertEquals(String.format("{\"error\":\"%s\"}", DataBaseErrorCode.MATCHING_LOGIN.getErrorString()), getServer().registerEmployee(jsonRequest));

        Employee employee7 = new Employee("C++", "gGYDu635%#&", "Cpp@rambler.co", "Mark", "");
        jsonRequest = gson.toJson(employee7);
        assertEquals("{\"error\":\"Wrong last name\"}", getServer().registerEmployee(jsonRequest));
        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());

        String jsonResponse = getServer().getEmployee(jsonRequest);
        RegisterEmployeeDtoResponse registerEmployeeDtoResponse = gson.fromJson(jsonResponse, RegisterEmployeeDtoResponse.class);
        assertEquals(employee.getFirstName(), registerEmployeeDtoResponse.getFirstName());
        assertEquals(employee.getLastName(), registerEmployeeDtoResponse.getLastName());
        assertEquals(employee.getEmail(), registerEmployeeDtoResponse.getEmail());
        assertEquals(employee.getLogin(), registerEmployeeDtoResponse.getLogin());
    }

    @Test
    public void testChangeFirstName() {
        Gson gson = new Gson();
        getServer().startServer(null);
        Employee employee = new Employee("Ktuluhu", "gGYDu635%#&", "Ktuluhu@g.com", "Jak", "Fresko");
        String jsonRequest = gson.toJson(employee);
        setToken(UUID.fromString(getServer().registerEmployee(jsonRequest)));
        jsonRequest = String.format("{\"firstName\":null,\"token\":\"%s\"}", getToken());
        assertEquals("{\"error\":\"Wrong first name\"}", getServer().changeFirstName(jsonRequest));
        jsonRequest = String.format("{\"firstName\":\"\",\"token\":\"%s\"}", getToken());
        assertEquals("{\"error\":\"Wrong first name\"}", getServer().changeFirstName(jsonRequest));
        jsonRequest = String.format("{\"firstName\":\"Sergey\",\"token\":\"%s\"}", getToken());
        assertEquals("{}", getServer().changeFirstName(jsonRequest));
        jsonRequest = "{\"firstName\":\"Petr\",\"token\":\"123\"}";
        assertEquals("{\"error\":\"Request dto error\"}", getServer().changeFirstName(jsonRequest));
        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());
        assertEquals("Sergey", gson.fromJson(getServer().getEmployee(jsonRequest), RegisterEmployeeDtoResponse.class).getFirstName());

    }

    @Test
    public void testChangeLastName() {
        Gson gson = new Gson();
        getServer().startServer(null);
        Employee employee = new Employee("Ktuluhu", "gGYDu635%#&", "Ktuluhu@g.com", "Jak", "Fresko");
        String jsonRequest = gson.toJson(employee);
        setToken(UUID.fromString(getServer().registerEmployee(jsonRequest)));
        jsonRequest = String.format("{\"lastName\":null,\"token\":\"%s\"}", getToken());
        assertEquals("{\"error\":\"Wrong last name\"}", getServer().changeLastName(jsonRequest));
        jsonRequest = String.format("{\"lastName\":\"\",\"token\":\"%s\"}", getToken());
        assertEquals("{\"error\":\"Wrong last name\"}", getServer().changeLastName(jsonRequest));
        jsonRequest = String.format("{\"lastName\":\"Tor\",\"token\":\"%s\"}", getToken());
        assertEquals("{}", getServer().changeLastName(jsonRequest));
        jsonRequest = "{\"lastName\":\"Petr\",\"token\":\"123\"}";
        assertEquals("{\"error\":\"Request dto error\"}", getServer().changeLastName(jsonRequest));
        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());
        assertEquals("Tor", gson.fromJson(getServer().getEmployee(jsonRequest), RegisterEmployeeDtoResponse.class).getLastName());
    }

    @Test
    public void testChangeEmail() {
        Gson gson = new Gson();
        getServer().startServer(null);
        Employee employee = new Employee("Ktuluhu", "gGYDu635%#&", "Ktuluhu@g.com", "Jak", "Fresko");
        String jsonRequest = gson.toJson(employee);
        setToken(UUID.fromString(getServer().registerEmployee(jsonRequest)));
        jsonRequest = String.format("{\"email\":null,\"token\":\"%s\"}", getToken());
        assertEquals("{\"error\":\"Wrong email\"}", getServer().changeEmail(jsonRequest));
        jsonRequest = String.format("{\"email\":\"lord.lord\",\"token\":\"%s\"}", getToken());
        assertEquals("{\"error\":\"Wrong email\"}", getServer().changeEmail(jsonRequest));
        jsonRequest = String.format("{\"email\":\"lord@gmail\",\"token\":\"%s\"}", getToken());
        assertEquals("{}", getServer().changeEmail(jsonRequest));
        jsonRequest = "{\"email\":\"Petr\",\"token\":\"123\"}";
        assertEquals("{\"error\":\"Request dto error\"}", getServer().changeEmail(jsonRequest));
        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());
        assertEquals("lord@gmail", gson.fromJson(getServer().getEmployee(jsonRequest), RegisterEmployeeDtoResponse.class).getEmail());
    }

    @Test
    public void changePassword_exitUser_loginUser() {
        Gson gson = new Gson();
        getServer().startServer(null);
        Employee employee = new Employee("Ktuluhu", "gGYDu635%#&", "Ktuluhu@g.com", "Jak", "Fresko");
        String jsonRequest = gson.toJson(employee);
        setToken(UUID.fromString(getServer().registerEmployee(jsonRequest)));
        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());
        assertEquals("{}", getServer().exit(jsonRequest));
        jsonRequest = "{\"login\":null,\"password\":\"123412344\"}";
        assertEquals("{\"error\":\"Wrong login format\"}", getServer().login(jsonRequest));
        jsonRequest = "{\"login\":\"dog\",\"password\":\"123412344\"}";
        assertEquals(String.format("{\"error\":\"%s\"}",DataBaseErrorCode.WRONG_LOGIN_OR_PASSWORD.getErrorString()), getServer().login(jsonRequest));
        jsonRequest = "{\"login\":\"dog\",\"password\":\"12341\"}";
        assertEquals("{\"error\":\"Wrong password\"}", getServer().login(jsonRequest));
        jsonRequest = "{\"login\":\"Ktuluhu\",\"password\":\"12341231\"}";
        assertEquals(String.format("{\"error\":\"%s\"}",DataBaseErrorCode.WRONG_LOGIN_OR_PASSWORD.getErrorString()), getServer().login(jsonRequest));

        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());
        assertEquals(String.format("{\"error\":\"%s\"}",DaoErrorCode.USER_OFFLINE.getErrorString()), getServer().getEmployee(jsonRequest));

        jsonRequest = "{\"login\":\"Ktuluhu\",\"password\":\"gGYDu635%#&\"}";
        assertEquals("{}", getServer().login(jsonRequest));

        jsonRequest = String.format("{\"token\":\"%s\"}", getToken());
        assertEquals("Ktuluhu@g.com", gson.fromJson(getServer().getEmployee(jsonRequest), RegisterEmployeeDtoResponse.class).getEmail());
    }
}
