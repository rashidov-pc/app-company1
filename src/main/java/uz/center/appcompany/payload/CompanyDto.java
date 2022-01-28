package uz.center.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotNull
    private String street;

    @NotNull
    private String homeNumber;
}
