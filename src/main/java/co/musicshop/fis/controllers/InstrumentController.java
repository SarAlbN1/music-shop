package co.musicshop.fis.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import co.musicshop.fis.dtos.CreateInstrumentDto;
import co.musicshop.fis.dtos.CreateSongDto;
import co.musicshop.fis.models.Instrument;
import co.musicshop.fis.services.InstrumentService;
import co.musicshop.fis.services.SongService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    private InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }


    @GetMapping()
    public ModelAndView getAllInstruments(Model model) {
        List<Instrument> instruments = instrumentService.findAll();
        ((Model) model).addAttribute("Instruments", instruments);
        return new ModelAndView("Instruments");
        
    }


    @GetMapping("/{id}")
    public ModelAndView getInstrumentById(@PathVariable Long id) {
        Optional<Instrument> instrument = instrumentService.findById(id);
        if (instrument.isPresent()) {
            return new ModelAndView("InstrumentDetails", "instrument", instrument.get());
        } else {
            return new ModelAndView("404");
        }

    }
    @GetMapping("/{id}/edit")
    public ModelAndView editInstrumentForm(@PathVariable Long id, Model model) {
        Optional<Instrument> instrument = instrumentService.findById(id);
        if (instrument.isPresent()) {
            model.addAttribute("instrument", instrument.get());
            return new ModelAndView("InstrumentEdit");
        } else {
            return new ModelAndView("404");
        }

    }

    // Mostrar formulario para agregar una canción
    @GetMapping("/add")
    public ModelAndView addInstrumentForm(Model model) {
        model.addAttribute("createInstrumentDto", new CreateInstrumentDto());  // DTO vacío para agregar nueva canción
        return new ModelAndView("InstrumentAdd"); // Refers to add.html (view)
    }

    @PostMapping()
    public ModelAndView createInstrument(@ModelAttribute("createInstrumentDto") CreateInstrumentDto createInstrumentDto, Model model) {
        Instrument instrument = new Instrument(
                createInstrumentDto.getName(),
                createInstrumentDto.getType(),
                createInstrumentDto.getBrand(),
                createInstrumentDto.getPrice(),
                createInstrumentDto.getPhoto());

        instrumentService.save(instrument);
        model.addAttribute("instrument", instrument);
        return new ModelAndView("Instruments"); 
    }

    @PostMapping("/{id}")
    public ModelAndView updateInstrument(@PathVariable Long id, @ModelAttribute("instrument") Instrument instrument) {
        Optional<Instrument> instrumentOptional = instrumentService.findById(id);
        if (instrumentOptional.isPresent()) {
            Instrument existingInstrument = instrumentOptional.get();
            existingInstrument.setName(instrument.getName());
            existingInstrument.setType(instrument.getType());
            existingInstrument.setBrand(instrument.getBrand());
            existingInstrument.setPrice(instrument.getPrice());
            existingInstrument.setPhoto(instrument.getPhoto());
            Instrument updatedInstrument = instrumentService.save(existingInstrument);
            ModelAndView modelAndView = new ModelAndView("InstrumentUpdated");
            modelAndView.addObject("instrument", updatedInstrument);
            return modelAndView;
        } else {
            return new ModelAndView("404");
        }
    }

    @DeleteMapping("/{id}")
    public String deleteInstrument(@PathVariable Long id) {
        instrumentService.deleteById(id);
        return "redirect:/Instruments";
    }
}
