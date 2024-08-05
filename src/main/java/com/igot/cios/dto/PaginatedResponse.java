package com.igot.cios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponse<T> {
    private List<T> result;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int size;
    private int page;
}