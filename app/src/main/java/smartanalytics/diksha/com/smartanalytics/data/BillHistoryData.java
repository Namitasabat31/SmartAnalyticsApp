package smartanalytics.diksha.com.smartanalytics.data;

/**
 * Created by A1GLJZJ1 on 5/9/2018.
 */

public class BillHistoryData {

    String billMonth[];

    public String[] getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String[] billMonth) {
        this.billMonth = billMonth;
    }

    public int[] getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int[] billAmount) {
        this.billAmount = billAmount;
    }

    int billAmount[];
}
