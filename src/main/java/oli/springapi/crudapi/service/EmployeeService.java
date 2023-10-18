package oli.springapi.crudapi.service;

import java.util.List;

import oli.springapi.crudapi.model.Employee;

public interface EmployeeService {
    List<Employee> getEmployees();

    List<Employee> getEmployees(int pageNumber, int pageSize);

    Employee getEmployee(Long id);

    Employee saveEmployee(Employee employee);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeesByNameContaining(String name);
}
