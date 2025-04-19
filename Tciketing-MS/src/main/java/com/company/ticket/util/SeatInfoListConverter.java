package com.company.ticket.util;

import java.util.List;

import com.company.ticket.model.SeatInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SeatInfoListConverter implements AttributeConverter<List<SeatInfo>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<SeatInfo> seatInfoList) {
        try {
            return objectMapper.writeValueAsString(seatInfoList);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert seat list to JSON", e);
        }
    }

    @Override
    public List<SeatInfo> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<SeatInfo>>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert JSON to seat list", e);
        }
    }

}
