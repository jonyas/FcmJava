// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fcmjava.client.retry.strategy;

import java.util.concurrent.TimeUnit;

import de.bytefish.fcmjava.client.functional.Action0;
import de.bytefish.fcmjava.client.functional.Func1;
import de.bytefish.fcmjava.exceptions.FcmRetryAfterException;

/**
 * The SimpleRetryStrategy retries all methods, that throw a @see {@link FcmRetryAfterException} for
 * a
 * maximum number of retries.
 *
 * The @see {@link FcmRetryAfterException} includes a Retry Delay, which indicates when the method
 * should be retried. This Strategy waits for the amount of time given in the @see {@link
 * FcmRetryAfterException}
 * and waits for a fixed amount of time.
 */
public class SimpleRetryStrategy implements IRetryStrategy {

    private final int maxRetries;

    public SimpleRetryStrategy(int maxRetries) {

        this.maxRetries = maxRetries;
    }

    @Override
    public void doWithRetry(final Action0 action) {

        getWithRetry(new Func1<Object>() {
            @Override
            public Object invoke() {

                action.invoke();
                return null;
            }
        });
    }

    @Override
    public <TResult> TResult getWithRetry(Func1<TResult> function) {

        // Holds the current Retry Count:
        int currentRetryCount = 0;

        // Holds the Return Value:
        TResult returnValue = null;

        // Simple Retry Loop with Thread Sleep for waiting:
        do {
            try {
                returnValue = function.invoke();
                // Break out of Loop, if there was no exception:
                break;
            } catch (FcmRetryAfterException e) {
                currentRetryCount = currentRetryCount + 1;
                // If we hit the maximum retry count, then throw the Exception:
                if (currentRetryCount == maxRetries) {
                    throw e;
                }
                // Sleep for the amount of time returned by FCM:
                internalSleep(TimeUnit.MILLISECONDS, e.getRetryDelay());
            }
        } while (currentRetryCount <= maxRetries);

        // And finally return the result:
        return returnValue;
    }

    private void internalSleep(TimeUnit timeUnit, long duration) {

        try {
            Thread.sleep(timeUnit.toMillis(duration));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
