package org.pasut.prode.services.implementation;

import com.google.inject.Inject;

import org.pasut.prode.ProdeApplication;
import org.pasut.prode.authentication.User;
import org.pasut.prode.services.PersisterService;
import org.pasut.prode.services.PreferencesService;
import org.pasut.prode.services.UserService;

/**
 * Created by marcelo on 07/04/14.
 */
public class DefaultUserService implements UserService {
    private final PersisterService persisterService;
    private final PreferencesService preferencesService;
    private final UserPersisterHelper helper;

    @Inject
    public DefaultUserService(PersisterService persisterService, PreferencesService preferencesService) {
        this.persisterService = persisterService;
        this.preferencesService = preferencesService;
        this.helper = new UserPersisterHelper();
    }
    @Override
    public void saveUser(User user) {
        user = persisterService.put(user, helper);
        preferencesService.put(ProdeApplication.CURRENT_USER_KEY, user);
    }
}
