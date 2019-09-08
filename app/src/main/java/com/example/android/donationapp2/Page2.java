package com.example.android.donationapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Page2 extends AppCompatActivity implements View.OnClickListener {

    int donationAmount = 0;
    TextView donation_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideSystemUI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        ImageButton[] buttons = {findViewById(R.id.button_0),
                findViewById(R.id.button_1),
                findViewById(R.id.button_2),
                findViewById(R.id.button_3),
                findViewById(R.id.button_4),
                findViewById(R.id.button_5),
                findViewById(R.id.button_6),
                findViewById(R.id.button_7),
                findViewById(R.id.button_8),
                findViewById(R.id.button_9),
                findViewById(R.id.button_cancel),
                findViewById(R.id.button_delete),
                findViewById(R.id.button_continue)};
        donation_amount = findViewById(R.id.donation_amount);

        for(ImageButton b : buttons){
            b.setOnClickListener(this);
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_0:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10) : 45000;
                break;
            case R.id.button_1:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 1) : 45000;
                break;
            case R.id.button_2:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 2) : 45000;
                break;
            case R.id.button_3:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 3) : 45000;
                break;
            case R.id.button_4:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 4) : 45000;
                break;
            case R.id.button_5:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 5) : 45000;
                break;
            case R.id.button_6:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 6) : 45000;
                break;
            case R.id.button_7:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 7) : 45000;
                break;
            case R.id.button_8:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 8) : 45000;
                break;
            case R.id.button_9:
                donationAmount = (donationAmount<=4500) ? (donationAmount*10 + 9) : 45000;
                break;
            case R.id.button_delete:
                donationAmount /= 10;
                break;
            case R.id.button_cancel:
                Toast.makeText(this, "Donation Canceled", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.button_continue:
                if(donationAmount > 0) {
                    Intent page3 = new Intent(this, Page3.class);
                    page3.putExtra("donationAmount", donationAmount);
                    startActivityForResult(page3, 0);
                }else{
                    Toast.makeText(this, "Minimum donation amount is $1.",
                            Toast.LENGTH_SHORT).show();
                }
        }

        donation_amount.setText(Long.toString(donationAmount));
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode, Intent data){
        finish();
    }
}
