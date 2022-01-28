package uz.center.appcompany.payload;

import lombok.Data;
import uz.center.appcompany.entity.Company;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {
    @NotNull
    private String name;

    @NotNull
    private Company company;
}
