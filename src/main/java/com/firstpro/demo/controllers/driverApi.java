package com.firstpro.demo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.firstpro.demo.Rebo.driversRepo;
import com.firstpro.demo.model.Drivers;
import com.firstpro.demo.model.Pagination;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("driverapi")
public class driverApi {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    driversRepo driversRepo;
    private boolean CardWithdrawn;

    @GetMapping(path="/allDrivers")
    public @ResponseBody Iterable<Drivers> getAllUsers() {
        // This returns a JSON or XML with the users
        return driversRepo.findAll();
    }
    @GetMapping(path="/SimCards")

    public Iterable<Drivers> listAll(int SimNum) {
            return driversRepo.findForChart(SimNum);

    }
    @GetMapping(path="/dateOfInsertion")

    public JSONArray listDates() {
        JSONArray res= new JSONArray();
        List<Object[]> ff=driversRepo.findForChart1();
        JSONObject o;
        for(Object obj[]:ff)
        {
           o= new JSONObject();
            o.put("count",obj[0]);
           o.put("date",obj[1]);
            res.add(o);
        }
        return res;

    }

    @PostMapping(path = "/addDriver")
    public @ResponseBody
    String addDriver (@RequestParam String Driver_name, @RequestParam  int Driver_id,
                      @RequestParam int PhoneNum, @RequestParam  int simNum, @RequestParam  boolean CardWithdrawn,
                      @RequestParam  LocalDate dateOfWithdrawal) {
        Drivers d = new Drivers();

            d.setSimNum(simNum);
            d.setDateOfInsertion(LocalDate.now());

      /* if (d.get().getDriver_id() == Driver_id) {
            return "Driver id is already exists"; }
        else if (d.getPhoneNum() == phoneNum) {
            return "phone number is already exists";}
        else  if (d.getSimNum() == SimNum) {
            return "Sim card  number is already exists";
        }

*/            driversRepo.save(d);
        return   "Driver,s info Saved" ;

    }


    @GetMapping("/Driverspagination")
    public JsonNode getUsers(@RequestParam(required = false) String filter, int page, int pagesize) {

        if (filter == null || filter.equals("null")) {
            filter = "";
        }

        Pageable pageable = PageRequest.of(page - 1, pagesize);
        var users = driversRepo.findForPagination(filter, pageable);
        var total = driversRepo.findAllDriers(filter);
        ObjectNode res = objectMapper.createObjectNode();

        res.putPOJO("items", users);

        ObjectNode pagination = res.putPOJO("pagination", new Pagination(total, users.size(), pagesize, page, Math.ceil(total / pagesize)));

        return res;
    }

    @GetMapping (value = "/edit")
    public String showUpdateForm(@PathVariable("Id")  int Id, Model model) {
        Drivers drivers = driversRepo.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + Id));

        model.addAttribute("drivers", drivers);
        return "update-driver";
    }
    @DeleteMapping  (path = "/deleteDriver")
    public String deleteRole(int Id) {
        driversRepo.deleteById(Id);
        return "driver was deleted with id:"+ Id;
    }

   /* @Transactional
    @PutMapping("/UpdateDriver")
    public String updateDriver(Drivers l) {

        driversRepo.updateDriverInfo(l.getId(), l.getDriver_name(),
                l.getDriver_id(), l.getPhoneNum(),
                l.getSimNum(), l.getCardWithdrawn(), l.getDateOfWithdrawal());
        return "Record updated successfully";
    }*/
    @Transactional
    @PutMapping("/UpdateDriver")
    public String updateDriver(int id, int SimNum, Optional<Boolean> cardWithdrawn, LocalDate dateOfWithdrawal, LocalDate dateOfInsertion) {
        Drivers m = new Drivers();
        m.setDateOfInsertion(LocalDate.now());
        Optional<Drivers> l = driversRepo.findById(id);
        if(l.isPresent()){
            l.get().setCardWithdrawn(cardWithdrawn.isEmpty()? false: cardWithdrawn.get());

            l.get().setSimNum(SimNum);
            l.get().setDateOfInsertion(LocalDate.now());
            if (l.get().getCardWithdrawn()  == true)
                l.get().setDateOfWithdrawal(LocalDate.now());
            driversRepo.save(l.get());
            return "Record updated successfully";
        }else{
            return "not found";
        }

    }


}
