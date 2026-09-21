package cl.bancocloud.bff.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa al usuario tal como lo expone banco-cloud-usuarios.
 * El BFF no tiene acceso a base de datos: este objeto solo sirve
 * para (de)serializar el JSON que viaja hacia/desde ese microservicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String nombre;
    private String email;
    private boolean activo;
    private boolean autorizado;
    private String rol;
}
