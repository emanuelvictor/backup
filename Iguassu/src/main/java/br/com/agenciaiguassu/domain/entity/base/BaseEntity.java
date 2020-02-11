package br.com.agenciaiguassu.domain.entity.base;

import br.com.agenciaiguassu.domain.entity.address.Endereco;

import javax.persistence.*;
import java.util.Calendar;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class BaseEntity extends SpringData<Long> {

	private static final long serialVersionUID = -1161960291273622919L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    protected Calendar dataDeCadastro = Calendar.getInstance();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    protected Calendar dataDeAlteracao = Calendar.getInstance();

    @Embedded
    protected Endereco endereco;

    public Endereco getEndereco() {
        if (endereco==null){
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Calendar getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Calendar dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public Calendar getDataDeAlteracao() {
        return dataDeAlteracao;
    }

    public void setDataDeAlteracao(Calendar dataDeAlteracao) {
        this.dataDeAlteracao = dataDeAlteracao;
    }
}