INSERT INTO integration_app VALUES (1,'calendly','https://api.calendly.com','OAUTH');
INSERT INTO integration_endpoint
(id, path, http_method, pagination_enabled, pagination_type, next_page_field, app_id)
VALUES
(1, '/users/me', 'GET', true, 'CURSOR', 'pagination.next_page', 1);
INSERT INTO field_mapping VALUES (1,'resource.email','email',1);
INSERT INTO field_mapping VALUES (2,'resource.name','name',1);
