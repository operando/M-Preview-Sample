package com.os.operando.m_preview_sample;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class PermissionRequestFragment extends Fragment {

    private static int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            showLineNumber();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        new Exception().printStackTrace();
        if (MY_PERMISSIONS_REQUEST_READ_PHONE_STATE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showLineNumber();
                Toast.makeText(getContext(), "Phone Permission OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Phone Permission NG", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showLineNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        Toast.makeText(getContext(), telephonyManager.getLine1Number(), Toast.LENGTH_SHORT).show();
    }
}
