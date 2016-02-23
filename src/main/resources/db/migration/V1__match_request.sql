CREATE TABLE match_request (
  id          SERIAL PRIMARY KEY,
  uuid         VARCHAR(255) NOT NULL,
  requester_id VARCHAR(255) NOT NULL,

  CONSTRAINT unique_uuid UNIQUE (uuid)
);
