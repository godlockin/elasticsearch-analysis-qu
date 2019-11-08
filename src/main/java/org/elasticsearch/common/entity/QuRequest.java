package org.elasticsearch.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class QuRequest {
    private String query;
    private List<String> types;
}
