package oli.springapi.crudapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oli.springapi.crudapi.model.Employee;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);

    List<Employee> findByNameAndLocation(String name, String location);

    List<Employee> findByNameContaining(String name);
}
