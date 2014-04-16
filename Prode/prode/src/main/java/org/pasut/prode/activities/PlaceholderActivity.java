package org.pasut.prode.activities;

import android.os.Bundle;

import com.google.inject.Inject;

import org.pasut.prode.ProdeApplication;
import org.pasut.prode.R;
import org.pasut.prode.services.PreferencesService;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

import static org.pasut.prode.ActivityUtils.*;

@ContentView(R.layout.activity_placeholder)
public class PlaceholderActivity extends RoboActivity {
    @Inject
    private PreferencesService preferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        asFullScreen(this);
        super.onCreate(savedInstanceState);
        startActivityAndDestroy(this, preferencesService.contain(ProdeApplication.CURRENT_USER_KEY)
            ? MainActivity.class
            : LoginActivity.class);

    }
}
