package org.pasut.prode;

import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by marcelo on 24/03/14.
 */
public class ActivityUtils {
    public static void asFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void startActivityAndDestroy(Activity activity, Class<? extends Activity> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }
}
