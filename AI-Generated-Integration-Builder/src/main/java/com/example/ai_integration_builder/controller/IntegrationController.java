package com.example.ai_integration_builder.controller;

import com.example.ai_integration_builder.entity.TempUser;
import com.example.ai_integration_builder.repository.TempUserRepository;
import com.example.ai_integration_builder.service.IntegrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/integrations")
public class IntegrationController {
    private final IntegrationService service;
    private final TempUserRepository userRepo;

    public IntegrationController(IntegrationService service, TempUserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    @PostMapping("/{app}/sync-users")
    public String sync(@PathVariable String app) throws Exception {
        service.syncUsers(app);
        return "Users synced successfully";
    }

    @GetMapping("/users")
    public List<TempUser> users() {
        return userRepo.findAll();
    }
}
