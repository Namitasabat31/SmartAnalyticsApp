package smartanalytics.diksha.com.smartanalytics.fragment;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.Collator;
import java.util.Random;

import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.commonutils.CommonUtility;
import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.TopManagementData;
import smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustManagementFragment extends BaseFragment {
    private LinearLayout layoutChart, layoutText;
    private GraphicalView mChartView;
    private XYMultipleSeriesRenderer multiRenderer;
    private XYSeriesRenderer seriesRenderer;
    private XYMultipleSeriesDataset dataset;
    private XYSeries xySeries;
    private int[] yaxisdata;
    private String[] xaxisdata;
    private TextView tvChartTitle,tvplan1,tvplan2,tvplan3,tvplan4,tvplan5,tvplan6,tvplan7,tvplan8,tvplan9,tvplan10;
    private DatabaseHelper databaseHelper;
    private Spinner spinner;
    private int randomColor;

    public CustManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.cust_mangment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    String selectedMonth;
    String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cust_management, container, false);
        setHasOptionsMenu(true);
        tvChartTitle = v.findViewById(R.id.tvChartTitle);

        tvplan1 = v.findViewById(R.id.tvplan1);
        tvplan2 = v.findViewById(R.id.tvplan2);
        tvplan3 = v.findViewById(R.id.tvplan3);
        tvplan4 = v.findViewById(R.id.tvplan4);
        tvplan5 = v.findViewById(R.id.tvplan5);
        tvplan6 = v.findViewById(R.id.tvplan6);
        tvplan7 = v.findViewById(R.id.tvplan7);
        tvplan8 = v.findViewById(R.id.tvplan8);
        tvplan9 = v.findViewById(R.id.tvplan9);
        tvplan10 = v.findViewById(R.id.tvplan10);

        layoutChart = v.findViewById(R.id.chart);
        layoutText = v.findViewById(R.id.textLegend);
        databaseHelper = SmartAppication.getInstance().getmDatabaseHelper();
        spinner = v.findViewById(R.id.spinner_month);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = parent.getItemAtPosition(position).toString();
                if (type.equalsIgnoreCase("Customer Segment Wise Revenue")) {
                    layoutText.setVisibility(View.GONE);
                    showCustSegmentWiseRevenueChart(selectedMonth);
                } else if (type.equalsIgnoreCase("Business unit wise revenue")) {
                    layoutText.setVisibility(View.GONE);
                    showBusinessUnitwiseRevenue(selectedMonth);

                } else if (type.equalsIgnoreCase("Top 10 revenue plans")) {
                    layoutText.setVisibility(View.VISIBLE);
                    top10RevenuePlans(selectedMonth);
                } else if (type.equalsIgnoreCase("Least 10 revenue plans")) {
                    layoutText.setVisibility(View.VISIBLE);
                    least10RevenuePlans(selectedMonth);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvChartTitle.setText("Customer Segment Wise Revenue");
        showCustSegmentWiseRevenue();
        return v;
    }

    TopManagementData monthdata, chartdata;

    private void showCustSegmentWiseRevenue() {
        type = "Customer Segment Wise Revenue";
        monthdata = SmartAppication.getInstance().getmDatabaseHelper().showCustSegmentWiseRevenue();
        String month[] = monthdata.getBillMonth();

        ArrayAdapter adapterLoc = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, month);
        spinner.setAdapter(adapterLoc);
        selectedMonth = month[0];
        showCustSegmentWiseRevenueChart(selectedMonth);
    }

    private void showCustSegmentWiseRevenueChart(String selectedMonth) {

        chartdata = SmartAppication.getInstance().getmDatabaseHelper().showCustSegmentWiseRevenueData(selectedMonth);
        yaxisdata = chartdata.getAmount();
        xaxisdata = chartdata.getDescription();
        if (yaxisdata != null && xaxisdata != null) {
            xySeries = new XYSeries("Customer Segment Wise Revenue");
            for (int i = 0; i < yaxisdata.length; i++) {

                xySeries.add(i, yaxisdata[i]);
            }
            // Creating a dataset to hold each series
            dataset = new XYMultipleSeriesDataset();
            // Adding Income Series to the dataset
            dataset.addSeries(xySeries);

            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(getResources().getColor(R.color.diksha_blue));
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2);
            seriesRenderer.setDisplayChartValues(true);
            seriesRenderer.setChartValuesTextSize(22);
            multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setXAxisMin(-0.5);
            multiRenderer.setYAxisMin(0);
            // multiRenderer.setYLabelsAngle(45);
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 120, 80, 30});
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(1.5f);
            multiRenderer.setBarWidth(25);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            //multiRenderer.setXTitle("Dialed Numbers");
            multiRenderer.setYTitle("Charges");
            multiRenderer.setZoomButtonsVisible(false);
            //  multiRenderer.setPanEnabled(false);
            for (int i = 0; i < yaxisdata.length; i++) {

                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }

            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(5);
            multiRenderer.setZoomEnabled(false, false);
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.addSeriesRenderer(seriesRenderer);
            mChartView = ChartFactory.getBarChartView(getActivity(), dataset,
                    multiRenderer, BarChart.Type.DEFAULT);
            layoutChart.removeAllViews();
            layoutChart.addView(mChartView);
            mChartView.repaint();
        } else {
            tvChartTitle.setText("");
            layoutChart.removeAllViews();
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }


    }


    private void showBusinessUnitwiseRevenue() {
        type = "Business unit wise revenue";
        tvChartTitle.setText(type);
        monthdata = SmartAppication.getInstance().getmDatabaseHelper().showBusinessUnitwiseRevenue();
        String month[] = monthdata.getBillMonth();

        ArrayAdapter adapterLoc = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, month);
        spinner.setAdapter(adapterLoc);
        selectedMonth = month[0];
        showBusinessUnitwiseRevenue(selectedMonth);

    }

    private void showBusinessUnitwiseRevenue(String selectedMonth) {
        chartdata = SmartAppication.getInstance().getmDatabaseHelper().showBusinessUnitwiseRevenueData(selectedMonth);
        yaxisdata = chartdata.getAmount();
        xaxisdata = chartdata.getDescription();
        if (yaxisdata != null && xaxisdata != null) {
            xySeries = new XYSeries("Business Unit Wise Revenue");
            for (int i = 0; i < yaxisdata.length; i++) {
                xySeries.add(i, yaxisdata[i]);
            }
            // Creating a dataset to hold each series
            dataset = new XYMultipleSeriesDataset();
            // Adding Income Series to the dataset
            dataset.addSeries(xySeries);

            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(getResources().getColor(R.color.diksha_blue));
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2);
            seriesRenderer.setDisplayChartValues(true);
            seriesRenderer.setChartValuesTextSize(22);
            multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setXAxisMin(-0.5);
            multiRenderer.setYAxisMin(0);
            //   multiRenderer.setYLabelsAngle(45);
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(1.5f);
            multiRenderer.setBarWidth(25);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            //multiRenderer.setXTitle("Dialed Numbers");
            multiRenderer.setYTitle("Charges");
            multiRenderer.setZoomButtonsVisible(false);

            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 120, 80, 30});
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(5);
            multiRenderer.setZoomEnabled(false, false);
            multiRenderer.setXAxisMax(xaxisdata.length);
            for (int i = 0; i < yaxisdata.length; i++) {

                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }

            multiRenderer.addSeriesRenderer(seriesRenderer);
            mChartView = ChartFactory.getBarChartView(getActivity(), dataset,
                    multiRenderer, BarChart.Type.DEFAULT);
            layoutChart.removeAllViews();
            layoutChart.addView(mChartView);
            mChartView.repaint();
        } else {
            tvChartTitle.setText("");
            layoutChart.removeAllViews();
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }
    }

    private void top10RevenuePlans() {
        type = "Top 10 revenue plans";
        tvChartTitle.setText(type);
        monthdata = SmartAppication.getInstance().getmDatabaseHelper().top10RevenuePlans();
        String month[] = monthdata.getBillMonth();

        ArrayAdapter adapterLoc = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, month);
        spinner.setAdapter(adapterLoc);
        selectedMonth = month[0];
        top10RevenuePlans(selectedMonth);


    }

    private void top10RevenuePlans(String selectedMonth) {
        chartdata = SmartAppication.getInstance().getmDatabaseHelper().top10RevenuePlansData(selectedMonth);
        yaxisdata = chartdata.getAmount();
        xaxisdata = chartdata.getDescription();
        if (yaxisdata != null && xaxisdata != null) {
            xySeries = new XYSeries("Top 10 revenue plans");
            for (int i = 0; i < yaxisdata.length; i++) {
                xySeries.add(i, yaxisdata[i]);
            }
            // Creating a dataset to hold each series
            dataset = new XYMultipleSeriesDataset();
            // Adding Income Series to the dataset
            dataset.addSeries(xySeries);

            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(getResources().getColor(R.color.diksha_blue));
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2);
            seriesRenderer.setDisplayChartValues(true);
            seriesRenderer.setChartValuesTextSize(22);
            multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setYLabelsPadding(10);
            //multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 160, 80, 30});
            multiRenderer.setXAxisMin(-0.5);
            multiRenderer.setYAxisMin(0);
            // multiRenderer.setYLabelsAngle(45);
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setXLabels(0);
            // multiRenderer.setXLabelsAngle(45);
            multiRenderer.setBarSpacing(1.5f);

            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setXLabelsAlign(Paint.Align.LEFT);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            //multiRenderer.setXTitle("Dialed Numbers");
            multiRenderer.setYTitle("Charges");
            multiRenderer.setZoomButtonsVisible(false);
            // multiRenderer.setPanEnabled(false);
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setBarWidth(25);
            multiRenderer.setLegendTextSize(30);
            //  multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
            multiRenderer.setXLabelsAngle(45);
            multiRenderer.setPanEnabled(false, false);
            //multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            //  multiRenderer.setZoomInLimitX(3);
            multiRenderer.setZoomEnabled(false, false);
            multiRenderer.setXAxisMax(xaxisdata.length);
           // layoutText.removeAllViews();
            for (int i = 0; i < xaxisdata.length; i++) {
               /* if (xaxisdata[i].contains(" ")) {
                    xaxisdata[i] = xaxisdata[i].replace(" ", "\n");
                }
                multiRenderer.addXTextLabel(i, xaxisdata[i]);*/

                multiRenderer.addXTextLabel(i, "plan " + (i + 1));

                /*TextView tv = new TextView(getActivity());
                // tv.setLayoutParams(lparams);
                tv.setText("plan " + (i + 1) + " - " + xaxisdata[i]);
                tv.setTextSize(14);
                tv.setPadding(1, 1, 1, 1);
                tv.setTextColor(Color.BLACK);
                layoutText.addView(tv);*/
            }
            tvplan1.setText("plan 1 - "+xaxisdata[0]);
            tvplan2.setText("plan 2 - "+xaxisdata[1]);
            tvplan3.setText("plan 3 - "+xaxisdata[2]);
            tvplan4.setText("plan 4 - "+xaxisdata[3]);
            tvplan5.setText("plan 5 - "+xaxisdata[4]);
            tvplan6.setText("plan 6 - "+xaxisdata[5]);
            tvplan7.setText("plan 7 - "+xaxisdata[6]);
            tvplan8.setText("plan 8 - "+xaxisdata[7]);
            tvplan9.setText("plan 9 - "+xaxisdata[8]);
            tvplan10.setText("plan 10 - "+xaxisdata[9]);


            multiRenderer.setExternalZoomEnabled(false);

            multiRenderer.addSeriesRenderer(seriesRenderer);
            mChartView = ChartFactory.getBarChartView(getActivity(), dataset,
                    multiRenderer, BarChart.Type.DEFAULT);
            layoutChart.removeAllViews();
            layoutChart.addView(mChartView);
            mChartView.repaint();
        } else {
            tvChartTitle.setText("");
            layoutChart.removeAllViews();
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }

    }

    private void least10RevenuePlans() {
        type = "Least 10 revenue plans";
        tvChartTitle.setText(type);
        monthdata = SmartAppication.getInstance().getmDatabaseHelper().least10RevenuePlans();
        String month[] = monthdata.getBillMonth();

        ArrayAdapter adapterLoc = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, month);
        spinner.setAdapter(adapterLoc);
        selectedMonth = month[0];
        least10RevenuePlans(selectedMonth);
    }

    private void least10RevenuePlans(String selectedMonth) {
        /*if(selectedMonth.equals("4")){
            least10PieChart();
        }else {*/
        chartdata = SmartAppication.getInstance().getmDatabaseHelper().least10RevenuePlansData(selectedMonth);
        yaxisdata = chartdata.getAmount();
        xaxisdata = chartdata.getDescription();
        if (yaxisdata != null && xaxisdata != null) {

            xySeries = new XYSeries("Least 10 revenue plans");
            for (int i = 0; i < yaxisdata.length; i++) {
                xySeries.add(i, yaxisdata[i]);
            }
            // Creating a dataset to hold each series
            dataset = new XYMultipleSeriesDataset();
            // Adding Income Series to the dataset
            dataset.addSeries(xySeries);

            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(getResources().getColor(R.color.diksha_blue));
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2);
            seriesRenderer.setDisplayChartValues(true);
            seriesRenderer.setChartValuesTextSize(22);

            multiRenderer = new XYMultipleSeriesRenderer();
            // multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.VERTICAL);
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 125, 100, 30});
            multiRenderer.setXAxisMin(-0.5);
            multiRenderer.setYAxisMin(0);
            multiRenderer.setXAxisMax(xaxisdata.length);
            // multiRenderer.setYLabelsAngle(45);
            //  multiRenderer.setXLabelsAngle(45);
            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(1.5f);
            multiRenderer.setBarWidth(25);
            multiRenderer.setXLabelsAngle(45);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            multiRenderer.setXLabelsAlign(Paint.Align.LEFT);

            multiRenderer.setYTitle("Charges");
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
            multiRenderer.setZoomButtonsVisible(false);

            multiRenderer.setPanEnabled(false, false);
            // multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            //   multiRenderer.setZoomInLimitX(3);
            multiRenderer.setZoomEnabled(false, false);
            //layoutText.removeAllViews();
            for (int i = 0; i < xaxisdata.length; i++) {
                   /* if (xaxisdata[i].contains(" ")) {
                        xaxisdata[i] = xaxisdata[i].replace(" ", "\n");
                    }
                    multiRenderer.addXTextLabel(i, xaxisdata[i]);*/

                multiRenderer.addXTextLabel(i, "plan " + (i + 1));

               /* TextView tv = new TextView(getActivity());
                // tv.setLayoutParams(lparams);
                tv.setText("plan " + (i + 1) + " - " + xaxisdata[i]);
                tv.setTextSize(14);
                tv.setPadding(1, 1, 1, 1);
                tv.setTextColor(Color.BLACK);
                layoutText.addView(tv);*/
            }

            tvplan1.setText("plan 1 - "+xaxisdata[0]);
            tvplan2.setText("plan 2 - "+xaxisdata[1]);
            tvplan3.setText("plan 3 - "+xaxisdata[2]);
            tvplan4.setText("plan 4 - "+xaxisdata[3]);
            tvplan5.setText("plan 5 - "+xaxisdata[4]);
            tvplan6.setText("plan 6 - "+xaxisdata[5]);
            tvplan7.setText("plan 7 - "+xaxisdata[6]);
            tvplan8.setText("plan 8 - "+xaxisdata[7]);
            tvplan9.setText("plan 9 - "+xaxisdata[8]);
            tvplan10.setText("plan 10 - "+xaxisdata[9]);

            multiRenderer.addSeriesRenderer(seriesRenderer);
            mChartView = ChartFactory.getBarChartView(getActivity(), dataset,
                    multiRenderer, BarChart.Type.DEFAULT);
            layoutChart.removeAllViews();
            layoutChart.addView(mChartView);
            mChartView.repaint();
        } else {
            tvChartTitle.setText("");
            layoutChart.removeAllViews();
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }
        //  }
    }

    public void least10PieChart() {
        chartdata = SmartAppication.getInstance().getmDatabaseHelper().least10RevenuePlansData("4");
        yaxisdata = chartdata.getAmount();
        xaxisdata = chartdata.getDescription();
        CategorySeries distributionSeries = new CategorySeries("Least 10 revenue plans");
        for (int i = 0; i < xaxisdata.length; i++) {
            // Adding a slice with its values and name to the Pie Chart
            distributionSeries.add(xaxisdata[i], yaxisdata[i]);
        }
        int[] colors = new int[]{Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.YELLOW, Color.DKGRAY, Color.LTGRAY, Color.WHITE, Color.BLACK, Color.GRAY};
        // Instantiating a renderer for the Pie Chart
        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < xaxisdata.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(getRandomColor());

            // Adding a renderer for a slice
            defaultRenderer.setChartTitleTextSize(25);
            defaultRenderer.setExternalZoomEnabled(false);
            defaultRenderer.setDisplayValues(true);
            defaultRenderer.setPanEnabled(false);
            //    defaultRenderer.setZoomEnabled(false);
            defaultRenderer.setShowLegend(true);
            defaultRenderer.setLegendTextSize(25);
            defaultRenderer.setLabelsTextSize(20);
            defaultRenderer.setShowLabels(false);
            defaultRenderer.setZoomButtonsVisible(false);
            defaultRenderer.addSeriesRenderer(seriesRenderer);

        }


        defaultRenderer.setMargins(new int[]{10, 10, 10, 10});
        mChartView = ChartFactory.getPieChartView(getActivity(), distributionSeries, defaultRenderer);
        layoutChart.removeAllViews();
        layoutChart.addView(mChartView);
        mChartView.repaint();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_item_revenue:
                layoutText.setVisibility(View.GONE);
                showCustSegmentWiseRevenue();
                break;
            case R.id.nav_item_businessunit:
                layoutText.setVisibility(View.GONE);
                showBusinessUnitwiseRevenue();
                break;
            case R.id.nav_item_toprevenueplans:
                layoutText.setVisibility(View.VISIBLE);
                top10RevenuePlans();
                break;
            case R.id.nav_item_usagebytypedayleastrevenueplans:
                least10RevenuePlans();
                layoutText.setVisibility(View.VISIBLE);
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected String getTitle() {
        return "Customer Management";
    }

    @Override
    protected <T> void onResponse(T t, int id) throws Exception {

    }

    public static CustManagementFragment newInstance() {
        return new CustManagementFragment();
    }

    public int getRandomColor() {
        Random rnd = new Random();
        int randomColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return randomColor;
    }
}
