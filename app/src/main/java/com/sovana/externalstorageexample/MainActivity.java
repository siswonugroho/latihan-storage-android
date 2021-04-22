package com.sovana.externalstorageexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.textToSave);
    }

    public void next(View view) {
        Intent intent = new Intent(MainActivity.this, ViewTextActivity.class);
        startActivity(intent);
    }

    public void savePublic(View view) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        String info = editText.getText().toString();
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File myFile = new File(folder, "myData.txt");
        writeData(myFile, info);
        editText.setText("");
    }

    public void savePrivate(View view) {
        String info = editText.getText().toString();
        File folder = getExternalFilesDir("folderSovana");
        File myFile = new File(folder, "myData.txt");
        writeData(myFile, info);
        editText.setText("");
    }

    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "File berhasil disimpan ke " + myFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal menyimpan file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }
}