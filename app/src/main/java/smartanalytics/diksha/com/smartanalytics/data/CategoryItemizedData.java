package smartanalytics.diksha.com.smartanalytics.data;

/**
 * Created by A1GLJZJ1 on 5/4/2018.
 */

public class CategoryItemizedData {

   String mobileNo;
    String categoryType;
    String subCatType;

    public String getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(String usageDate) {
        this.usageDate = usageDate;
    }

    String usageDate;

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public String getUsageHours() {
        return usageHours;
    }

    public void setUsageHours(String usageHours) {
        this.usageHours = usageHours;
    }

    String usageType;
    String billMonth;
    String usageHours;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getSubCatType() {
        return subCatType;
    }

    public void setSubCatType(String subCatType) {
        this.subCatType = subCatType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount;
}
