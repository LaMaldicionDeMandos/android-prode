package org.pasut.prode.services.implementation;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.pasut.prode.entities.Fixture;
import org.pasut.prode.entities.helpers.FixturePersisterHelper;
import org.pasut.prode.services.FixtureService;
import org.pasut.prode.services.datastorage.PersisterService;

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
    public void findAvailableFixtures(Date date, PersisterService.FindCallback<List<Fixture>> callback) {
        PersisterService.CriteriaBuilder builder = persisterService.getCriteriaBuilder(helper);
        builder.withGt(FixturePersisterHelper.CLOSE_DATE, new Date());
        persisterService.find(helper, builder, callback);
    }
}
