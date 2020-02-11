package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "idproduct", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idProduct;

	@Column(name = "description", type = Column.STRING, length = 150, nullable = true)
	private String description;

	@Column(name = "value", type = Column.FLOAT, nullable = true, insertable = true, updatable = true, unique = false)
	private Float value;
		
	@Column(name = "units", type = Column.INTEGER, nullable = true, insertable = true, updatable = true, unique = false)
	private Integer units;

	@Column(name = "idmodel", type = Column.OBJECT, nullable = false)
	private Model model;
	
	@Column(name = "brand", type = Column.STRING, nullable = false)
	private String brand;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "idsupplier", type = Column.OBJECT, nullable = false)
	private Supplier supplier;
	
	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", description="
				+ description + ", value=" + value + ", units=" + units
				+ ", model=" + model + ", brand=" + brand + ", supplier="
				+ supplier + "]";
	}

	
}