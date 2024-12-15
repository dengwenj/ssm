package vip.dengwj.domain;

public class Address {
    private String city;

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    /**
     * 获取
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return "Address{city = " + city + "}";
    }
}
