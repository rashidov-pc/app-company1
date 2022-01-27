package uz.center.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.center.appcompany.entity.Address;
import uz.center.appcompany.entity.Company;
import uz.center.appcompany.payload.ApiResponse;
import uz.center.appcompany.payload.CompanyDto;
import uz.center.appcompany.repository.AddressRepository;
import uz.center.appcompany.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse all(){
        List<Company> all = companyRepository.findAll();
        return new ApiResponse("ok", true);
    }

    public ApiResponse getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Company not found", false);
        }

        return new ApiResponse("ok", true);
    }

    public ApiResponse saveCompany(CompanyDto dto){
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setHomeNumber(dto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Company company = new Company();
        company.setCorpName(dto.getCorpName());
        company.setDirectorName(dto.getDirectorName());
        company.setAddress(savedAddress);

        Company savedCompany = companyRepository.save(company);

        return new ApiResponse("Saved", true);
    }

    public ApiResponse editCompany(CompanyDto dto, Integer id){
        try {
            Company company = companyRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("Company not found"));
            Address address = new Address();
            address.setStreet(dto.getStreet());
            address.setHomeNumber(dto.getHomeNumber());
            company.setAddress(address);
            company.setCorpName(dto.getCorpName());
            company.setDirectorName(dto.getDirectorName());
            companyRepository.save(company);
            return new ApiResponse("Edited", true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse remove(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

}
