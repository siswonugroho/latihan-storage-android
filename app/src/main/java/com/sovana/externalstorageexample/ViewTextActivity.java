package com.sovana.externalstorageexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ViewTextActivity extends AppCompatActivity {

    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_text);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        showText = findViewById(R.id.getText);
    }

    public void getPublic(View view) {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File myFile = new File(folder, "myData.txt");
        setTextContent(myFile);
    }

    public void getPrivate(View view) {
        File folder = getExternalFilesDir("folderSovana");
        File myFile = new File(folder, "myData.txt");
        setTextContent(myFile);
    }

//    Function ini adalah isi dari potongan kode getPrivate dan getPublic yang sama persis untuk menghindari penulisan kode yang berulang
    private void setTextContent (File file) {
        String text = getdata(file);
        if (text != null) {
            showText.setText(text);
        } else {
            showText.setText("(Tidak ada teks)");
        }
    }

//    Function untuk mengambil isi teks dari suatu file
    private String getdata(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuilder buffer = new StringBuilder();
            while((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}