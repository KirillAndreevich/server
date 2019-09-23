package hiring.converter;

import hiring.dto.*;
import hiring.model.Employee;
import hiring.model.Employer;
import hiring.model.Skill;
import hiring.model.Vacancy;

public final class Convert {

    public static Employee from(RegisterEmployeeDtoRequest registerEmployeeDtoRequest) {
        Employee employee = new Employee(registerEmployeeDtoRequest.getLogin(), registerEmployeeDtoRequest.getPassword()
                , registerEmployeeDtoRequest.getEmail(), registerEmployeeDtoRequest.getFirstName(), registerEmployeeDtoRequest.getLastName(),
                registerEmployeeDtoRequest.getPatronymic());
        return employee;
    }
    public static RegisterEmployeeDtoResponse from(Employee employee){
        RegisterEmployeeDtoResponse registerEmployeeDtoResponse = new RegisterEmployeeDtoResponse(employee.getLogin(),
                employee.getEmail(),employee.getFirstName(),employee.getLastName(),employee.isActive(),employee.getPatronymic());
        return registerEmployeeDtoResponse;
    }

    public static Employer from(RegisterEmployerDtoRequest registerEmployerDtoRequest) {
        Employer employer = new Employer(registerEmployerDtoRequest.getLogin(),registerEmployerDtoRequest.getPassword(),registerEmployerDtoRequest.getEmail(),
                registerEmployerDtoRequest.getCompany(),registerEmployerDtoRequest.getAddress(),registerEmployerDtoRequest.getFirstName(),
                registerEmployerDtoRequest.getLastName(),registerEmployerDtoRequest.getPatronymic());
        return employer;
    }
    public static Vacancy from(NewVacancyDtoRequest newVacancyDtoRequest) {
        Vacancy vacancy = new Vacancy(newVacancyDtoRequest.getPosition(),newVacancyDtoRequest.getSalary(),newVacancyDtoRequest.getDemands());
        return vacancy;
    }
    public static Skill from(NewSkillDtoRequest newSkillDtoRequest) {
        Skill skill = new Skill(newSkillDtoRequest.getName(),newSkillDtoRequest.getLvl());
        return skill;
    }
}
