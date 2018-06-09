package smartanalytics.diksha.com.smartanalytics.data;

import java.util.ArrayList;

/**
 * Created by A1GLJZJ1 on 5/18/2018.
 */

public class TopManagementData {
    public String[] getBillMonth() {
        return billMonth;
    }


    public void setBillMonth(String[] billMonth) {
        this.billMonth = billMonth;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public int[] getAmount() {
        return amount;
    }

    public void setAmount(int[] amount) {
        this.amount = amount;
    }

    String[] billMonth,description;
    int [] amount;
}
