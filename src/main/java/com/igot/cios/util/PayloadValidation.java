package com.igot.cios.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.igot.cios.exception.CiosContentException;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Set;

@Slf4j
@Service
public class PayloadValidation {
    public void validatePayload(String fileName, JsonNode payload) {
        try {
            log.debug("PayloadValidation :: validatePayload");
            JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance();
            InputStream schemaStream = schemaFactory.getClass().getResourceAsStream(fileName);
            JsonSchema schema = schemaFactory.getSchema(schemaStream);
            if (payload.isArray()) {
                for (JsonNode objectNode : payload) {
                    validateObject(schema, objectNode);
                }
            } else {
                validateObject(schema, payload);
            }
        } catch (Exception e) {
            log.error("Failed to validate payload", e);
            throw new CiosContentException("Failed to validate payload", e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateObject(JsonSchema schema, JsonNode objectNode) {
        Set<ValidationMessage> validationMessages = schema.validate(objectNode);
        if (!validationMessages.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation error(s): \n");
            for (ValidationMessage message : validationMessages) {
                errorMessage.append(message.getMessage()).append("\n");
            }
            log.error("Validation Error", errorMessage.toString());
            throw new CiosContentException("Validation Error", errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}

