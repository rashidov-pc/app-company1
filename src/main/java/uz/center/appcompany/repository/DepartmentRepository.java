package uz.center.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.center.appcompany.entity.Address;
import uz.center.appcompany.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
