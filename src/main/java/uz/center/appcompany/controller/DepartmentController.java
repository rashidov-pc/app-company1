package uz.center.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.center.appcompany.entity.Department;
import uz.center.appcompany.payload.ApiResponse;
import uz.center.appcompany.payload.DepartmentDto;
import uz.center.appcompany.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public HttpEntity<?> getDepartments(){
        List<Department> departments = departmentService.getDdepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public HttpEntity<Department> getDepartmentById(@PathVariable Integer id){
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public HttpEntity<ApiResponse> addCustomer(@Valid @RequestBody DepartmentDto dto){
        ApiResponse response = departmentService.addDepartment(dto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDepartment(@PathVariable Integer id, @Valid @RequestBody DepartmentDto dto){
        ApiResponse response = departmentService.editDepartment(id, dto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDepartment(@PathVariable Integer id){
        ApiResponse response = departmentService.deleteDepartment(id);
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
