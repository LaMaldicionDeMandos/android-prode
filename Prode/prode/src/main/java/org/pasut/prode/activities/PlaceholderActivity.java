package org.pasut.prode.activities;

import android.os.Bundle;

import com.google.inject.Inject;

import org.pasut.prode.MainActivity;
import org.pasut.prode.ProdeApplication;
import org.pasut.prode.R;
import org.pasut.prode.authentication.User;
import org.pasut.prode.services.PreferencesService;

import roboguice.activity.RoboActivity;

import static org.pasut.prode.ActivityUtils.*;

public class PlaceholderActivity extends RoboActivity {
    @Inject
    private PreferencesService preferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asFullScreen(this);
        setContentView(R.layout.activity_placeholder);
        startActivityAndDestroy(this, preferencesService.contain(ProdeApplication.CURRENT_USER_KEY)
            ? MainActivity.class
            : LoginActivity.class);

    }
}
