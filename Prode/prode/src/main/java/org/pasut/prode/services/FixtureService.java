package org.pasut.prode.services;

import org.pasut.prode.entities.Fixture;

import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 26/04/14.
 */
public interface FixtureService {
    void findAvailableFixtures(Date date, PersisterService.FindCallback<List<Fixture>> callback);
}
