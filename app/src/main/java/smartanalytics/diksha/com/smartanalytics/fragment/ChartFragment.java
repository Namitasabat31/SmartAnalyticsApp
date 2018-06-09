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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

import smartanalytics.diksha.com.smartanalytics.MainActivity;
import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.commonutils.CommonUtility;
import smartanalytics.diksha.com.smartanalytics.commonutils.LogUtils;
import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.BillHistoryData;
import smartanalytics.diksha.com.smartanalytics.data.ChartData;
import smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper;
import smartanalytics.diksha.com.smartanalytics.network.OttoEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends BaseFragment {
    public String TAG = ChartFragment.class.getSimpleName();

    public ChartFragment() {
        // Required empty public constructor
    }


    public static ChartFragment newInstance() {
        return new ChartFragment();
    }

    private DatabaseHelper databaseHelper;
    private LinearLayout layoutChart, layoutText;
    private GraphicalView mChartView;
    private XYMultipleSeriesRenderer multiRenderer;
    private XYSeriesRenderer seriesRenderer;
    private XYMultipleSeriesDataset dataset;
    private XYSeries xySeries;
    private int[] yaxisdata;
    private String[] xaxisdata;
    private TextView tvChartTitle, tvplan1, tvplan2, tvplan3, tvplan4, tvplan5, tvplan6, tvplan7, tvplan8, tvplan9, tvplan10;
    ;
    private String selectedAccNo;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.chart_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        tvChartTitle = v.findViewById(R.id.tvChartTitle);
        layoutChart = v.findViewById(R.id.chart);
        layoutText = v.findViewById(R.id.textLegend);
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
        databaseHelper = SmartAppication.getInstance().getmDatabaseHelper();
        selectedAccNo = SmartAppication.getInstance().getSelectedAccNo();
        tvChartTitle.setText(getResources().getString(R.string.usagecategory));
        layoutText.setVisibility(View.VISIBLE);
        showUsageByCategoryChart();

        return v;
    }

    @Override
    protected String getTitle() {
        return getResources().getString(R.string.charts);
    }

    private void durationOfcallbytype() {
        layoutText.setVisibility(View.GONE);
        BillHistoryData data = databaseHelper.durationOfcallbytypeData(selectedAccNo);
        yaxisdata = data.getBillAmount();
        xaxisdata = data.getBillMonth();
        if (yaxisdata != null && xaxisdata != null) {
            xySeries = new XYSeries("Call type");
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
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(1.5f);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            //multiRenderer.setXTitle("Dialed Numbers");
            multiRenderer.setYTitle("Time(in sec)");
            multiRenderer.setZoomButtonsVisible(false);
            multiRenderer.setPanEnabled(false);
            for (int i = 0; i < yaxisdata.length; i++) {
                if (xaxisdata[i].contains(" ")) {
                    xaxisdata[i] = xaxisdata[i].replace(" ", "\n");
                }
                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 140, 120, 30});
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(6);
            multiRenderer.setZoomEnabled(false, false);
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
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

    private void showUsageByCategoryChart() {
       // layoutText.setVisibility(View.GONE);
        BillHistoryData data = databaseHelper.showUsageByCategoryChartData(selectedAccNo);
        yaxisdata = data.getBillAmount();
        xaxisdata = data.getBillMonth();
        if (yaxisdata != null && xaxisdata != null) {
            xySeries = new XYSeries("Amount");
            for (int i = 0; i < xaxisdata.length; i++) {
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
            // multiRenderer.setYLabels(10);
            multiRenderer.setYAxisMin(0);
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(0.5f);
            multiRenderer.setXLabelsAngle(30);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            multiRenderer.setZoomButtonsVisible(false);
            multiRenderer.setPanEnabled(false);
            // multiRenderer.setXTitle("Dialed Numbers");
            multiRenderer.setYTitle("Amount(Rs.)");

            for (int i = 0; i < xaxisdata.length; i++) {
                /*if(xaxisdata[i].contains(" ")){
                    xaxisdata[i] = xaxisdata[i].replace(" ","\n");
                }
                multiRenderer.addXTextLabel(i, xaxisdata[i]);*/
                multiRenderer.addXTextLabel(i, "calls " + (i + 1));
            }
            tvplan1.setText("calls 1 - " + xaxisdata[0]);
            tvplan2.setText("calls 2 - " + xaxisdata[1]);
            tvplan3.setText("calls 3 - " + xaxisdata[2]);
            tvplan4.setText("calls 4 - " + xaxisdata[3]);
            tvplan5.setText("calls 5 - " + xaxisdata[4]);
            tvplan6.setText("calls 6 - " + xaxisdata[5]);
            tvplan7.setText("calls 7 - " + xaxisdata[6]);
            tvplan8.setText("calls 8 - " + xaxisdata[7]);
            tvplan9.setVisibility(View.GONE);
            tvplan10.setVisibility(View.GONE);

            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 80, 70, 30});
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(6);
            multiRenderer.setZoomEnabled(false, false);
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


    private void show10MostExpensiveChargesChart() {
layoutText.setVisibility(View.GONE);
        BillHistoryData data = databaseHelper.show10MostExpensiveChargesChartData(selectedAccNo);
        yaxisdata = data.getBillAmount();
        xaxisdata = data.getBillMonth();
        if (yaxisdata != null && xaxisdata != null) {
            // Creating an  XYSeries for Income
            xySeries = new XYSeries("Dialed Numbers");
            // Creating an  XYSeries for Expense
            // Adding data to Income and Expense Series
            for (int i = 0; i < yaxisdata.length; i++) {
                xySeries.add(i, yaxisdata[i]);
            }
            // Creating a dataset to hold each series
            dataset = new XYMultipleSeriesDataset();
            // Adding Income Series to the dataset
            dataset.addSeries(xySeries);
            // Creating XYSeriesRenderer to customize incomeSeries
            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(getResources().getColor(R.color.diksha_blue));
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2);
            seriesRenderer.setDisplayChartValues(true);
            seriesRenderer.setChartValuesTextSize(22);
            multiRenderer = new XYMultipleSeriesRenderer();
            multiRenderer.setXLabels(0);
            multiRenderer.setXAxisMin(-0.5);
            multiRenderer.setYAxisMin(0);
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setBarSpacing(1.5f);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            multiRenderer.setXLabelsAlign(Paint.Align.LEFT);
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 80, 130, 30});
            multiRenderer.setYTitle("Amount(Rs.)");
            multiRenderer.setXLabelsAngle(30);
            multiRenderer.setZoomButtonsVisible(false);
            multiRenderer.setPanEnabled(false);
            for (int i = 0; i < yaxisdata.length; i++) {
                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(6);
            multiRenderer.setZoomEnabled(false, false);

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


    //line chart
    private void averageUsagebyHours() {
        ChartData data = databaseHelper.averageUsagebyHoursData(selectedAccNo);

        ArrayList<Double> voicelist = data.getVoiceunit();
        ArrayList<Double> smslist = data.getSmsunit();
        if (voicelist != null && smslist != null) {
            double[] seriesFirstY = new double[voicelist.size()];
            double[] seriesSecondY = new double[smslist.size()];

            for (int i = 0; i < voicelist.size(); i++) {
                seriesFirstY[i] = voicelist.get(i);
            }
            for (int k = 0; k < smslist.size(); k++) {
                seriesSecondY[k] = smslist.get(k);
            }

            xaxisdata = data.getDate();

            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

            XYSeries firstSeries = new XYSeries("Voice");
            for (int i = 0; i < seriesFirstY.length; i++)
                firstSeries.add(i, seriesFirstY[i]);
            dataset.addSeries(firstSeries);

            XYSeries secondSeries = new XYSeries("SMS");
            for (int j = 0; j < seriesSecondY.length; j++)
                secondSeries.add(j, seriesSecondY[j]);
            dataset.addSeries(secondSeries);

            multiRenderer = new XYMultipleSeriesRenderer();

            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(Color.BLUE);
            seriesRenderer.setPointStyle(PointStyle.SQUARE);
            seriesRenderer.setFillBelowLine(true);
            seriesRenderer.setFillBelowLineColor(Color.WHITE);
            seriesRenderer.setFillPoints(true);
            multiRenderer.addSeriesRenderer(seriesRenderer);
            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setPointStyle(PointStyle.CIRCLE);
            seriesRenderer.setColor(Color.BLACK);
            seriesRenderer.setFillPoints(true);
            multiRenderer.addSeriesRenderer(seriesRenderer);

            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(0.5f);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            //  multiRenderer.setXLabelsAngle(45);
            multiRenderer.setYTitle("Amount");
            multiRenderer.setZoomButtonsVisible(false);
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(10);
            multiRenderer.setZoomEnabled(false, false);
           // multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 80, 60, 30});
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);

            for (int i = 0; i < xaxisdata.length; i++) {
                if (xaxisdata[i].contains(" ")) {
                    xaxisdata[i] = xaxisdata[i].replace(" ", "\n");
                }
                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }

            mChartView = ChartFactory.getLineChartView(getActivity(), dataset,
                    multiRenderer);
            layoutChart.removeAllViews();
            layoutChart.addView(mChartView);
            mChartView.repaint();
        } else {
            tvChartTitle.setText("");
            layoutChart.removeAllViews();
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }
    }

    // line chart
    private void showUsageBytypeandDay() {
        layoutText.setVisibility(View.GONE);
        ChartData data = databaseHelper.showUsageBytypeandDayData(selectedAccNo);

        ArrayList<Double> datalist = data.getDataunit();
        ArrayList<Double> voicelist = data.getVoiceunit();
        ArrayList<Double> smslist = data.getSmsunit();
        if (datalist != null && voicelist != null && smslist != null) {
            double[] seriesFirstY = new double[voicelist.size()];
            double[] seriesSecondY = new double[datalist.size()];
            double[] seriesthirdY = new double[smslist.size()];

            for (int i = 0; i < voicelist.size(); i++) {
                seriesFirstY[i] = voicelist.get(i);
            }

            for (int j = 0; j < datalist.size(); j++) {
                seriesSecondY[j] = datalist.get(j);
            }

            for (int k = 0; k < smslist.size(); k++) {
                seriesthirdY[k] = smslist.get(k);
            }


            xaxisdata = data.getDate();
            //yaxisdata = new int[]{0,10,20,30,40,50,60,70,80};
            dataset = new XYMultipleSeriesDataset();

            XYSeries firstSeries = new XYSeries("Voice");
            for (int i = 0; i < seriesFirstY.length; i++)
                firstSeries.add(i, seriesFirstY[i]);
            dataset.addSeries(firstSeries);

            XYSeries secondSeries = new XYSeries("ChartData");
            for (int j = 0; j < seriesSecondY.length; j++)
                secondSeries.add(j, seriesSecondY[j]);
            dataset.addSeries(secondSeries);

            XYSeries thirdSeries = new XYSeries("SMS");
            for (int j = 0; j < seriesthirdY.length; j++)
                thirdSeries.add(j, seriesthirdY[j]);
            dataset.addSeries(thirdSeries);

            multiRenderer = new XYMultipleSeriesRenderer();
            // multiRenderer.setMargins(new int[] { 20, 30, 15, 0 });
            // first
            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setColor(Color.BLUE);
            seriesRenderer.setPointStyle(PointStyle.SQUARE);
            seriesRenderer.setFillBelowLine(true);
            seriesRenderer.setFillBelowLineColor(Color.WHITE);
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2f);

            multiRenderer.addSeriesRenderer(seriesRenderer);
            // 2nd
            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setPointStyle(PointStyle.CIRCLE);
            seriesRenderer.setColor(Color.GREEN);
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2f);
            multiRenderer.addSeriesRenderer(seriesRenderer);

            // 3rd
            seriesRenderer = new XYSeriesRenderer();
            seriesRenderer.setPointStyle(PointStyle.CIRCLE);
            seriesRenderer.setColor(Color.BLACK);
            seriesRenderer.setFillPoints(true);
            seriesRenderer.setLineWidth(2f);
            multiRenderer.addSeriesRenderer(seriesRenderer);


            multiRenderer.setXLabels(0);

            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);

            multiRenderer.setYTitle("Amount");
            multiRenderer.setZoomButtonsVisible(false);
            multiRenderer.setXLabelsAngle(30);
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(10);
            multiRenderer.setZoomEnabled(false, false);
            //multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 80, 80, 10});
            multiRenderer.setLabelsTextSize(26);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);

            for (int i = 0; i < xaxisdata.length; i++) {
                if (xaxisdata[i].contains(" ")) {
                    xaxisdata[i] = xaxisdata[i].replace(" ", "\n");
                }
                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }

            mChartView = ChartFactory.getLineChartView(getActivity(), dataset,
                    multiRenderer);
            layoutChart.removeAllViews();
            layoutChart.addView(mChartView);
            mChartView.repaint();

        } else {
            tvChartTitle.setText("");
            layoutChart.removeAllViews();
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }
    }

    private void show10NumberCallsChart() {
        layoutText.setVisibility(View.GONE);
        BillHistoryData data = databaseHelper.show10NumberCallsChartData(selectedAccNo);

        yaxisdata = data.getBillAmount();
        xaxisdata = data.getBillMonth();
        if (yaxisdata != null && xaxisdata != null) {
            xySeries = new XYSeries("Dialed Numbers");
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
            multiRenderer.setXAxisMax(xaxisdata.length);
            multiRenderer.setXLabels(0);
            multiRenderer.setBarSpacing(1.5f);
            multiRenderer.setMarginsColor(getResources().getColor(R.color.diksha_white));
            multiRenderer.setYLabelsColor(0, Color.BLACK);
            multiRenderer.setXLabelsColor(Color.BLACK);
            multiRenderer.setLabelsColor(Color.BLACK);
            multiRenderer.setAxesColor(Color.BLACK);
            multiRenderer.setYAxisAlign(Paint.Align.LEFT, 0);
            multiRenderer.setYLabelsAlign(Paint.Align.RIGHT, 0);
            multiRenderer.setXLabelsAlign(Paint.Align.LEFT);
            multiRenderer.setShowGrid(true);
            multiRenderer.setXLabelsAngle(30);
            multiRenderer.setYTitle("Time(in sec)");
            multiRenderer.setZoomButtonsVisible(false);
            multiRenderer.setPanEnabled(false);
            for (int i = 0; i < yaxisdata.length; i++) {
                multiRenderer.addXTextLabel(i, xaxisdata[i]);
            }
            multiRenderer.setYLabelsPadding(10);
            multiRenderer.setXLabelsPadding(10);
            multiRenderer.setMargins(new int[]{30, 80, 140, 100});
            multiRenderer.setPanEnabled(false, false);
            multiRenderer.setPanLimits(new double[]{0, xaxisdata.length, 0, 0});
            multiRenderer.setZoomInLimitX(6);
            multiRenderer.setZoomEnabled(false, false);
            multiRenderer.setLabelsTextSize(30);
            multiRenderer.setAxisTitleTextSize(30);
            multiRenderer.setLegendTextSize(30);
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

    @Override
    protected <T> void onResponse(T t, int id) throws Exception {
        LogUtils.logD(TAG, t.toString());

    }

    @Subscribe
    public void onOttoEventReceived(OttoEvent event) {
        LogUtils.logD(TAG, "DashboardAccountsFragment");

        try {
            super.onOttoEventReceived(event);

        } catch (Exception e) {
            LogUtils.logD(TAG, "Exception --> " + e.getMessage());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_item_usagecategory) {
            tvChartTitle.setText(getResources().getString(R.string.usagecategory));
            showUsageByCategoryChart();
        } else if (itemId == R.id.nav_item_usagebytypeday) {
            tvChartTitle.setText(getResources().getString(R.string.usagebytypeday));
            showUsageBytypeandDay();
        } else if (itemId == R.id.nav_item_costbytypeandday) {
            layoutChart.removeAllViews();
            tvChartTitle.setText("");
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        } else if (itemId == R.id.nav_item_durationcallbytype) {
            tvChartTitle.setText(getResources().getString(R.string.durationcallbytype));
            durationOfcallbytype();
        } else if (itemId == R.id.nav_item_topnumbercalls) {
            tvChartTitle.setText(getResources().getString(R.string.topnumbercalls));
            show10NumberCallsChart();
        } else if (itemId == R.id.nav_item_expcharges) {
            tvChartTitle.setText(getResources().getString(R.string.expcharges));
            show10MostExpensiveChargesChart();
        } else if (itemId == R.id.nav_item_averageusagehours) {
            tvChartTitle.setText(getResources().getString(R.string.averageusagehours));
            averageUsagebyHours();
        } else if (itemId == R.id.nav_item_lastusagebytype) {
            layoutChart.removeAllViews();
            tvChartTitle.setText("");
            CommonUtility.displayToast(getActivity(), getResources().getString(R.string.no_data_found));
        }

        return super.onOptionsItemSelected(item);
    }

}
