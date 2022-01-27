package uz.center.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.center.appcompany.entity.Address;

public interface WorkerRepository extends JpaRepository<Address, Integer> {
}
