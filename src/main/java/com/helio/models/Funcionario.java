package com.helio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Funcionario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
    @JoinColumn(name = "id_conta_poupanca")
    private ContaPoupanca conta;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    
    private double salario;
    private String cargo;

    public Funcionario (Pessoa pessoa, double salario, String cargo, ContaPoupanca conta, Empresa empresa){
        super(); 
        this.conta = conta;
        this.pessoa = pessoa;
        this.salario = salario;
        this.cargo = cargo;
        this.empresa = empresa;
    }
 
    public void depositarSalario(){
        if (conta != null) {
            conta.depositar(this.salario);
        } else {
            System.out.println("Erro: Funcionário não possui uma conta vinculada.");
        }  
    }

    @Override
    public String toString() {
        String info = "\nFuncionário {\n" +
                    "              - Id = " + getId() +
                    "\n              - Nome = " + pessoa.getNome() +
                    "\n              - NrConta = " + conta.getConta() +
                    "\n              - Cargo = " + getCargo() +
                    "\n              - Salário = " + getSalario();

        if (empresa != null && empresa.getNomeEmpresa() != null) {
            info += "\n              - Empresa = " + empresa.getNomeEmpresa();
        }

        info += "\n}";
        return info;
    }

}
