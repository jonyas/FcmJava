// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fcmjava.model.topics;

import java.util.List;

public class TopicList {

    private final List<Topic> topics;

    public TopicList(List<Topic> topics) {

        this.topics = topics;
    }

    public List<Topic> getTopics() {

        return topics;
    }

    public String getTopicsCondition() {

        StringBuilder sb = new StringBuilder();
        for (Topic topic : topics) {
            sb.append(String.format("'%s' in topics", topic.getName()));
            sb.append(" || ");
        }
        return sb.toString();
    }
}
