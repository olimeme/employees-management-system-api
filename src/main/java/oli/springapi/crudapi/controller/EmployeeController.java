package oli.springapi.crudapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import oli.springapi.crudapi.model.Employee;
import oli.springapi.crudapi.service.EmployeeService;

@RestController // @Controller + @ResponseBody
public class EmployeeController {

    @Autowired
    private EmployeeService eService;

    @Value("${app.name: Employee Tracker}")
    private String appName;

    @Value("${app.version: v1}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppDetails() {
        return appName + "-" + appVersion;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployeesByPageNumberAndPageSize(@RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    // @GetMapping("/employees")
    // public ResponseEntity<List<Employee>> getEmployees() {
    // return new ResponseEntity<List<Employee>>(eService.getEmployees(),
    // HttpStatus.OK);
    // }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<Employee>(eService.getEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updatEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("id") Long id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name,
            @RequestParam String location) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location),
                HttpStatus.OK);
    }

    @GetMapping("/employees/filterByNameContaining")
    public ResponseEntity<List<Employee>> getEmployeesByNameContaining(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameContaining(name),
                HttpStatus.OK);
    }
}
