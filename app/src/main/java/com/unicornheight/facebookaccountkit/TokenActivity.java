package com.unicornheight.facebookaccountkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;


/**
 * Created by debo on 02/09/2016.
 */
public class TokenActivity  extends Activity {

    public void onBackPressed() {
        //super.onBackPressed();
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        //Button logNow = (Button) findViewById(R.id.log_int)
        final Button signOut = (Button) findViewById(R.id.log_out_button);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"Logged In With Facebook", Toast.LENGTH_SHORT).show();
                //new LoginActivity().onLoginSuccess();
                Intent intent = new Intent(TokenActivity.this, MainActivity.class);
                startActivity(intent);
              //  AccountKit.logOut();
                finish();
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        // Workshop slide 26 - 29
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                final TextView userId = (TextView) findViewById(R.id.user_id);
                userId.setText(account.getId());

                final TextView phoneNumber = (TextView) findViewById(R.id.user_phone);
                final PhoneNumber number = account.getPhoneNumber();
                phoneNumber.setText(number == null ? null : number.toString());

                final TextView email = (TextView) findViewById(R.id.user_email);
                email.setText(account.getEmail());
            }

            @Override
            public void onError(final AccountKitError error) {
            }
        });
    }
}
