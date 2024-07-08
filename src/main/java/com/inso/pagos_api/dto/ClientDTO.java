package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO
{
    @Min(value = 1)
    private Integer idClient;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String lastName;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Size(min = 6, max = 60)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String address;
}
