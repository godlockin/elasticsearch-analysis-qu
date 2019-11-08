package org.elasticsearch.common.entity;

import lombok.Data;

@Data
public class Segment {

    private int startIndex;
    private int endIndex;
    private String pos;
    private String text;
}
