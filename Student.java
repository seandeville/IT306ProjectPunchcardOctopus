
public class Student{
    private String FirstName;
    private String LastName;
    private String GNumber;
    private String PhoneNumber;
    private String EmailID;
    private String ShippingAddress;
    public Student(){
            this.FirstName = "";
            this.LastName = "";
            this.GNumber = "";
            this.PhoneNumber = "";
            this.EmailID = "";
            this.ShippingAddress = "";
    }

    public Student(String FirstNames, String LastNames, String GNumbers, String PhoneNumbers, String EmailIDs, String ShippingAdd){
        this.FirstName = FirstNames;
        this.LastName = LastNames;
        this.GNumber = GNumbers;
        this.PhoneNumber = PhoneNumbers;
        this.EmailID = EmailIDs;
        this.ShippingAddress = ShippingAdd;
    }
    public String getFirstName(){
        return this.FirstName;
    }

    public String getLastName(){
        return this.LastName;
    }

    public String getGNumber(){
        return this.GNumber;
    }

    public String getPhoneNumber(){
        return this.PhoneNumber;
    }

    public String getEmailID(){
        return this.EmailID;
    }

    public String getShippingAddress(){
        return this.ShippingAddress;
    }
}
