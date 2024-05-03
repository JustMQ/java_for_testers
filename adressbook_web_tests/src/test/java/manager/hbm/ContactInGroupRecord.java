package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_in_groups")
public class ContactInGroupRecord {

    @Id
    @Column(name="id")
    public int id;

    @Id
    @Column(name="group_id")
    public int group_id;

    public ContactInGroupRecord () {}

    public ContactInGroupRecord (int id, int group_id) {
        this.id = id;
        this.group_id = group_id;
    }
}
