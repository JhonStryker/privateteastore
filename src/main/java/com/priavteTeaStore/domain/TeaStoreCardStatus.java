package com.priavteTeaStore.domain;

import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.util.ERR;
import com.priavteTeaStore.util.OperationResult;
import com.priavteTeaStore.util.TimeUtil;

import javax.persistence.Embeddable;
import java.sql.Time;

/**
 * Created by Thoughtworks on 16/6/9.
 */

@Embeddable
public class TeaStoreCardStatus {
    Status status = Status.no_active_card;
    Time activeTime = null;

    public boolean isActiveTeaStore() {
        return this.status == Status.active_teaStore;
    }

    public OperationResult active() {
        if (!isActiveTeaStore()) {
            this.status = Status.active_teaStore;
            activeTime = TimeUtil.getCurrentTime();
            return OperationResult.success;
        } else {
            throw new ToClientException(ERR.already_has_active);
        }
    }

    enum Status {
        no_active_card,
        active_teaStore,
    }
}
