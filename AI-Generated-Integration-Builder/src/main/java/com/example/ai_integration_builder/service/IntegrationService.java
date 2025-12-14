package com.example.ai_integration_builder.service;

import com.example.ai_integration_builder.entity.FieldMapping;
import com.example.ai_integration_builder.entity.IntegrationEndpoint;
import com.example.ai_integration_builder.entity.TempUser;
import com.example.ai_integration_builder.repository.FieldMappingRepository;
import com.example.ai_integration_builder.repository.IntegrationEndpointRepository;
import com.example.ai_integration_builder.repository.TempUserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IntegrationService {
    private final IntegrationEndpointRepository endpointRepo;
    private final FieldMappingRepository mappingRepo;
    private final TempUserRepository userRepo;
    private final GenericApiService apiService;
    private final MappingService mappingService;
    private final ObjectMapper mapper = new ObjectMapper();

    public IntegrationService(IntegrationEndpointRepository endpointRepo, FieldMappingRepository mappingRepo, TempUserRepository userRepo, GenericApiService apiService, MappingService mappingService) {
        this.endpointRepo = endpointRepo;
        this.mappingRepo = mappingRepo;
        this.userRepo = userRepo;
        this.apiService = apiService;
        this.mappingService = mappingService;
    }

    public void syncUsers(String appName) throws Exception {

        IntegrationEndpoint ep = endpointRepo
                .findByApp_Name(appName)
                .orElseThrow();

        List<FieldMapping> mappings =
                mappingRepo.findByEndpoint_Id(ep.getId());

        String nextUrl = ep.getApp().getBaseUrl() + ep.getPath();

        Map<String, String> headers = Map.of(
                "Authorization", "Bearer YOUR_CALENDLY_TOKEN"
        );

        // 🔹 Pagination loop
        while (nextUrl != null) {

            String response = apiService.callApi(nextUrl, headers);
            JsonNode root = mapper.readTree(response);

            JsonNode collection = root.get("collection");
            for (JsonNode node : collection) {
                TempUser user = mappingService.map(node, mappings, appName);
                userRepo.save(user);
            }

            // 🔹 Resolve next page
            nextUrl = resolveNextPage(root, ep);
        }
    }

    private String resolveNextPage(JsonNode root, IntegrationEndpoint ep) {

        if (!ep.isPaginationEnabled()) {
            return null;
        }

        JsonNode next = root.at(
                "/" + ep.getNextPageField().replace(".", "/")
        );

        return next.isMissingNode() || next.isNull()
                ? null
                : next.asText();
    }
}
