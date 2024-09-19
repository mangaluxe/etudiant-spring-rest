package org.example.etudiant.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDtoReceive {

    @Size(min = 2, max = 99)
    private String username;

    @Size(min = 2, max = 99)
    private String password;

}
