package vip.dengwj.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Character gender;
    private String address;

    public User() {
    }

    public User(Integer id, String username, String password, Character gender, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.address = address;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return gender
     */
    public Character getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(Character gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", gender = " + gender + ", address = " + address + "}";
    }
}
