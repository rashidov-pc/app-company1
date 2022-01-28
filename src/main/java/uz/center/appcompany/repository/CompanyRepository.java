package uz.center.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.center.appcompany.entity.Address;
import uz.center.appcompany.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Integer id);
}
