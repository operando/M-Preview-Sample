package com.os.operando.m_preview_sample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            showLineNumber();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (MY_PERMISSIONS_REQUEST_READ_PHONE_STATE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showLineNumber();
                Toast.makeText(this, "Phone Permission OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Phone Permission NG", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera  Permission OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Camera  Permission NG", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showLineNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Toast.makeText(this, telephonyManager.getLine1Number(), Toast.LENGTH_SHORT).show();
    }
}