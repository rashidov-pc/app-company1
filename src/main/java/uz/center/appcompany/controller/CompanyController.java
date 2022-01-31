package uz.center.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.center.appcompany.entity.Company;
import uz.center.appcompany.payload.ApiResponse;
import uz.center.appcompany.payload.CompanyDto;
import uz.center.appcompany.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public HttpEntity<?> all() {
        List<Company> companies = companyService.all();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PostMapping
    public HttpEntity<?> addCompany(@Valid @RequestBody CompanyDto dto) {
        ApiResponse response = companyService.saveCompany(dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCompany(@Valid @RequestBody CompanyDto companyDto, @PathVariable Integer id) {
        ApiResponse response = companyService.editCompany(companyDto, id);
        return ResponseEntity.status(response.isSuccess() ? 202 : 409).body(response);
    }

    @DeleteMapping("/remove/{id}")
    public HttpEntity<?> remove(@PathVariable Integer id) {
        ApiResponse response = companyService.remove(id);
        return ResponseEntity.status(response.isSuccess() ? 202 : 409).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
