// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fcmjava.exceptions;

/**
 * This Exception is thrown, when a message failed, but we are allowed to Retry it. You have to respect the Retry Delay
 * associated with this Exception, before you retry the Operation. You can use the RetryUtils to retry the operations.
 */
public class FcmRetryAfterException extends FcmException {

    private final long retryDelay;

    public FcmRetryAfterException(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public FcmRetryAfterException(long retryDelay, String message) {
        super(message);

        this.retryDelay = retryDelay;
    }

    public FcmRetryAfterException(long retryDelay, String message, Throwable cause) {
        super(message, cause);

        this.retryDelay = retryDelay;
    }

    public FcmRetryAfterException(long retryDelay, Throwable cause) {
        super(cause);

        this.retryDelay = retryDelay;
    }

    public FcmRetryAfterException(long retryDelay, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

        this.retryDelay = retryDelay;
    }

    public long getRetryDelay() {
        return retryDelay;
    }
}
