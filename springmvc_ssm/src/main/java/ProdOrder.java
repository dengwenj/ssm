public class ProdOrder {
    private String machine;
    private String date;
    private String daySort;

    public ProdOrder() {
    }

    public ProdOrder(String machine, String date, String daySort) {
        this.machine = machine;
        this.date = date;
        this.daySort = daySort;
    }

    /**
     * 获取
     * @return machine
     */
    public String getMachine() {
        return machine;
    }

    /**
     * 设置
     * @param machine
     */
    public void setMachine(String machine) {
        this.machine = machine;
    }

    /**
     * 获取
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取
     * @return daySort
     */
    public String getDaySort() {
        return daySort;
    }

    /**
     * 设置
     * @param daySort
     */
    public void setDaySort(String daySort) {
        this.daySort = daySort;
    }

    public String toString() {
        return "ProdOrder{machine = " + machine + ", date = " + date + ", daySort = " + daySort + "}";
    }
}
