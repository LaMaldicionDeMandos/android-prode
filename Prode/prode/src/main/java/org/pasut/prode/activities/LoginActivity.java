package org.pasut.prode.activities;

import android.os.Bundle;
import org.pasut.prode.R;

import roboguice.activity.RoboActivity;

import static org.pasut.prode.ActivityUtils.asFullScreen;

public class LoginActivity extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asFullScreen(this);
        setContentView(R.layout.activity_login);
    }
}
