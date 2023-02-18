package com.android.attendance.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.Manifest;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.androidattendancesystem.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class empty extends Activity {
	Button btn;
	Date c = Calendar.getInstance().getTime();
	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
	String abba = df.format(c);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.export_data);

		btn = (Button)findViewById(R.id.exp_std);
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";

		String csv = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+abba+"-CsvFile.csv"); // Here csv file name is MyCsvFile.csv

		//by Hiting button csv will create inside phone storage.
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(empty.this,"Attendance.db",(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))+"/");
                sqLiteToExcel.exportAllTables("Student-"+abba+".xls", new SQLiteToExcel.ExportListener() {
					@Override
					public void onStart() {

					}

					@Override
					public void onCompleted(String filePath) {

					}

					@Override
					public void onError(Exception e) {

					}
				});


				CSVWriter writer = null;
				try {
					writer = new CSVWriter(new FileWriter(csv));

					List<String[]> data = new ArrayList<String[]>();
					data.add(new String[]{"Student Name", "Contact No.", "Address", "Status"});
					data.add(new String[]{"Deep Chakraborty", "6291907144" , "xyz" , ""});
					data.add(new String[]{"Shubham Gupta", "9674644518" , "aaa" , ""});
					data.add(new String[]{"Ankit Purakayastha", "9231559955" , "fff" , ""});

					writer.writeAll(data); // data is adding to csv

					writer.close();

					Toast.makeText(empty.this, "Work" + csv, Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
