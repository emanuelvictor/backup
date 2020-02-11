package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;

@Entity
@Table(name = "client")
public class Client {

	// Variables and annotations
	@Id
	@GeneratedValue
	@Column(name = "idclient", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idClient;

	@Column(name = "name", type = Column.STRING, length = 100, nullable = false)
	private String name;

	@Column(name = "fantasyname", type = Column.STRING, length = 100, nullable = false)
	private String fantasyName;

	@Column(name = "cnpj", type = Column.STRING, length = 25, nullable = false)
	private String CNPJ;

	@Column(name = "stateregistration", type = Column.STRING, length = 100, nullable = false)
	private String stateRegistration;

	@Column(name = "corporatename", type = Column.STRING, length = 100, nullable = false)
	private String corporateName;

	@Column(name = "rg", type = Column.STRING, length = 18, nullable = true)
	private String rg;

	@Column(name = "cpf", type = Column.STRING, length = 25, nullable = false)
	private String cpf;

	@Column(name = "address", type = Column.STRING, length = 400, nullable = true)
	private String address;

	@Column(name = "cep", type = Column.STRING, length = 100, nullable = true)
	private String cep;

	@Column(name = "email", type = Column.STRING, length = 400, nullable = true)
	private String email;

	@Column(name = "phone", type = Column.STRING, length = 18, nullable = true)
	private String phone;

	@Column(name = "cellular", type = Column.STRING, length = 25, nullable = true)
	private String cellular;

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellular() {
		return cellular;
	}

	public void setCellular(String cellular) {
		this.cellular = cellular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CNPJ == null) ? 0 : CNPJ.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((cellular == null) ? 0 : cellular.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result
				+ ((corporateName == null) ? 0 : corporateName.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fantasyName == null) ? 0 : fantasyName.hashCode());
		result = prime * result
				+ ((idClient == null) ? 0 : idClient.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime
				* result
				+ ((stateRegistration == null) ? 0 : stateRegistration
						.hashCode());
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
		Client other = (Client) obj;
		if (CNPJ == null) {
			if (other.CNPJ != null)
				return false;
		} else if (!CNPJ.equals(other.CNPJ))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cellular == null) {
			if (other.cellular != null)
				return false;
		} else if (!cellular.equals(other.cellular))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (corporateName == null) {
			if (other.corporateName != null)
				return false;
		} else if (!corporateName.equals(other.corporateName))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fantasyName == null) {
			if (other.fantasyName != null)
				return false;
		} else if (!fantasyName.equals(other.fantasyName))
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (stateRegistration == null) {
			if (other.stateRegistration != null)
				return false;
		} else if (!stateRegistration.equals(other.stateRegistration))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", name=" + name
				+ ", fantasyName=" + fantasyName + ", CNPJ=" + CNPJ
				+ ", stateRegistration=" + stateRegistration
				+ ", corporateName=" + corporateName + ", rg=" + rg + ", cpf="
				+ cpf + ", address=" + address + ", cep=" + cep + ", email="
				+ email + ", phone=" + phone + ", cellular=" + cellular + "]";
	}

}
