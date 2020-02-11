package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import java.util.Arrays;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;

@Entity
@Table(name = "os")
public class SO {
	@Id
	@GeneratedValue
	@Column(name = "idso", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idSO;

	@Column(name = "opendate", type = Column.DATE, nullable = true, insertable = true, updatable = true, unique = false)
	private String openDate;

	@Column(name = "closedate", type = Column.DATE, nullable = true, insertable = true, updatable = true, unique = false)
	private String closeDate;

	@Column(name = "description", type = Column.STRING, nullable = true, insertable = true, updatable = true, unique = false)
	private String description;

	@Column(name = "idclient", type = Column.OBJECT, nullable = false)
	private Client client;

	@Column(name = "idvehicle", type = Column.OBJECT, nullable = false)
	private Vehicle vehicle;

	@Column(name = "idproduct", type = Column.OBJECT, nullable = false)
	private Product[] product;

	@Column(name = "subtotalproduct", type = Column.FLOAT, nullable = true, insertable = true, updatable = true, unique = false)
	private Float subTotalProduct;

	@Column(name = "idservice", type = Column.OBJECT, nullable = false)
	private SS[] service;

	@Column(name = "subtotalservice", type = Column.FLOAT, nullable = true, insertable = true, updatable = true, unique = false)
	private Float subTotalService;

	

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Product[] getProduct() {
		return product;
	}

	public void setProduct(Product[] product) {
		this.product = product;
	}

	public Float getSubTotalProduct() {
		return subTotalProduct;
	}

	public void setSubTotalProduct(Float subTotalProduct) {
		this.subTotalProduct = subTotalProduct;
	}

	public SS[] getService() {
		return service;
	}

	public void setService(SS[] service) {
		this.service = service;
	}

	public Float getSubTotalService() {
		return subTotalService;
	}

	public void setSubTotalService(Float subTotalService) {
		this.subTotalService = subTotalService;
	}

	public Integer getIdSO() {
		return idSO;
	}

	public void setIdSO(Integer idSO) {
		this.idSO = idSO;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SO other = (SO) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (closeDate == null) {
			if (other.closeDate != null)
				return false;
		} else if (!closeDate.equals(other.closeDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idSO == null) {
			if (other.idSO != null)
				return false;
		} else if (!idSO.equals(other.idSO))
			return false;
		if (openDate == null) {
			if (other.openDate != null)
				return false;
		} else if (!openDate.equals(other.openDate))
			return false;
		if (!Arrays.equals(product, other.product))
			return false;
		if (!Arrays.equals(service, other.service))
			return false;
		if (subTotalProduct == null) {
			if (other.subTotalProduct != null)
				return false;
		} else if (!subTotalProduct.equals(other.subTotalProduct))
			return false;
		if (subTotalService == null) {
			if (other.subTotalService != null)
				return false;
		} else if (!subTotalService.equals(other.subTotalService))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SO [idSO=" + idSO + ", openDate=" + openDate + ", closeDate="
				+ closeDate + ", description=" + description + ", client="
				+ client + ", vehicle=" + vehicle + ", product="
				+ Arrays.toString(product) + ", subTotalProduct="
				+ subTotalProduct + ", service=" + Arrays.toString(service)
				+ ", subTotalService=" + subTotalService + "]";
	}

	

}
