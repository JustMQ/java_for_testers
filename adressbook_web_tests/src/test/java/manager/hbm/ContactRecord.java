package manager.hbm;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name="id")
    public int id;
    @Column(name="firstname")
    public String firstname;
    @Column(name="middlename")
    public String middlename;
    @Column(name="lastname")
    public String lastname;
    @Column(name="address")
    public String address;

    public String nickname = new String();
    public String company = new String();
    public String title = new String();
    public String addr_long = new String();
    public String addr_lat = new String();
    public String addr_status = new String();
    public String home = new String();
    public String mobile = new String();
    public String work = new String();
    public String phone2 = new String();
    public String fax = new String();
    public String email = new String();
    public String email2 = new String();
    public String email3 = new String();
    public String homepage = new String();

    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    public List<GroupRecord> groups;

    public ContactRecord () {}

    public ContactRecord (
            int id,
            String firstname,
            String middlename,
            String lastname,
            String address,
            String home,
            String mobile,
            String work,
            String email,
            String email2,
            String email3) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;

    }
}
