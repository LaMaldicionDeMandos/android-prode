package org.pasut.prode.activities;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.inject.Inject;

import org.pasut.prode.R;
import org.pasut.prode.authentication.MailAuthentication;

import java.io.IOException;

import roboguice.activity.RoboActivity;

import static org.pasut.prode.ActivityUtils.asFullScreen;

public class LoginActivity extends RoboActivity {
    @Inject private MailAuthentication mailAuthentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asFullScreen(this);
        setContentView(R.layout.activity_login);
    }

    public void loginWithMail(View view) throws IOException, GoogleAuthException {
        mailAuthentication.login(this);
    }
}
