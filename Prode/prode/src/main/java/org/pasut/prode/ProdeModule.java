package org.pasut.prode;

import com.google.inject.AbstractModule;

import org.pasut.prode.services.PreferencesService;

/**
 * Created by marcelo on 24/03/14.
 */
public class ProdeModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(PreferencesService.class).to(org.pasut.prode.services.implementation.PreferencesService.class);
    }
}
