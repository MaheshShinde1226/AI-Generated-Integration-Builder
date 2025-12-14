package com.example.ai_integration_builder.service;

import com.example.ai_integration_builder.entity.FieldMapping;
import com.example.ai_integration_builder.entity.TempUser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingService {
    public TempUser map(JsonNode node, List<FieldMapping> mappings, String sourceApp) {
        TempUser user = new TempUser();
        user.setSourceApp(sourceApp);

        for (FieldMapping fm : mappings) {
            String value = node.at("/" + fm.getSourcePath().replace(".", "/")).asText();
            if ("email".equals(fm.getTargetField())) user.setEmail(value);
            if ("name".equals(fm.getTargetField())) user.setName(value);
        }
        return user;
    }
}
