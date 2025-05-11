package com.helio.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresas")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomeEmpresa;
    private String cnpj;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    

    @Override
    public String toString() {
        return "\nEmpresa {\n" +
                "              - Id = " + getId() +
                "\n              - Nome da Empresa = " + getNomeEmpresa() +
                "\n              - Nr CNPJ = " + getCnpj() + "\n}";
    }

}
