# AI-Generated Integration Builder (Spring Boot)

## Overview
This project demonstrates a **config-driven integration framework** built using Spring Boot.  
It enables dynamic integration with third-party SaaS applications without redeploying the service.

Calendly is implemented as a proof of concept.

---

## AI Integration Builder (Conceptual)

> AI code is not implemented intentionally.  
> This project represents the backend foundation that an AI-generated integration system would use.

### How AI Would Read API Docs
1. User provides an API documentation URL.
2. Documentation is parsed and chunked.
3. A Retrieval-Augmented Generation (RAG) approach is used to:
   - Detect authentication type
   - Identify base URLs and endpoints
   - Detect pagination strategy
   - Extract response schemas

---

## How Configuration Is Auto-Generated
Instead of generating executable code, AI generates **structured configuration** stored in DB tables:
- Application metadata (base URL, auth type)
- Endpoint definitions
- Pagination details
- Field mappings

The existing generic execution engine uses this configuration to perform integrations dynamically.

---

## AI Model Choice
- GPT-4 / GPT-4o
- Retrieval-Augmented Generation (RAG) using official API documentation

---

## Safeguards
- Sandbox execution before production
- Read-only access by default
- Rate-limit aware retries
- Human approval before enabling production sync
- Versioned configuration with rollback

---

## Architecture
Controller
→ IntegrationService
→ GenericApiService
→ WebClient
→ MappingService
→ Temp User Store


---

## Implemented Integration
- Calendly user sync
- Cursor-based pagination
- Retry with exponential backoff
- Dynamic field mapping from DB

---

## How to Run
```bash
mvn clean install
mvn spring-boot:run

## Sync Users
POST /integrations/calendly/sync-users

## View Users
GET /integrations/users

## Tech Stack
Java 17
Spring Boot 3
WebClient
Spring Data JPA
H2 Database

Author
Mahesh Shinde

