package org.pasut.prode.utils;

import android.content.Context;
import android.provider.Settings;

import com.google.inject.Inject;

import roboguice.inject.ContextSingleton;

/**
 * Created by marcelo on 07/04/14.
 */
@ContextSingleton
public class DeviceResolver {
    private final Context context;
    private String deviceId;

    @Inject
    public DeviceResolver(Context context) {
        this.context = context;
    }

    public String getDeviceId() {
        if(deviceId==null) deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }
}
