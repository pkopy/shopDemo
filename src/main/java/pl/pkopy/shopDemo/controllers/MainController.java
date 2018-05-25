package pl.pkopy.shopDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pkopy.shopDemo.models.BarcodeEntity;
import pl.pkopy.shopDemo.models.forms.BarcodeForm;
import pl.pkopy.shopDemo.models.repositories.BarcodeRepository;

@Controller
public class MainController {

    @Autowired
    BarcodeRepository barcodeRepository;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("barcodeForm", new BarcodeForm());
        model.addAttribute("allBarcodes", barcodeRepository.findAll());

        return "addBarcode";
    }

    @PostMapping("/")

    public String index(@ModelAttribute BarcodeForm barcodeForm){
        BarcodeEntity barcodeEntity = new BarcodeEntity(barcodeForm);

        barcodeRepository.save(barcodeEntity);
        return "redirect:/";
    }
}
