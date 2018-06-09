package smartanalytics.diksha.com.smartanalytics.commonutils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import smartanalytics.diksha.com.smartanalytics.data.BillData;
import smartanalytics.diksha.com.smartanalytics.data.CategoryItemizedData;
import smartanalytics.diksha.com.smartanalytics.data.TopManagementData;
import smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper;

import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.ACCOUNT_NO;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.ADJUSTMENTS;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.AMOUNT_PAYABLE;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.BILL_DATE;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.BILL_MONTH;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.BILL_NO;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.DUE_DATE;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.MOBILE_NO;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.MONTHLY_CHARGES;
import static smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper.USAGE_GROUP_DESCRIPTION;

/**
 * Created by A1GLJZJ1 on 5/18/2018.
 */

public class DBHelper {


    public TopManagementData showCustSegmentWiseRevenueData(String selectedMonth, SQLiteDatabase mDatabase) {

        TopManagementData data = new TopManagementData();
        String query = "select bill_mon,description,sum(this_months_charges)charges from dirs_file_master m, airtel_customer_classification c where m.status=5 " +
                "and m.CUSTOMER_CLASSIFICATION=c.classification_id AND bill_mon='"+selectedMonth+"' group by bill_mon,description;";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                       // billMonth[i] = cursor.getString(cursor.getColumnIndex("BILL_MON"));
                        desc[i] = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }

        return data;
    }

    public TopManagementData showCustSegmentWiseRevenue(SQLiteDatabase mDatabase) {
        TopManagementData data = new TopManagementData();
        String query = "select distinct bill_mon  from dirs_file_master m, airtel_customer_classification c where m.status=5\n" +
                "and m.CUSTOMER_CLASSIFICATION=c.classification_id group by bill_mon,description;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        billMonth[i] = cursor.getString(cursor.getColumnIndex("BILL_MON"));
                       /* desc[i] = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));*/
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }
        return  data;
    }

    public TopManagementData showBusinessUnitwiseRevenue(SQLiteDatabase mDatabase) {
        TopManagementData data = new TopManagementData();
        String query = "select distinct bill_mon from dirs_file_master m, airtel_business_unit b where m.status=5 " +
                "and m.business_unit=b.unit_id group by bill_mon,description;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        billMonth[i] = cursor.getString(cursor.getColumnIndex("BILL_MON"));
                       /* desc[i] = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));*/
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }
        return  data;
    }

    public TopManagementData showBusinessUnitwiseRevenueData(String selectedMonth, SQLiteDatabase mDatabase) {

        TopManagementData data = new TopManagementData();
        String query = "select bill_mon,description,sum(this_months_charges)charges from dirs_file_master m, airtel_business_unit b where m.status=5 " +
                "and m.business_unit=b.unit_id AND bill_mon = '"+selectedMonth+"' group by bill_mon,description;";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        // billMonth[i] = cursor.getString(cursor.getColumnIndex("BILL_MON"));
                        desc[i] = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }

        return data;
    }

    public TopManagementData top10RevenuePlansData(String selectedMonth, SQLiteDatabase mDatabase) {
        TopManagementData data = new TopManagementData();
        String query = "select bill_month,bill_plan_name,charges from ( select bill_month,bill_plan_name,sum(this_month_charges)charges from dirs_files_xml x, airtel_bill_plan_sum p " +
                "where file_type=2 and x.BILL_PLAN_ID = p.offer_id AND bill_month='"+selectedMonth+"' group by bill_month,bill_plan_name order by charges desc) limit 10;";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        // billMonth[i] = cursor.getString(cursor.getColumnIndex("BILL_MON"));
                        desc[i] = cursor.getString(cursor.getColumnIndex("bill_plan_name"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }

        return data;
    }

    public TopManagementData least10RevenuePlansData(String selectedMonth, SQLiteDatabase mDatabase) {
        TopManagementData data = new TopManagementData();
        String query = "select bill_month,bill_plan_name,charges from ( select bill_month,bill_plan_name,sum(this_month_charges)charges  from dirs_files_xml x, airtel_bill_plan_sum p\n" +
                "where file_type=2 and x.BILL_PLAN_ID = p.offer_id AND bill_month='"+selectedMonth+"' group by bill_month,bill_plan_name order by charges) limit 10;";
        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        // billMonth[i] = cursor.getString(cursor.getColumnIndex("BILL_MON"));
                        desc[i] = cursor.getString(cursor.getColumnIndex("bill_plan_name"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }

        return data;
    }

    public TopManagementData top10RevenuePlans(SQLiteDatabase mDatabase) {
        TopManagementData data = new TopManagementData();
        String query = "select distinct bill_month from ( select bill_month,bill_plan_name,sum(this_month_charges)charges from dirs_files_xml x, airtel_bill_plan_sum p " +
                "where file_type=2 and x.BILL_PLAN_ID = p.offer_id group by bill_month,bill_plan_name order by charges desc) limit 10;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        billMonth[i] = cursor.getString(cursor.getColumnIndex("bill_month"));
                       /* desc[i] = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));*/
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }
        return  data;

    }

    public TopManagementData least10RevenuePlans(SQLiteDatabase mDatabase) {
        TopManagementData data = new TopManagementData();
        String query = "select distinct bill_month from (select bill_month,bill_plan_name,sum(this_month_charges)charges  from dirs_files_xml x, airtel_bill_plan_sum p " +
                "where file_type=2 and x.BILL_PLAN_ID = p.offer_id group by bill_month,bill_plan_name order by charges) limit 10;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                int i = 0;
                String[] billMonth = new String[cursor.getCount()];
                int[] amount = new int[cursor.getCount()];
                String [] desc = new String[cursor.getCount()];
                if (cursor.moveToFirst()) {
                    do {
                        billMonth[i] = cursor.getString(cursor.getColumnIndex("bill_month"));
                       /* desc[i] = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                        amount[i] = cursor.getInt(cursor.getColumnIndex("charges"));*/
                        i++;
                    } while (cursor.moveToNext());
                }
                data.setAmount(amount);
                data.setDescription(desc);
                data.setBillMonth(billMonth);
                cursor.close();
                mDatabase.close();
            }

        }
        return  data;
    }

    public ArrayList<BillData> getBillDetails(String selectedAccNo, SQLiteDatabase mDatabase) {
        ArrayList<BillData> billArrayList = new ArrayList<>();
        String query = "select account_no,bill_no,bill_date,monthly_charges,adjustments,amount_payable,due_Date from user_analytics_Acc_summary  " +
                "where account_no='" + selectedAccNo + "'";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        BillData data = new BillData();
                        data.setAccNo(cursor.getString(cursor.getColumnIndex(ACCOUNT_NO)));
                        data.setBillNo(cursor.getString(cursor.getColumnIndex(BILL_NO)));
                        data.setBilldate(CommonUtility.getShowDate(cursor.getString(cursor.getColumnIndex(BILL_DATE))));
                        data.setMonthlyCharges(cursor.getString(cursor.getColumnIndex(MONTHLY_CHARGES)));
                        data.setAdjustments(cursor.getString(cursor.getColumnIndex(ADJUSTMENTS)));
                        data.setAmountPayable(cursor.getString(cursor.getColumnIndex(AMOUNT_PAYABLE)));
                        data.setDuedate(CommonUtility.getShowDate(cursor.getString(cursor.getColumnIndex(DUE_DATE))));
                        data.setMonthHeader(CommonUtility.formatNewDate(cursor.getString(cursor.getColumnIndex(BILL_DATE))));
                        billArrayList.add(data);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                mDatabase.close();
            }

        }
        return billArrayList;
    }

    public ArrayList<CategoryItemizedData> getItemizedHoursData(String selectedAccNo, String selectedInvoiceNo, SQLiteDatabase mDatabase) {
        ArrayList<CategoryItemizedData> categoryItemizedDataArrayList = new ArrayList<>();

        String query = "select distinct account_no,invoice_no,mobile_no,bill_month,USAGE_GROUP_DESCRIPTION,usage_hour,sum(amount) from (select distinct account_no, " +
                "invoice_no,mobile_no,bill_month,t.USAGE_GROUP_DESCRIPTION, strftime('%H',usage_time) as usage_hour,sum(usage_amount) amount " +
                "from user_analytics_itemized i,analytics_usage_Types t where i.usage_id=t.usage_id and i.account_no='" + selectedAccNo + "' AND i.invoice_no ='" + selectedInvoiceNo + "' " +
                "group by account_no,invoice_no,mobile_no,bill_month,t.USAGE_GROUP_DESCRIPTION,usage_time)a group by account_no,invoice_no,mobile_no,bill_month, " +
                "USAGE_GROUP_DESCRIPTION,usage_hour order by invoice_no,mobile_no,USAGE_GROUP_DESCRIPTION desc;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        CategoryItemizedData data = new CategoryItemizedData();
                        data.setMobileNo(cursor.getString(cursor.getColumnIndex("mobile_no")));
                        data.setUsageType(cursor.getString(cursor.getColumnIndex(USAGE_GROUP_DESCRIPTION)));
                        data.setBillMonth(cursor.getString(cursor.getColumnIndex("bill_month")));
                        data.setUsageHours(cursor.getString(cursor.getColumnIndex("usage_hour")));
                        data.setAmount(cursor.getString(cursor.getColumnIndex("sum(amount)")));
                        categoryItemizedDataArrayList.add(data);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                mDatabase.close();
            }

        }
        return categoryItemizedDataArrayList;

    }

    public ArrayList<CategoryItemizedData> getItemizedCostData(String selectedAccNo, String selectedInvoiceNo, SQLiteDatabase mDatabase) {
        ArrayList<CategoryItemizedData> categoryItemizedDataArrayList = new ArrayList<>();
        String query = "select account_no,invoice_no,mobile_no,bill_month,t.USAGE_GROUP_DESCRIPTION,ltrim(usage_date)usage_date, sum (usage_amount) amount " +
                "from user_analytics_itemized i,analytics_usage_Types t where i.usage_id=t.usage_id and i.account_no='" + selectedAccNo + "' AND i.invoice_no ='" + selectedInvoiceNo + "' " +
                "group by account_no,invoice_no,mobile_no,bill_month,t.USAGE_GROUP_DESCRIPTION,usage_date order by invoice_no,mobile_no,t.USAGE_GROUP_DESCRIPTION desc;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        CategoryItemizedData data = new CategoryItemizedData();
                        data.setMobileNo(cursor.getString(cursor.getColumnIndex(MOBILE_NO)));
                        data.setBillMonth(cursor.getString(cursor.getColumnIndex(BILL_MONTH)));
                        data.setUsageType(cursor.getString(cursor.getColumnIndex(USAGE_GROUP_DESCRIPTION)));
                        data.setUsageDate(CommonUtility.getShowDate(cursor.getString(cursor.getColumnIndex("usage_date"))));
                        data.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
                        categoryItemizedDataArrayList.add(data);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                mDatabase.close();
            }

        }
        return categoryItemizedDataArrayList;
    }

    public ArrayList<CategoryItemizedData> getItemizedUsageData(String selectedAccNo, String selectedInvoiceNo, SQLiteDatabase mDatabase) {
        ArrayList<CategoryItemizedData> categoryItemizedDataArrayList = new ArrayList<>();
        String query = "select account_no,invoice_no,mobile_no,bill_month,t.USAGE_GROUP_DESCRIPTION,ltrim(usage_date)usage_date, sum(pulse) usage_units from " +
                "user_analytics_itemized i,analytics_usage_Types t where i.usage_id=t.usage_id and i.account_no='" + selectedAccNo + "' AND i.invoice_no ='" + selectedInvoiceNo + "' " +
                "group by account_no,invoice_no,mobile_no,bill_month,t.USAGE_GROUP_DESCRIPTION,usage_date " +
                "order by invoice_no,mobile_no,t.USAGE_GROUP_DESCRIPTION desc;";

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        CategoryItemizedData data = new CategoryItemizedData();
                        data.setMobileNo(cursor.getString(cursor.getColumnIndex(MOBILE_NO)));
                        data.setBillMonth(cursor.getString(cursor.getColumnIndex(BILL_MONTH)));
                        data.setUsageType(cursor.getString(cursor.getColumnIndex(USAGE_GROUP_DESCRIPTION)));
                        data.setUsageDate(CommonUtility.getShowDate(cursor.getString(cursor.getColumnIndex("usage_date"))));
                        data.setAmount(cursor.getString(cursor.getColumnIndex("usage_units")));
                        categoryItemizedDataArrayList.add(data);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                mDatabase.close();
            }

        }
        return categoryItemizedDataArrayList;
    }
}

