package br.com.fiap.Auxilia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Triagem {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String doenca;
    private String resultado;
    private String detalhes;
    @ManyToOne
    private Usuario usuario;
}
