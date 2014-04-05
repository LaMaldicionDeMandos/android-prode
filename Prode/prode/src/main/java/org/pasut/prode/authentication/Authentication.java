package org.pasut.prode.authentication;

import android.content.Context;

import com.google.android.gms.auth.GoogleAuthException;

import java.io.IOException;

/**
 * Created by marcelo on 29/03/14.
 */
public interface Authentication {
    User findUser(Context context) throws GoogleAuthException, IOException;
}
