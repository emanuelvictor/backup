package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;

@Entity
@Table(name = "model")
public class Model {
	@Id
	@GeneratedValue
	@Column(name = "idmodel", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idModel;

	@Column(name = "description", type = Column.STRING, nullable = true, insertable = true, updatable = true, unique = false)
	private String description;

	@Column(name = "idbrand", type = Column.OBJECT, nullable = false)
	private Brand brand;

	public Integer getIdModel() {
		return idModel;
	}

	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idModel == null) {
			if (other.idModel != null)
				return false;
		} else if (!idModel.equals(other.idModel))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Model [idVehicle=" + idModel + ", description=" + description
				+ ", brand=" + brand + "]";
	}
}
