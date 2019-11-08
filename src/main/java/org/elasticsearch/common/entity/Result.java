package org.elasticsearch.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private List<Segment> segment;
}
