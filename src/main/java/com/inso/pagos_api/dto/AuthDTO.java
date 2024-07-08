package com.inso.pagos_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDTO
{
    @NotNull
    @Email
    @Length(min = 6, max = 100)
    private String email;

    @NotNull
    @Length(min = 6, max = 60)
    private String password;
}
