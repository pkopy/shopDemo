package pl.pkopy.shopDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pkopy.shopDemo.models.BarcodeEntity;
import pl.pkopy.shopDemo.models.forms.BarcodeForm;
import pl.pkopy.shopDemo.models.repositories.BarcodeRepository;
import pl.pkopy.shopDemo.models.services.BasketService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    BarcodeRepository barcodeRepository;

    @Autowired
    BasketService basketService;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("barcodeForm", new BarcodeForm());
        model.addAttribute("allBarcodes", barcodeRepository.findAll());
        model.addAttribute("basket", basketService);

        return "addBarcode";
    }

    @PostMapping("/")

    public String index(@ModelAttribute BarcodeForm barcodeForm){
        BarcodeEntity barcodeEntity = new BarcodeEntity(barcodeForm);

        barcodeRepository.save(barcodeEntity);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("barcodeForm", new BarcodeForm());

        return "search";
    }

    @PostMapping("/search")
    public String searchPost(@RequestParam("search") String search, Model model){
        List<BarcodeEntity> barcodeEntities = new ArrayList<>();
        barcodeEntities.addAll(barcodeRepository.findAllByProductNameContains(search));
        barcodeEntities.addAll(barcodeRepository.findAllByProductCompanyContains(search));

        model.addAttribute("barcodeForm", new BarcodeForm());
        model.addAttribute("allBarcodes", barcodeEntities);
        return "addBarcode";

    }

    @GetMapping("/add/{id}")


    public String addToBasket(@PathVariable("id") int id){

        basketService.addProductToBasket(barcodeRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String removeFromBasket(@PathVariable("id") int id){

        basketService.removeProductFromBasket(id);
        return "redirect:/";
    }
}
