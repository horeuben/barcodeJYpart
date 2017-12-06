package com.example.jingyun.barcodescanningapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.androidlegacy.common.executor.AsyncTaskExecManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scanButton;
    private TextView formatTxt;
    private TextView contentTxt;
    private TextView producerTxt;
    private String GTINnumber;
    private String CompanyName="test company";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanButton=(Button)findViewById(R.id.scan_button);
        //testButton=(Button)findViewById(R.id.test_button);
        formatTxt=(TextView) findViewById(R.id.scan_format);
        contentTxt=(TextView) findViewById(R.id.scan_content);
        producerTxt=(TextView) findViewById(R.id.product_producer_company);

        scanButton.setOnClickListener(this);
    }
//testinh
    @Override
    public void onClick(View view) {
        //once the scan button is clicked, scan is initiaited and you ca begin scanning ur product
        if(view.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        // if there actually is a result from the scannig activity
        if (scanningResult != null){
            String scanContent=scanningResult.getContents();
            String scanFormat=scanningResult.getFormatName();
            GTINnumber=scanContent;
            //display it on screen
            //will display it on the initial screen
            formatTxt.setText("FORMAT: "+scanFormat);
            contentTxt.setText("CONTENT: "+scanContent);
            producerTxt.setText("COMPANY IS:"+CompanyName);
            //different kinds of barcodes: EAN13, EAN_8, UPC_12
            //scan content is the number on the barcode--> use this number to get information about copmany

            //get the company by searching on this website http://gepir.gs1.org/index.php/search-by-gtin
        }else{
            Toast toast=Toast.makeText(getApplicationContext(),"No scan data received :(", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
