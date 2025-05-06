package com.helio.ui;

import static com.helio.dao.PessoaDao.criarPersistenciaPessoa;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class SubmenuPessoaTest {

    @Test
    public void deveLancarExcecaoSeCpfForInvalido(){
        //teste para o campo cpf vazio
        Exception exceptionCpfVazio = assertThrows(Exception.class, ()->{
            criarPersistenciaPessoa(0, "nomeTeste1", 20, "Rua enderecoTeste1", "");
        });
        assertEquals("CPF inválido", exceptionCpfVazio.getMessage());
        
    }
}
