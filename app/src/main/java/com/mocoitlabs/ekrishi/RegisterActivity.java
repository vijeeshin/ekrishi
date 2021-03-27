package com.mocoitlabs.ekrishi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends AppCompatActivity {

    // variable to hold the password toggle button
    private ImageButton eyeButton = null;
    //variable to hold the password input text box
    private EditText passwordInput = null;
    //variable to check the status of toggle false: eye open, cant see password , true: eye close, can see password
    private boolean passwordVisibility = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //initialising the image button , toggle button
        eyeButton = (ImageButton) findViewById(R.id.eye_button);
        //initialising the edit text , password text box
        passwordInput = (EditText) findViewById(R.id.password_input);
        //initialising register button
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

