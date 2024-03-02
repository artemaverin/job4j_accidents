package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidents;

    private AccidentTypeService types;

    private RuleService rules;

    @GetMapping()
    public String getIndexPage(Model model) {
        model.addAttribute("class_active", "index");
        var a = accidents.findAll();
        model.addAttribute("accidents", accidents.findAll());
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "accidents/accidentList";
    }

    @GetMapping({"/{id}"})
    public String getById(Model model, @PathVariable int id) {
        var accidentOptional = accidents.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Нарушения с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("types", types.findAll());
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("rules", rules.findAll());
        return "accidents/editAccident";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, Model model, HttpServletRequest req,
                       @RequestParam List<Integer> rIds) {
        var accidentTypeOptional = types.findById(accident.getType().getId());
        if (accidentTypeOptional.isEmpty()) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        var rulesSet = rules.findByIds(rIds);
        accident.setType(accidentTypeOptional.get());
        accident.setRules(rulesSet);
        accidents.create(accident);
        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, Model model, HttpServletRequest req,
                         @RequestParam List<Integer> rIds) {
        var accidentTypeOptional = types.findById(accident.getType().getId());
        var rulesSet = rules.findByIds(rIds);
        accident.setRules(rulesSet);
        if (accidentTypeOptional.isEmpty()) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        accident.setType(accidentTypeOptional.get());
        var isUpdated = accidents.update(accident);
        if (!isUpdated) {
            model.addAttribute("message", "Не удалось обновить нарушение");
            return "errors/404";
        }
        return "redirect:/accidents";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        var accidentOptional = accidents.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Нарушения с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "accidents/update";
    }
}
