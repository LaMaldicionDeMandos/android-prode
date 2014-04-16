package org.pasut.prode.activities;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.inject.Inject;

import org.pasut.prode.R;
import org.pasut.prode.authentication.MailAuthentication;

import java.io.IOException;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

import static org.pasut.prode.ActivityUtils.asFullScreen;
import static org.pasut.prode.ActivityUtils.startActivityAndDestroy;

@ContentView(R.layout.activity_login)
public class LoginActivity extends RoboActivity {
    @Inject private MailAuthentication mailAuthentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        asFullScreen(this);
        super.onCreate(savedInstanceState);
    }

    public void loginWithMail(View view) throws IOException, GoogleAuthException {
        mailAuthentication.login(this);
        startActivityAndDestroy(this, MainActivity.class);
    }
}
