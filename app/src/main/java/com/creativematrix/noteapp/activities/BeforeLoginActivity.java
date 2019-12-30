package com.creativematrix.noteapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.SYSTEM_ALERT_WINDOW;
import com.creativematrix.noteapp.R;
import com.creativematrix.noteapp.util.UtilPermissions;

import java.util.HashMap;
import java.util.Map;

public class BeforeLoginActivity extends AppCompatActivity {
Button btn_company_login,btn_user_login;
    private static final int MY_PERMISSIONS_REQUEST_SYSTEM_ALERT_WINDOW = 100;
    private Handler h;
    private Runnable r;
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);
        configureViews();
        btn_company_login.setOnClickListener(view -> openCompanyLoginRegisterActivity());
        btn_user_login.setOnClickListener(view -> openUserLoginRegisterActivity());

        String[] PERMISSIONS = {
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE,
                RECORD_AUDIO,
                ACCESS_NETWORK_STATE,
                SYSTEM_ALERT_WINDOW
        };

        if (!UtilPermissions.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this , PERMISSIONS, MY_PERMISSIONS_REQUEST_SYSTEM_ALERT_WINDOW);
        } else{
            h=new Handler();
            h.postDelayed(r, 1500);
        }
        checkDrawOverlayPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int index = 0;
        Map<String, Integer> PermissionsMap = new HashMap<String, Integer>();
        for (String permission : permissions) {
            PermissionsMap.put(permission, grantResults[index]);
            index++;
        }

        if (PermissionsMap.get(WRITE_EXTERNAL_STORAGE) != 0
                || PermissionsMap.get(READ_EXTERNAL_STORAGE) != 0
                || PermissionsMap.get(RECORD_AUDIO) != 0
                || PermissionsMap.get(ACCESS_NETWORK_STATE) != 0
                || PermissionsMap.get(SYSTEM_ALERT_WINDOW) != 0
               ) {
            // Toast.makeText(this, "You cannot run dawadoz without getting permissions", Toast.LENGTH_SHORT).show();
          //  finish();
        } else {
            h.postDelayed(r, 1500);
        }}
    private void openCompanyLoginRegisterActivity() {
        startActivity(new Intent(BeforeLoginActivity.this,CompanyLoginRegisterActivity.class));
    }
    private void openUserLoginRegisterActivity() {
        startActivity(new Intent(BeforeLoginActivity.this,UserLoginRegisterActivity.class));
    }
    private void configureViews() {
        btn_company_login = findViewById(R.id.btn_company_login);
        btn_user_login = findViewById(R.id.btn_user_login);
    }
    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + this.getPackageName()));
                startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
               //     initializeView();
                }

            }
        }
    }
}
