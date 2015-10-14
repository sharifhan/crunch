package crunch;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends CrudRepository<Salary, Long> {

    List<Salary> findByEmail(String email);
}