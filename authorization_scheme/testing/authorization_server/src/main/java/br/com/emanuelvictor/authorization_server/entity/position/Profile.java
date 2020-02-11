package br.com.emanuelvictor.authorization_server.entity.position;


import javax.persistence.*;

@Entity
public class Profile {

    @Id
    private Integer profileId;

    @Column(unique = true)
    private String name;

    public Profile() {
        super();
    }

    public Profile(Profile profile) {
        super();
        this.profileId = profile.profileId;
        this.name = profile.name;
    }

    public Profile(Integer profileId, String name) {
        super();
        this.profileId = profileId;
        this.name = name;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
