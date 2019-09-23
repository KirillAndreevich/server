package hiring.server;

import hiring.service.EmployerService;
import hiring.service.EmployeeService;
import hiring.service.ServerService;
import hiring.service.UserService;

public class Server {
    private boolean serverStatus = false;

    public Server() {

    }
    public String startServer(String savedDataFileName) {
        this.serverStatus = true;
        if (savedDataFileName == null) {
            return ServerService.startDataBase();
        } else {
            return ServerService.loadDataBase(savedDataFileName);
        }
    }

    public String stopServer(String saveDataFileName) {
        this.serverStatus = false;
        if (saveDataFileName == null) {
            return ServerService.startDataBase();
        } else {
            return ServerService.saveDataBase(saveDataFileName);
        }
    }
/*
    public String registerEmployer(String requestJsonString)
    {
        return EmployerService.registerEmployer(requestJsonString);

    }
*/
    public String registerEmployee(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return EmployeeService.registerEmployee(requestJsonString);
    }
    public String getEmployee(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.getEmployee(requestJsonString);
    }
    public String getEmployer(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployerService.getEmployer(requestJsonString);
    }
    public String deleteSkill(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.deleteSkill(requestJsonString);
    }
    public String changeFirstName(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.changeFirstName(requestJsonString);
    }
    public String changeLastName(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.changeLastName(requestJsonString);
    }
    public String changeEmail(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return UserService.changeEmail(requestJsonString);
    }
    public String changePatronymic(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.changePatronymic(requestJsonString);
    }
    public String getSuitableVacanciesAllDemandsMyLvl(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.getSuitableVacanciesAllDemandsMyLvl(requestJsonString);
    }
    public String getSuitableVacanciesNecessaryDemandsMyLvl(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.getSuitableVacanciesNecessaryDemandsMyLvl(requestJsonString);
    }
    public String getSuitableVacanciesAllDemandsAllLvl(String requestJsonString){
        if(!serverStatus) return "Server is off";
        return EmployeeService.getSuitableVacanciesAllDemandsAllLvl(requestJsonString);
    }
    public String getVacanciesWithOneSuitableDemandsMyLvl(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return EmployeeService.getVacanciesWithOneSuitableDemandsMyLvl(requestJsonString);
    }
    public String setActiveEmployee(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return EmployeeService.setActiveEmployee(requestJsonString);
    }
    public String changeSkillLvl(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return EmployeeService.changeSkillLvl(requestJsonString);
    }
    public String changeSkillName(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return EmployeeService.changeSkillName(requestJsonString);
    }
    public String addSkill(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return EmployeeService.addSkill(requestJsonString);
    }


    public String exit(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return UserService.exitUser(requestJsonString);
    }

    public String login(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return UserService.loginUser(requestJsonString);
    }

    public String deleteAccaunt(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return requestJsonString;
    }

    public String addVacancy(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return requestJsonString;
    }

    public String getVacancies(String requestJsonString) {
        if(!serverStatus) return "Server is off";
        return requestJsonString;
    }

}
