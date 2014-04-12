package org.pasut.prode;

import com.google.inject.AbstractModule;

import org.pasut.prode.authentication.MailAuthentication;
import org.pasut.prode.services.PersisterService;
import org.pasut.prode.services.PreferencesService;
import org.pasut.prode.services.UserService;
import org.pasut.prode.services.implementation.DefaultUserService;
import org.pasut.prode.services.implementation.ParsePersisterService;
import org.pasut.prode.utils.DeviceResolver;

/**
 * Created by marcelo on 24/03/14.
 */
public class ProdeModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(DeviceResolver.class);
        this.bind(PreferencesService.class).to(org.pasut.prode.services.implementation.PreferencesService.class);
        this.bind(PersisterService.class).to(ParsePersisterService.class);
        this.bind(UserService.class).to(DefaultUserService.class);
        this.bind(MailAuthentication.class);
    }
}
