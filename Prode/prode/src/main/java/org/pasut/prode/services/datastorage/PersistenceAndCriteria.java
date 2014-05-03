package org.pasut.prode.services.datastorage;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by marcelo on 03/05/14.
 */
public class PersistenceAndCriteria implements PersistenceCriteria {
    private final List<PersistenceCriteria> criterias = Lists.newArrayList();

    public void add(PersistenceCriteria... criterias) {
        addAll(Arrays.asList(criterias));
    }

    public void add(Collection<PersistenceCriteria> criterias) {
        addAll(criterias);
    }

    private void addAll(Iterable<PersistenceCriteria> criterias) {
        for (PersistenceCriteria criteria : criterias) {
            this.criterias.add(criteria);
        }
    }

    @Override
    public void apply() {
        for (PersistenceCriteria criteria : criterias) {
            criteria.apply();
        }
    }
}
