package com.helio.models;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class ContaBancaria {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titular;
    private double saldo;

    @Column(unique = true) 
    private String conta;
    private String agencia;
    
    public abstract void depositar(double valor);

    public abstract void sacar(double valor);

    @Override
    public String toString() {
        return "\nConta Pupança {\n" +
                "              - Id=" + getId() +
                "\n              - Titular=" + getTitular() +
                "\n              - NrConta=" + getConta()  +
                "\n              - Agencia=" + getAgencia() +
                "\n              - Saldo=" + getSaldo() + "\n}";
    }

}
