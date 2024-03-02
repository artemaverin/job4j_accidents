CREATE TABLE accidents_rule (
   id serial PRIMARY KEY,
   accident_id int not null REFERENCES accidents(id),
   rule_id int not null REFERENCES rules_table(id),
   UNIQUE (accident_id, rule_id)
);