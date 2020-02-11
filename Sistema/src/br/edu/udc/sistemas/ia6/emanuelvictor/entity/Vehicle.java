package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	@Id
	@GeneratedValue
	@Column(name = "idvehicle", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idVehicle;

	@Column(name = "plaque", type = Column.STRING, length = 15, nullable = false)
	private String plaque;

	@Column(name = "chassis", type = Column.STRING, length = 100, nullable = false)
	private String chassis;

	@Column(name = "idmodel", type = Column.OBJECT, nullable = false)
	private Model model;

	@Column(name = "idclient", type = Column.OBJECT, nullable = false)
	private Client client;

	public Integer getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(Integer idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getPlaque() {
		return plaque;
	}

	public void setPlaque(String plaque) {
		this.plaque = plaque;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (chassis == null) {
			if (other.chassis != null)
				return false;
		} else if (!chassis.equals(other.chassis))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (idVehicle == null) {
			if (other.idVehicle != null)
				return false;
		} else if (!idVehicle.equals(other.idVehicle))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (plaque == null) {
			if (other.plaque != null)
				return false;
		} else if (!plaque.equals(other.plaque))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [idVehicle=" + idVehicle + ", plaque=" + plaque
				+ ", chassis=" + chassis + ", model=" + model + ", client="
				+ client + "]";
	}

}
