package uz.center.appcompany.payload;

import lombok.Data;
import uz.center.appcompany.entity.Company;

@Data
public class DepartmentDto {
    private String name;
    private Company company;
}
