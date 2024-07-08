package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDTO
{

    @Min(value = 1)
    private Integer idCompany;

    @Size(min = 3, max = 100)
    private String name;

    @Size(min = 11, max = 11)
    private String ruc;

    @Size(min = 3, max = 100)
    private String address;

    @Min(value = 1)
    @Max(value = 3)
    @NotNull
    private int code;
}
