package org.pasut.prode.services.implementation;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.pasut.prode.entities.Fixture;
import org.pasut.prode.entities.helpers.FixturePersisterHelper;
import org.pasut.prode.services.FixtureService;
import org.pasut.prode.services.PersisterService;

import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 26/04/14.
 */
@Singleton
public class DefaultFixtureService implements FixtureService {
    private final PersisterService persisterService;
    private final FixturePersisterHelper helper;

    @Inject
    public DefaultFixtureService(FixturePersisterHelper helper, PersisterService persisterService) {
        this.helper = helper;
        this.persisterService = persisterService;
    }
    @Override
    public List<Fixture> findAvailableFixtures(Date date) {
        return null;
    }
}
