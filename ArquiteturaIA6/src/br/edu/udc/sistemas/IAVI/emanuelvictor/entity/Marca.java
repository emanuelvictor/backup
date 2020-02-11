package br.edu.udc.sistemas.IAVI.emanuelvictor.entity;

import br.edu.udc.sistemas.IAVI.emanuelvictor.annotation.*;

/**
 * Created by emanuelvictor on 09/09/14.
 */
@Entity
@Table(name="marca")
public class Marca {

    @Id
    @GeneratedValue
    @Column(name="idmarca",type=Column.INTEGER,unique=true,insertable=false,updatable=false,nullable=false)
    private Integer idMarca;

    @Column(name="descricao",type=Column.STRING,length=100,nullable=false)
    private String descricao;

    public Integer getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}