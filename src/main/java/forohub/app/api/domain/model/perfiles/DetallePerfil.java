package forohub.app.api.domain.model.perfiles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String campo; // Ej.: "fechaNacimiento", "correo"

    @Column(nullable = false)
    private String valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibilidad visibilidad; // Público, Privado, Semi-Privado

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}
