package cz.jalasoft.mobile.swimming.domain.model.tracking;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.util.Optional;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public final class PoolTrackingService {

    private final PoolStatusService statusService;
    private final PoolTrackingDescriptorRepository configRepository;

    public PoolTrackingService(PoolStatusService statusService, PoolTrackingDescriptorRepository configRepository) {
        this.statusService = statusService;
        this.configRepository = configRepository;
    }

    public void performTracking(final AsyncCallback<Optional<PoolTracking>> callback) {
        final PoolTrackingDescriptor descriptor = configRepository.get();

        statusService.getStatusAsynchronously(new AsyncCallback<Optional<PoolStatus>>() {
            @Override
            public void process(Optional<PoolStatus> maybePoolStatus) {
                if (maybePoolStatus.isNotPresent()) {
                    callback.process(Optional.<PoolTracking>empty());
                } else {
                    PoolTracking tracking = new PoolTracking(maybePoolStatus.get(), descriptor);
                    callback.process(Optional.of(tracking));
                }
            }

            @Override
            public void processFail(Exception exc) {
                callback.processFail(exc);
            }
        });
    }
}
