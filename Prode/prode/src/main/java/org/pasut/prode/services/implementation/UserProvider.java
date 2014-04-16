package org.pasut.prode.services.implementation;

import com.google.inject.Inject;
import com.google.inject.Provider;

import org.pasut.prode.ProdeApplication;
import org.pasut.prode.authentication.User;
import org.pasut.prode.services.PreferencesService;

/**
 * Created by marcelo on 15/04/14.
 */
public class UserProvider implements Provider<User> {
    private final PreferencesService preferenceService;

    @Inject
    public UserProvider(PreferencesService preferencesService) {
        this.preferenceService = preferencesService;
    }
    @Override
    public User get() {
        return preferenceService.get(ProdeApplication.CURRENT_USER_KEY, User.class);
    }
}
