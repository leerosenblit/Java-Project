public class Address {
    private String street;
    private String blockNumber;
    private String city;
    private String state;



    public Address(String street, String blockNumber, String city, String state) {
        this.street = street;
        this.blockNumber = blockNumber;
        this.city = city;
        this.state = state;
    }

    public Address(Address other) {
        this.street = other.street;
        this.blockNumber = other.blockNumber;
        this.city = other.city;
        this.state = other.state;
    }

    public String getStreet() {
        return street;
    }
    public String getBlockNumber() {
        return blockNumber;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
