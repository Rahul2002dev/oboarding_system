﻿# oboarding_system
 
📘 Project Overview
The Candidate Onboarding System manages the complete journey of a hired candidate, from
offer notification to document upload and onboarding confirmation. It provides APIs to:
● View hired candidates & profiles
● Update statuses (offer sent, onboarded, etc.)
● Notify candidates via email
● Update personal, bank, and educational details
● Upload & verify documents
● Track onboarding status
● Generate reports (Excel/PDF optional in future phase)

🏗 Architecture Overview
📦 Layered Architecture
plaintext
CopyEdit
|----------------------------|
| Controller Layer | --> REST APIs
|----------------------------|
| Service Layer | --> Business Logic
|----------------------------|
| Repository Layer | --> Spring Data JPA (MySQL)
|----------------------------|
| Entity/Model Layer | --> JPA Entities
|----------------------------|
| Utils / Helpers / DTOs |
|----------------------------|

🔧 Tech Stack
Area Technology
Framework Spring Boot
ORM Spring Data JPA (Hibernate)
DB MySQL
Messaging Queue RabbitMQ
Email Notifications Spring Mail
Logging SLF4J + Logback
AOP Spring AOP
Exception Handling Global Exception Handler
API Clients (if needed) RestTemplate
File Uploads Multipart + Cloud/local path
Validation JSR 380 (Bean Validation)

📚 Module Breakdown
1. Candidate Management Module
● GET /api/candidates/hired
● GET /api/candidates/{id}
● GET /api/candidates/count

2. Candidate Status Module
● POST /api/candidates/{id}/status
● PUT /api/candidates/{id}/onboard-status

3. Job Offer Notification Module
● POST /api/candidates/{id}/notify-job-offer
○ RabbitMQ to handle async email queue
○ Spring Mail for sending email
○ Retry queue on failure

4. Candidate Info Module
● PUT /api/candidates/{id}/personal-info
● PUT /api/candidates/{id}/bank-info
● PUT /api/candidates/{id}/educational-info

5. Document Module
● POST /api/candidates/{id}/upload-document
● PUT /api/candidates/{id}/verify-document

📤 Asynchronous Communication (RabbitMQ)
Use Case: Job Offer Email Notification
● Service sends a message to RabbitMQ
● Consumer listens and sends email
● Adds resilience and scalability

📩 Email Notification (Spring Mail)
● Custom HTML email template for job offers
● Dynamic content (candidate name, position, etc.)
● Attachments (optional)

🧠 Business Logic - Service Layer
● Handle status transitions: APPLIED → INTERVIEWED → OFFERED → ONBOARDED
● Validate documents and personal info
● Handle retries and failures gracefully

🔐 Security (Optional Phase)
● JWT for secure API access (Admin/HR roles)
● Spring Security (Role-based access)

📑 Logging and AOP
Logging
● SLF4J with Logback
● Log API requests, DB errors, document uploads

AOP

● Method execution time logging
● Error tracing
● Audit logs for critical actions

❗ Global Exception Handling
● Custom Exception Classes: CandidateNotFoundException,
InvalidStatusException, etc.
● @ControllerAdvice with @ExceptionHandler
● Return proper HTTP status codes with structured response

json
CopyEdit
{
"timestamp": "2025-05-05T14:23:00",
"status": 404,
"error": "Candidate Not Found",
"message": "Candidate with ID 12 does not exist",
"path": "/api/candidates/12"
}

📂 Suggested Directory Structure
graphql
CopyEdit
com.example.onboarding
│
├── config # Config classes (email, RabbitMQ, etc.)
├── controller # API endpoints
├── dto # Request/response objects
├── entity # JPA entities

├── exception # Custom exceptions & handler
├── repository # Spring Data JPA repos
├── service # Business logic
├── util # Utility classes (mappers, file, etc.)
├── listener # RabbitMQ consumers
└── Application.java # Main Spring Boot class

📋 Requirements Summary

Feature Requirement
View Candidates Paginated, sorted listing with filters
View Profile Full details + documents + onboarding status
Update Status Enum-based, tracked transitions
Notify Job Offer Email + Queue + Logging
Update Info Personal, Bank, Education validations
Upload/Verify Documents File storage (cloud/local), status flags
AOP Logging Method execution, DB calls, exception tracing
Global Exception Handling Custom messages, traceability
RabbitMQ Reliable async communication
Email via Spring Mail Dynamic templates, HTML emails
