package br.com.emanuelvictor.funcionario2.entity.position;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
public class Position {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer positionId;
	
	@NotBlank(message = "O campo nome é obrigatório")
	@Column(unique = true)
	private String name;
	
	
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public Position(Position position) {
		super();
		this.positionId = position.positionId;
		this.name = position.name;
	}

	public Position(Integer positionId, String name) {
		super();
		this.positionId = positionId;
		this.name = name;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
