package com.example.android.donationapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class Page3 extends AppCompatActivity {
    final double CUSTOM_TRANSACTION_PERCENTAGE = 0.0257;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideSystemUI();
        super.onCreate(savedInstanceState);

        int donationAmount = getIntent().getExtras().getInt("donationAmount");
        final int donationAmountInCents = donationAmount * 100;
        final int transactionFeeInCents = ((int) Math.ceil(donationAmount *
                CUSTOM_TRANSACTION_PERCENTAGE * 100)) + 10;
        double transactionFee = transactionFeeInCents/100.0;

        setContentView(R.layout.activity_page3);

        TextView transaction_prompt = findViewById(R.id.transaction_prompt);
        transaction_prompt.setText("Would you like to add " +
                NumberFormat.getCurrencyInstance().format(transactionFee) +
                " to cover the transaction fee?");

        ImageButton button_cancel = findViewById(R.id.button_cancel_page3);
        ImageButton button_yes = findViewById(R.id.button_yes);
        ImageButton button_no = findViewById(R.id.button_no);

        button_cancel.setOnClickListener(v -> {
            Toast.makeText(Page3.this, "Donation Canceled", Toast.LENGTH_SHORT)
                    .show();
            finish();
        });
        button_yes.setOnClickListener(v -> {
            int adjustedDonationAmountInCents = donationAmountInCents + transactionFeeInCents;
            Intent page4 = new Intent(Page3.this, Page4.class);
            page4.putExtra("donationAmountInCents", adjustedDonationAmountInCents);
            startActivityForResult(page4, 0);
        });
        button_no.setOnClickListener(v -> {
            Intent page4 = new Intent(Page3.this, Page4.class);
            page4.putExtra("donationAmountInCents", donationAmountInCents);
            startActivityForResult(page4, 0);
        });

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

    protected void onActivityResult(int requestCode , int resultCode, Intent data){
        finish();
    }
}
