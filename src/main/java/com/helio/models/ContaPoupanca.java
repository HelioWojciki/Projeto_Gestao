package com.helio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;   

@Entity
@Table(name = "contas_poupancas")
public class ContaPoupanca extends ContaBancaria {

    @ManyToOne                      // Uso porque é muitas contas para uma pessoa
    @JoinColumn(name = "id_pessoa") // Nesta tabela crio a FK id_pessoa
    public Pessoa pessoa;           // Referenciado ao objeto Pessoa
    
    public ContaPoupanca(int id, String titular, double saldo, String conta, String agencia, Pessoa pessoa){
        super(id, titular, saldo, conta, agencia);
        this.pessoa = pessoa; 
    }

    public ContaPoupanca(){
    }

    public void depositar(double valor){
        if (valor > 0) {
            setSaldo(getSaldo()+valor);
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso!");
        } else {
            System.out.println("Valor de depósito inválido!");
        }
    }

    public void sacar(double valor){
        if (valor > 0 && getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque de R$ " + valor + " realizado com sucesso!");
        } else {
            System.out.println("Valor de saque inválido ou saldo insuficiente.");
        }
    }
}
