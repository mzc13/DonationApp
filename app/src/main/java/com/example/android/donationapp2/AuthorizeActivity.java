package com.example.android.donationapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.sdk.reader.ReaderSdk;
import com.squareup.sdk.reader.authorization.AuthorizationManager;
import com.squareup.sdk.reader.authorization.AuthorizationState;
import com.squareup.sdk.reader.authorization.AuthorizeErrorCode;
import com.squareup.sdk.reader.authorization.Location;
import com.squareup.sdk.reader.core.CallbackReference;
import com.squareup.sdk.reader.core.Result;
import com.squareup.sdk.reader.core.ResultError;

public class AuthorizeActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 0;
    private CallbackReference authorizeCallbackRef;
    EditText authorizationCodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);

        AuthorizationManager authorizationManager = ReaderSdk.authorizationManager();
        AuthorizationState state = authorizationManager.getAuthorizationState();
        if(state.isAuthorized()){
            startMainActivity();
        }

        authorizeCallbackRef = authorizationManager.addAuthorizeCallback(this::onAuthorizeResult);
        Button authorizationButton = findViewById(R.id.authorization_button);
        Button getCodeButton = findViewById(R.id.get_code_button);
        authorizationCodeInput = findViewById(R.id.authorization_code_input);
        
        authorizationButton.setOnClickListener(v -> {
            String authorizationCode = authorizationCodeInput.getText().toString();
            authorizationManager.authorize(authorizationCode);
        });

        getCodeButton.setOnClickListener(v -> {
            Intent browserAuthentication = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://algorithmtest.com/square/request_token.php"));
            startActivity(browserAuthentication);
        });
    }

    private void onAuthorizeResult(Result<Location, ResultError<AuthorizeErrorCode>> result) {
        if (result.isSuccess()) {
            startMainActivity();
        } else {
            ResultError<AuthorizeErrorCode> error = result.getError();
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        authorizeCallbackRef.clear();
    }
}
