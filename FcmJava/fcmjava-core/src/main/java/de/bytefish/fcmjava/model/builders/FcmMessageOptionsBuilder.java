// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fcmjava.model.builders;

import java.util.concurrent.TimeUnit;

import de.bytefish.fcmjava.model.enums.PriorityEnum;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;

public class FcmMessageOptionsBuilder {

    private String condition = null;
    private String collapseKey = null;
    private PriorityEnum priorityEnum = null;
    private Boolean contentAvailable = null;
    private Boolean delayWhileIdle = null;
    private int timeToLive = 60;
    private String restrictedPackageName = null;
    private Boolean dryRun = null;

    public FcmMessageOptionsBuilder setCondition(String condition) {
        this.condition = condition;

        return this;
    }

    public FcmMessageOptionsBuilder setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;

        return this;
    }

    public FcmMessageOptionsBuilder setPriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;

        return this;
    }

    public FcmMessageOptionsBuilder setContentAvailable(Boolean contentAvailable) {
        this.contentAvailable = contentAvailable;

        return this;
    }

    public FcmMessageOptionsBuilder setDelayWhileIdle(Boolean delayWhileIdle) {
        this.delayWhileIdle = delayWhileIdle;

        return this;
    }

    public FcmMessageOptionsBuilder setTimeToLive(TimeUnit timeUnit, long amount) {
        this.timeToLive = (int) timeUnit.toSeconds(amount);

        return this;
    }

    public FcmMessageOptionsBuilder setRestrictedPackageName(String restrictedPackageName) {
        this.restrictedPackageName = restrictedPackageName;

        return this;
    }

    public FcmMessageOptionsBuilder setDryRun(Boolean dryRun) {
        this.dryRun = dryRun;

        return this;
    }

    public FcmMessageOptions build() {
        return new FcmMessageOptions(condition, collapseKey, priorityEnum, contentAvailable, delayWhileIdle, timeToLive, restrictedPackageName, dryRun);
    }
}
