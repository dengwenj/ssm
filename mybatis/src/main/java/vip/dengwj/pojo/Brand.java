package vip.dengwj.pojo;

public class Brand {
    private Integer id;
    private String brandName;
    private String companyName;
    private int ordered;
    private String description;
    private int status;

    public Brand() {
    }

    public Brand(Integer id, String brandName, String companyName, int ordered, String description, int status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
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
     * @return brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取
     * @return ordered
     */
    public int getOrdered() {
        return ordered;
    }

    /**
     * 设置
     * @param ordered
     */
    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    /**
     * 获取
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public String toString() {
        return "Brand{id = " + id + ", brandName = " + brandName + ", companyName = " + companyName + ", ordered = " + ordered + ", description = " + description + ", status = " + status + "}";
    }
}
