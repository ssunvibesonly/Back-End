DROP TABLE if EXISTS member CASCADE;
CREATE TABLE member(
    id bigint generated BY DEFAULT as IDENTITY,
    NAME VARCHAR(255),
    PRIMARY KEY(id)
)

