package smartanalytics.diksha.com.smartanalytics.data;

/**
 * Created by A1GLJZJ1 on 5/9/2018.
 */

public class AccountSummaryData {

    public String getPrevBalance() {
        return prevBalance;
    }

    public void setPrevBalance(String prevBalance) {
        this.prevBalance = prevBalance;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(String adjustments) {
        this.adjustments = adjustments;
    }

    public String getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(String monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public String getAmountdueAfterDueDate() {
        return amountdueAfterDueDate;
    }

    public void setAmountdueAfterDueDate(String amountdueAfterDueDate) {
        this.amountdueAfterDueDate = amountdueAfterDueDate;
    }

    String prevBalance,payments,adjustments,monthlyRental,usage,taxes,amountDue,amountdueAfterDueDate;
}
