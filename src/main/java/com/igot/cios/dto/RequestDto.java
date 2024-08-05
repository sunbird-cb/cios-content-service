package com.igot.cios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto {
    private String providerName;
    private int size;
    private int page;
    private Boolean isActive;
}
