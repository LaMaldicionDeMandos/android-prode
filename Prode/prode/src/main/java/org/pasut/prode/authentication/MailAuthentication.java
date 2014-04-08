package org.pasut.prode.authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.json.JSONException;
import org.json.JSONObject;
import org.pasut.prode.R;
import org.pasut.prode.services.UserService;
import org.pasut.prode.utils.DeviceResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marcelo on 29/03/14.
 */
@Singleton
public class MailAuthentication implements Authentication {
    private static final String MAIL_ACCOUNT_TYPE = "com.google";
    private static final String AUTH_TOKEN_TYPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    private static final String USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=%s&client_id=%s";
    private static final String NAME_KEY = "name";
    private static final String ID_KEY = "id";

    private static final String TAG = MailAuthentication.class.getSimpleName();

    private final UserService userService;
    private final DeviceResolver deviceResolver;

    @Inject
    public MailAuthentication(UserService userService, DeviceResolver deviceResolver) {
        this.userService = userService;
        this.deviceResolver = deviceResolver;
    }

    @Override
    public void login(final Context context) {
        AccountManager accountManager = AccountManager.get(context);
        final Account[] accounts = checkNotNull(accountManager.getAccountsByType(MAIL_ACCOUNT_TYPE));
        checkArgument(accounts.length > 0);
        Bundle options = new Bundle();
        final Account account = accounts[0];
        accountManager.getAuthToken(accounts[0], AUTH_TOKEN_TYPE, options, (Activity)context, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle result = future.getResult();
                    final String token = result.getString(AccountManager.KEY_AUTHTOKEN);
                    URL url = new URL(String.format(USER_INFO,token, context.getString(R.string.cliet_api)));
                    AsyncTask<URL, Void, String> asyncTask = new AsyncTask<URL, Void, String>() {
                        @Override
                        protected String doInBackground(URL... params) {
                            try {
                                HttpURLConnection con = (HttpURLConnection) params[0].openConnection();
                                int sc = 0;
                                sc = con.getResponseCode();
                                if (sc == 200) {
                                    InputStream is = con.getInputStream();
                                    String json = readResponse(is);
                                    is.close();
                                    return json;
                                } else if (sc == 401) {
                                    GoogleAuthUtil.invalidateToken(context, token);
                                    Log.i(TAG, "Server auth error: " + readResponse(con.getErrorStream()));
                                    return null;
                                } else {
                                    return null;
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                        @Override
                        protected void onPostExecute(String json) {
                            try {
                                String name = getFirstName(json);
                                Log.d(TAG, "User First Name: " + name);
                                User user = new User(
                                        getId(json),
                                        account.name,
                                        UserType.GOOGLE,
                                        deviceResolver.getDeviceId(),
                                        getFirstName(json));
                                userService.saveUser(user);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                    asyncTask.execute(url);

                } catch (OperationCanceledException e) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                } catch (Exception e) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    e.printStackTrace();
                }
            }
        }, new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d("Login", msg.toString());
                return false;
            }
        }));
    }

    /**
     * Parses the response and returns the first name of the user.
     * @throws JSONException if the response is not JSON or if first name does not exist in response
     */
    private String getFirstName(String jsonResponse) throws JSONException {
        JSONObject profile = new JSONObject(jsonResponse);
        return profile.getString(NAME_KEY);
    }

    /**
     * Parses the response and returns the id of the user.
     * @throws JSONException if the response is not JSON or if first name does not exist in response
     */
    private String getId(String jsonResponse) throws JSONException {
        JSONObject profile = new JSONObject(jsonResponse);
        return profile.getString(ID_KEY);
    }

    /**
     * Reads the response from the input stream and returns it as a string.
     */
    private static String readResponse(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        int len = 0;
        while ((len = is.read(data, 0, data.length)) >= 0) {
            bos.write(data, 0, len);
        }
        return new String(bos.toByteArray(), "UTF-8");
    }
}
