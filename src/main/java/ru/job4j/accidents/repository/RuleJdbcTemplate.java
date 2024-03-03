package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Collection<Rule> findAll() {
        return jdbc.query("select * from rules_table", new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public Set<Rule> findByIds(List<Integer> rIds) {
        String inSql = String.join(",", Collections.nCopies(rIds.size(), "?"));
        return new HashSet<>(jdbc.query(String.format("select * from rules_table where id IN (%s)", inSql),
                rIds.toArray(),
                        (rs, row) -> new Rule(rs.getInt("id"), rs.getString("name"))));
    }
}
