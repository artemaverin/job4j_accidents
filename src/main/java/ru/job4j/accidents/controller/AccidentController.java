package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidents;

    private AccidentTypeService types;

    @GetMapping()
    public String getIndexPage(Model model) {
        model.addAttribute("class_active", "index");
        model.addAttribute("accidents", accidents.findAll());
        return "accidents/accidentList";
    }

    @GetMapping({"/{id}"})
    public String getById(Model model, @PathVariable int id) {
        var accidentOptional = accidents.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Нарушения с указанным идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("types", types.findAll().values());
        model.addAttribute("accident", accidentOptional.get());
        return "accidents/editAccident";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", types.findAll().values());
        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        var accidentType = types.findAll().get(accident.getType().getId());
        accident.setType(accidentType);
        accidents.create(accident);
        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, Model model) {
        var accidentType = types.findAll().get(accident.getType().getId());
        accident.setType(accidentType);
        var isUpdated = accidents.update(accident);
        if (!isUpdated) {
            model.addAttribute("message", "Резюме с указанным идентификатором не найдено");
            return "errors/404";
        }
        return "redirect:/accidents";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id).get());
        model.addAttribute("types", types.findAll().values());
        return "accidents/update";
    }
}
