package uz.center.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.center.appcompany.payload.ApiResponse;
import uz.center.appcompany.payload.CompanyDto;
import uz.center.appcompany.service.CompanyService;

import java.util.UUID;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/all")
    public HttpEntity<?>all(){
        ApiResponse response = companyService.all();
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/getCompany/{id}")
    public HttpEntity<?> search(@PathVariable Integer id){
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PostMapping("/addCompany")
    public HttpEntity<?> addCompany(@RequestBody CompanyDto dto){
        ApiResponse response = companyService.saveCompany(dto);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }

    @PutMapping("/editCompany/{id}")
    public HttpEntity<?> editCompany(@RequestBody CompanyDto companyDto, @PathVariable Integer id){
        ApiResponse response = companyService.editCompany(companyDto, id);
        return ResponseEntity.status(response.isSuccess()?202:409).body(response);
    }

    @GetMapping("/remove/{id}")
    public HttpEntity<?> remove(@PathVariable Integer id){
        ApiResponse response = companyService.remove(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

}
