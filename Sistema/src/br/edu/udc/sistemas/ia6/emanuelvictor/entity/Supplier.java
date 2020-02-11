package br.edu.udc.sistemas.ia6.emanuelvictor.entity;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue
	@Column(name = "idsupplier", type = Column.INTEGER, nullable = false, insertable = false, updatable = false, unique = true)
	private Integer idSupplier;

	@Column(name = "fantasyname", type = Column.STRING, length = 100, nullable = false)
	private String fantasyName;

	@Column(name = "email", type = Column.STRING, length = 400, nullable = true)
	private String email;

	@Column(name = "phone", type = Column.STRING, length = 18, nullable = true)
	private String phone;

	@Column(name = "cellular", type = Column.STRING, length = 25, nullable = true)
	private String cellular;

	@Column(name = "cep", type = Column.STRING, length = 400, nullable = true)
	private String CEP;

	@Column(name = "address", type = Column.STRING, length = 400, nullable = true)
	private String address;

	@Column(name = "cnpj", type = Column.STRING, length = 25, nullable = false)
	private String CNPJ;

	@Column(name = "stateregistration", type = Column.STRING, length = 100, nullable = false)
	private String stateRegistration;

	@Column(name = "corporatename", type = Column.STRING, length = 100, nullable = false)
	private String corporateName;

	public Integer getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(Integer idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
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

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CEP == null) ? 0 : CEP.hashCode());
		result = prime * result + ((CNPJ == null) ? 0 : CNPJ.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((cellular == null) ? 0 : cellular.hashCode());
		result = prime * result
				+ ((corporateName == null) ? 0 : corporateName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fantasyName == null) ? 0 : fantasyName.hashCode());
		result = prime * result
				+ ((idSupplier == null) ? 0 : idSupplier.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Supplier other = (Supplier) obj;
		if (CEP == null) {
			if (other.CEP != null)
				return false;
		} else if (!CEP.equals(other.CEP))
			return false;
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
		if (corporateName == null) {
			if (other.corporateName != null)
				return false;
		} else if (!corporateName.equals(other.corporateName))
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
		if (idSupplier == null) {
			if (other.idSupplier != null)
				return false;
		} else if (!idSupplier.equals(other.idSupplier))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
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
		return "Supplier [idSupplier=" + idSupplier + ", fantasyName="
				+ fantasyName + ", email=" + email + ", phone=" + phone
				+ ", cellular=" + cellular + ", CEP=" + CEP + ", address="
				+ address + ", CNPJ=" + CNPJ + ", stateRegistration="
				+ stateRegistration + ", corporateName=" + corporateName + "]";
	}

}