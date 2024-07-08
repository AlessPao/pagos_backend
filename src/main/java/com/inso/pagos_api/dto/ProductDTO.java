package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO
{
    @EqualsAndHashCode.Include
    private Integer idProduct;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String description;

    @Min(value = 1)
    @Max(value = 9999)
    private double price;

    @NotNull
    private boolean enabled;
}
