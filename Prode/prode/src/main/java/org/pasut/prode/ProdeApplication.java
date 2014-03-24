package org.pasut.prode;

import android.app.Application;

import roboguice.RoboGuice;

/**
 * Created by marcelo on 24/03/14.
 */
public class ProdeApplication extends Application {
    public final static String CURRENT_USER_KEY = "current_user";
    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new ProdeModule());
    }
}
