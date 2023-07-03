package com.riyan.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class ForexMainActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView aedTextView, eurTextView, idrTextView, jpyTextView, tndTextView, cnyTextView, gbpTextView, zwlTextView, kwdTextView, usdTextView;

    public ForexMainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout1);
        aedTextView = findViewById(R.id.aedTextView);
        eurTextView = findViewById(R.id.eurTextView);
        idrTextView = findViewById(R.id.idrTextView);
        jpyTextView = findViewById(R.id.jpyTextView);
        tndTextView = findViewById(R.id.tndTextView);
        cnyTextView = findViewById(R.id.cnyTextView);
        gbpTextView = findViewById(R.id.gpbTextView);
        zwlTextView = findViewById(R.id.zwlTextView);
        kwdTextView = findViewById(R.id.kwdTextView);
        usdTextView = findViewById(R.id.usdTextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        initSwipeRefreshLayout();
        initForex();
    }
    private void initSwipeRefreshLayout()
    {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            initForex();

            swipeRefreshLayout.setRefreshing(false);
        });
    }
    public String formatNumber(double number, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }
    private void initForex() {
        loadingProgressBar.setVisibility(TextView.VISIBLE);

        String url = "https://openexchangerates.org/api/latest.json?app_id=77de91edc39c45aab0c2f11a5da916ec";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                RootModelForex rootModel = gson.fromJson(new String(responseBody), RootModelForex.class);
                RatesModelForex ratesModel = rootModel.getRatesmodel();

                double aed = ratesModel.getIDR() / ratesModel.getAED();
                double eur = ratesModel.getIDR() / ratesModel.getEUR();
                double idr = ratesModel.getIDR();
                double jpy = ratesModel.getIDR() / ratesModel.getJPY();
                double tnd = ratesModel.getIDR() / ratesModel.getTND();
                double cny = ratesModel.getIDR() / ratesModel.getCNY();
                double gbp = ratesModel.getIDR() / ratesModel.getGBP();
                double zwl = ratesModel.getIDR() / ratesModel.getZWL();
                double kwd = ratesModel.getIDR() / ratesModel.getKWD();
                double usd = ratesModel.getIDR() / ratesModel.getUSD();

                aedTextView.setText(formatNumber(aed, "###,##0.##"));
                eurTextView.setText(formatNumber(eur, "###,##0.##"));
                idrTextView.setText(formatNumber(idr, "###,##0.##"));
                jpyTextView.setText(formatNumber(jpy, "###,##0.##"));
                tndTextView.setText(formatNumber(tnd, "###,##0.##"));
                cnyTextView.setText(formatNumber(cny, "###,##0.##"));
                gbpTextView.setText(formatNumber(gbp, "###,##0.##"));
                zwlTextView.setText(formatNumber(zwl, "###,##0.##"));
                kwdTextView.setText(formatNumber(zwl, "###,##0.##"));
                usdTextView.setText(formatNumber(usd, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });
    }
}