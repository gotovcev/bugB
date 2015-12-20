import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "A")
public class MyTable {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="last_name")
    private String last_name;

    @Column(name="first_name")
    private String first_name;

    @Column(name="gender")
    private String gender;

    @Column(name="birthDate")
    private String birthDate;

    @Column(name="phoneNumber")
    private String phoneNumber;

    public MyTable() {}

    // getters

    public String getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public String getGender()
    {
        return gender;
    }
    public String getBirthDate()
    {
        return birthDate;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    // setters

    public void setId(String uuid)
    {
        this.id = uuid;
    }

    public void setLogin(String lastname)
    {
        this.login = lastname;
    }

    public void setPassword(String firstname)
    {
        this.password = firstname;
    }

    public void setLast_name(String middlename)
    {
        this.last_name = middlename;
    }

    public void setFirst_name(String first_name) { this.first_name = first_name;}

    public void setGender(String gender) { this.gender = gender;}

    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}