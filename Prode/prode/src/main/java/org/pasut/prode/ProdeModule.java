package org.pasut.prode;

import com.google.inject.AbstractModule;

import org.pasut.prode.authentication.MailAuthentication;
import org.pasut.prode.authentication.User;
import org.pasut.prode.entities.helpers.FixturePersisterHelper;
import org.pasut.prode.services.FixtureService;
import org.pasut.prode.services.PersisterService;
import org.pasut.prode.services.PreferencesService;
import org.pasut.prode.services.UserService;
import org.pasut.prode.services.implementation.DefaultFixtureService;
import org.pasut.prode.services.implementation.DefaultUserService;
import org.pasut.prode.services.implementation.ParsePersisterService;
import org.pasut.prode.entities.helpers.UserPersisterHelper;
import org.pasut.prode.services.implementation.UserProvider;
import org.pasut.prode.utils.DeviceResolver;

/**
 * Created by marcelo on 24/03/14.
 */
public class ProdeModule extends AbstractModule {
    @Override
    protected void configure() {
        configureHelpers();
        this.bind(DeviceResolver.class);
        configurePreferences();
        configureEntityServices();
        this.bind(MailAuthentication.class);
        this.bind(User.class).toProvider(UserProvider.class);
    }

    private void configureEntityServices() {
        this.bind(UserService.class).to(DefaultUserService.class);
        this.bind(FixtureService.class).to(DefaultFixtureService.class);
    }

    private void configurePreferences() {
        this.bind(PreferencesService.class).to(org.pasut.prode.services.implementation.PreferencesService.class);
        this.bind(PersisterService.class).to(ParsePersisterService.class);
    }

    private void configureHelpers() {
        this.bind(UserPersisterHelper.class);
        this.bind(FixturePersisterHelper.class);
    }
}
