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

import com.squareup.sdk.reader.ReaderSdk;
import com.squareup.sdk.reader.checkout.CheckoutErrorCode;
import com.squareup.sdk.reader.checkout.CheckoutManager;
import com.squareup.sdk.reader.checkout.CheckoutParameters;
import com.squareup.sdk.reader.checkout.CheckoutResult;
import com.squareup.sdk.reader.checkout.CurrencyCode;
import com.squareup.sdk.reader.checkout.Money;
import com.squareup.sdk.reader.core.CallbackReference;
import com.squareup.sdk.reader.core.Result;
import com.squareup.sdk.reader.core.ResultError;

import java.text.NumberFormat;

public class Page4 extends AppCompatActivity {

    private CallbackReference checkoutCallbackRef;
    int THRESHOLD_FOR_RECEIPT_IN_CENTS = 25000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideSystemUI();
        super.onCreate(savedInstanceState);

        int donationAmountInCents = getIntent().getExtras().getInt("donationAmountInCents");
        double donationAmount = donationAmountInCents/100.0;

        if (!ReaderSdk.authorizationManager().getAuthorizationState().isAuthorized()) {
            goToAuthorizeActivity();
        }

        setContentView(R.layout.activity_page4);

        TextView total_donation_amount = findViewById(R.id.total_donation_amount);
        total_donation_amount.setText(NumberFormat.getCurrencyInstance().format(donationAmount));

        ImageButton button_cancel_page4 = findViewById(R.id.button_cancel_page4);
        ImageButton button_pay_with_card = findViewById(R.id.button_pay_with_card);

        button_cancel_page4.setOnClickListener(v -> {
            Toast.makeText(Page4.this, "Donation Canceled", Toast.LENGTH_SHORT)
                    .show();
            finish();
        });
        button_pay_with_card.setOnClickListener(v -> startCheckout(donationAmountInCents));

        CheckoutManager checkoutManager = ReaderSdk.checkoutManager();
        checkoutCallbackRef =
                checkoutManager.addCheckoutActivityCallback(this::onCheckoutResult);

    }

    private void onCheckoutResult(Result<CheckoutResult, ResultError<CheckoutErrorCode>> result) {
        if (result.isSuccess()) {
            Toast.makeText(this, "Thank You For Your Donation", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ResultError<CheckoutErrorCode> error = result.getError();
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void goToAuthorizeActivity() {
        Intent intent = new Intent(this, AuthorizeActivity.class);
        startActivity(intent);
        finish();
    }

    private void startCheckout(int donationAmountInCents){
        CheckoutManager checkoutManager = ReaderSdk.checkoutManager();
        Money amountMoney = new Money(donationAmountInCents, CurrencyCode.current());
        CheckoutParameters.Builder parametersBuilder =
                CheckoutParameters.newBuilder(amountMoney);
        if (donationAmountInCents < THRESHOLD_FOR_RECEIPT_IN_CENTS){
            parametersBuilder.skipReceipt(true);
        }
        checkoutManager.startCheckoutActivity(this, parametersBuilder.build());
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

}
