package com.mocoitlabs.ekrishi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    // variable to hold the password toggle button
    private ImageButton eyeButton = null;
    //variable to hold the password input text box
    private EditText passwordInput = null;
    //variable to check the status of toggle false: eye open, cant see password , true: eye close, can see password
    private boolean passwordVisibility = false;
    //variable for mobile input
    private EditText mobileInput = null;



    private Button registerButton = null;

    private String TAG = LoginActivity.class.getSimpleName();

    private List<Boolean> isValidForm = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialising the image button , toggle button
        eyeButton = (ImageButton) findViewById(R.id.eye_button);
        //initialising the edit text , password text box
        passwordInput = (EditText) findViewById(R.id.password_input);
        //initialising mobile inpute text box
        mobileInput = (EditText) findViewById(R.id.phone_input);
        //initialising register button
        registerButton = (Button) findViewById(R.id.register_button);
    }

    private  boolean checkMobileNumber (String number) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phonenumber = null;
        try {
            phonenumber = phoneNumberUtil.parse(number, "IN");
        } catch (NumberParseException e) {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return phoneNumberUtil.isValidNumber(phonenumber);
    }

    public void onSubmitButtonOnClick (View view) {

        String mobileNumber = null;
        String password = null;
        if(!TextUtils.isEmpty(mobileInput.getText().toString())) {
            mobileNumber = mobileInput.getText().toString().trim();
            isValidForm.add(true);
            if(checkMobileNumber(mobileNumber)) {
                isValidForm.add(true);
                Toast.makeText(this, getString(R.string.mobile_number_is_valid), Toast.LENGTH_SHORT).show();
            } else {
                isValidForm.add(false);
                Toast.makeText(this, getString(R.string.mobile_number_is_not_valid), Toast.LENGTH_SHORT).show();
            }
        } else {
            isValidForm.add(false);
            Toast.makeText(this, getString(R.string.mobile_number_is_empty), Toast.LENGTH_SHORT).show();
        }

       if(!TextUtils.isEmpty(passwordInput.getText().toString())) {
           isValidForm.add(true);
            password = passwordInput.getText().toString().trim();
            if(password.length() < 6) {
                isValidForm.add(false);
                Toast.makeText(this, getString(R.string.password_is_short), Toast.LENGTH_SHORT).show();
            }

        } else {
           isValidForm.add(false);
            Toast.makeText(this, getString(R.string.password_is_empty), Toast.LENGTH_SHORT).show();
        }
        // if isValidForm contains all true the do your server push code otherwise show error
        // server
    }



    public void onRegisterButtonOnClick (View view) {
        Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }



    // function for doing the toggle password
    public void onEyeButtonPressed(View view) {
        if(eyeButton != null) { // checks whether the image button is initialized  to avoid error
            if(passwordInput != null) { // checks whether the edit text is initialized
                if(passwordVisibility) { // checking the status of the toggle state
                    passwordInput.setTransformationMethod(new PasswordTransformationMethod()); // this will transform the chars in password text field to ******
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // to change the image of the image button , first need to check the os version ,, this will check the os is greater that lollipop
                        eyeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_open, getApplicationContext().getTheme())); // changing the image of image button in os > lollipop
                    } else {
                        eyeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_open));// changing the image of image button in os < lollipop
                    }
                    passwordVisibility = false; // toggling the status to false
                } else {
                    passwordInput.setTransformationMethod(null);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        eyeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_close, getApplicationContext().getTheme()));
                    } else {
                        eyeButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_close));
                    }
                    passwordVisibility = true;
                }
            }
        }
    }
}