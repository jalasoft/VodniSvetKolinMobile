package cz.jalasoft.mobile.swimming.activity;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;

/**
 * Created by Honza "Honzales" Lastovicka on 1/10/16.
 */
public interface SwimmingPoolDisplay {

    void update(SwimmingPool pool);

    void handleFail(Exception exc);

}
