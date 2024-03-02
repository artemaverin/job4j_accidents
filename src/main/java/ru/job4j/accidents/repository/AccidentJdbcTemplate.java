package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@AllArgsConstructor
@Primary
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(
                            "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);

        accident.setId((int) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        for (Rule rule: accident.getRules()) {
            jdbc.update("insert into accidents_rule(accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {

        var accidentById = jdbc.queryForObject(
                "select * from accidents where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("type_id"));
                    accident.setType(accidentType);
                    accident.setRules(new HashSet<>(jdbc.query("""
                                select rules_table.id, rules_table.name from accidents
                                join accidents_rule on accidents_rule.accident_id = accidents.id
                                join rules_table on rules_table.id = accidents_rule.rule_id
                                where accidents.id = ?
                                """,
                            new BeanPropertyRowMapper<>(Rule.class), accident.getId())));
                    return accident;
                }, id
        );
        return Optional.ofNullable(accidentById);
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query(
                "select * from accidents",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("type_id"));
                    accident.setType(accidentType);
                    accident.setRules(new HashSet<>(jdbc.query("""
                                select rules_table.id, rules_table.name
                                from accidents
                                join accidents_rule on accidents_rule.accident_id = accidents.id
                                join rules_table on rules_table.id = accidents_rule.rule_id
                                where accidents.id = ?
                                """,
                            new BeanPropertyRowMapper<>(Rule.class), accident.getId())));
                    return accident;
                }
                );
    }

    @Override
    public boolean update(Accident accident) {
        jdbc.update("delete from accidents_rule where accident_id =?", accident.getId());
        var update = jdbc.update("update accidents set name = ?, text = ?, address = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId()
                ) > 0;
        for (Rule rule: accident.getRules()) {
            jdbc.update("insert into accidents_rule(accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }
        return update;
    }
}
