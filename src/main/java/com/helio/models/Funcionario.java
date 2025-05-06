package com.helio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "funcionarios")
public class Funcionario  { //extends Pessoa (removido pra evitar criar a nova tabela nula sem sentido)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
    @JoinColumn(name = "id_conta_poupanca")
    private ContaPoupanca conta;

    @OneToOne
    @JoinColumn(name = "id_pessoa") // ----------criado agora one to one e id_pessoa
    private Pessoa pessoa;  // <- aqui está o campo que permite setar a pessoa como pessoa.setPessoa(pessoa) na classe funcionarioDAO
    
    private double salario; // passar esse com this.
    private String cargo;

    public Funcionario (Pessoa pessoa, double salario, String cargo, ContaPoupanca conta){
        super(); // necessário ? pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getEndereco(), pessoa.getCpf() -- removi e funcionou   
        this.conta = conta;
        this.pessoa = pessoa; // tanto a (ContaPoupanca conta e Pessoa pessoa) - precisam ser atribuídas no construtor com this.
        this.salario = salario;
        this.cargo = cargo;
    }
 
    public void depositarSalario(){
        if (conta != null) {
            conta.depositar(this.salario);  // Chama o método depositar da conta
        } else {
            System.out.println("Erro: Funcionário não possui uma conta vinculada.");
        }  
    }
}
