package DataStructures;

public class Contact {
    String firstName,lastName,email,address,phone, FullName;
    public Contact(){
        FullName="0";
    }
    public Contact(String firstName,String lastName, String email, String
            address, String phone){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.address=address;
        this.phone=phone;
        FullName= firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String printInfo(){
        return "Name: " +FullName + "\n" +
                "Phone number: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Address: "+ address;
    }
    public String toString(){
        return FullName;
    }
}
