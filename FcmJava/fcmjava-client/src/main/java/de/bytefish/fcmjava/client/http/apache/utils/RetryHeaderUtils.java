// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fcmjava.client.http.apache.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.util.concurrent.TimeUnit;

import de.bytefish.fcmjava.client.utils.OutParameter;
import de.bytefish.fcmjava.client.utils.StringUtils;

public class RetryHeaderUtils {

    public static boolean tryDetermineRetryDelay(HttpResponse httpResponse, OutParameter<Long>
            result) {
        try {
            return internalTryDetermineRetryDelay(httpResponse, result);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean internalTryDetermineRetryDelay(HttpResponse httpResponse,
            OutParameter<Long> result) {

        // Try to get the Retry-After Header send by FCM:
        Header retryAfterHeader = httpResponse.getFirstHeader("Retry-After");

        // Early exit, if we do not have a Retry Header:
        if (retryAfterHeader == null) {
            return false;
        }

        // Try to get the Value:
        String retryDelayAsString = retryAfterHeader.getValue();

        // Early exit, if the Retry Header has no Value:
        if(StringUtils.isNullOrWhiteSpace(retryDelayAsString)) {
            return false;
        }

        // First check if we have a Number Retry Delay as Seconds:
        return tryGetFromLong(retryDelayAsString, result);
    }

    private static boolean tryGetFromLong(String retryDelayAsString, OutParameter<Long> result) {

        // Try to convert the String to a Long:
        OutParameter<Long> longResult = new OutParameter<>();

        if(!tryConvertToLong(retryDelayAsString, longResult)) {
            return false;
        }

        // If we can convert it to Long, then convert to a Duration in seconds:
        result.set(TimeUnit.MILLISECONDS.toSeconds(longResult.get()));

        return true;
    }

    private static boolean tryConvertToLong(String longAsString, OutParameter<Long> result) {
        try {
            result.set(Long.parseLong(longAsString));

            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
