package model;

public record ContactData (
        String id,
        String firstName,
        String middleName,
        String lastName,
        String address,
        String photo) {

    public ContactData() {this("", "", "", "", "", "");}

    public ContactData withId(String id) { return new ContactData(id, this.firstName, this.middleName,this.lastName, this.address, this.photo);}
    public ContactData withFirstName(String firstName) {return new ContactData(this.id, firstName, this.middleName, this.lastName, this.address, this.photo);}
    public ContactData withLastName(String lastName) {return new ContactData(this.id, this.firstName, this.middleName, lastName, this.address, this.photo);}
    public ContactData withAddress(String address) {return new ContactData(this.id, this.firstName, this.middleName, this.lastName, address, this.photo);}
    public ContactData withPhoto(String photo) {return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.address, photo);}
}

