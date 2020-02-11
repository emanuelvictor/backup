package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;

@Entity
@Table(name = "brand")
public class Brand {

	// Variables and annotations
	@Id
	@GeneratedValue
	@Column(name = "idbrand", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idBrand;

	@Column(name = "description", type = Column.STRING, length = 150, nullable = false)
	private String description;

	// Methods
	public Integer getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Integer idBrand) {
		this.idBrand = idBrand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Brand [idBrand=" + idBrand + ", description=" + description
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idBrand == null) {
			if (other.idBrand != null)
				return false;
		} else if (!idBrand.equals(other.idBrand))
			return false;
		return true;
	}

}
