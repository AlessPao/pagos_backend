package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO
{
    @Min(value = 1)
    private Integer idCategory;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private boolean enabled;

    @Min(value = 1)
    @Max(value = 3)
    @NotNull
    private int code;
}
