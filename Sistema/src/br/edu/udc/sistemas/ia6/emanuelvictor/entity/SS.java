package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import java.util.Arrays;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;
@Entity
@Table(name = "ss")
public class SS {

	//TODO repensar e refazer toda OS e SS
	
	@Id
	@GeneratedValue
	@Column(name = "idss", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idSS;

	@Column(name = "description", type = Column.STRING, nullable = true, insertable = true, updatable = true, unique = false)
	private String description;

	@Column(name = "valuehour", type = Column.FLOAT, nullable = false)
	private Float valueHour;

	@Column(name = "idss_product", type = Column.OBJECT, nullable = true)
	private Product[] product;

	public Integer getIdSS() {
		return idSS;
	}

	public void setIdSS(Integer idSS) {
		this.idSS = idSS;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getValueHour() {
		return valueHour;
	}

	public void setValueHour(Float valueHour) {
		this.valueHour = valueHour;
	}

	public Product[] getProduct() {
		return product;
	}

	public void setProduct(Product[] product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "SS [idSS=" + idSS + ", description=" + description
				+ ", valueHour=" + valueHour + ", product="
				+ Arrays.toString(product) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idSS == null) ? 0 : idSS.hashCode());
		result = prime * result + Arrays.hashCode(product);
		result = prime * result
				+ ((valueHour == null) ? 0 : valueHour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SS other = (SS) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idSS == null) {
			if (other.idSS != null)
				return false;
		} else if (!idSS.equals(other.idSS))
			return false;
		if (!Arrays.equals(product, other.product))
			return false;
		if (valueHour == null) {
			if (other.valueHour != null)
				return false;
		} else if (!valueHour.equals(other.valueHour))
			return false;
		return true;
	}
}
