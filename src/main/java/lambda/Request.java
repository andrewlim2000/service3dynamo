package lambda;

/**
 *
 */
public class Request {

    private String table;
   
    private String region;
    private String itemType;
    private String salesChannel;
    private String orderPriority;
    private String country;
    
    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the salesChannel
     */
    public String getSalesChannel() {
        return salesChannel;
    }

    /**
     * @param salesChannel the salesChannel to set
     */
    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    /**
     * @return the orderPriority
     */
    public String getOrderPriority() {
        return orderPriority;
    }

    /**
     * @param orderPriority the orderPriority to set
     */
    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

}