public class Provider implements ValidatePhone{
    String name;
    String phoneNumber;

    public Provider(String name, String phoneNumber) throws WrongPhoneNumberException {
        this.name = name;
        if(validatePhoneNumber(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }

    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) throws WrongPhoneNumberException {
        if (phoneNumber.matches("[0-9]{10}$")==false) {
            throw new WrongPhoneNumberException();
        }

        return true;

    }

}
