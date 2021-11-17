package com.firstpro.demo.scheduledTask;

import com.firstpro.demo.Rebo.driversRepo;
import com.firstpro.demo.model.Drivers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

@Component
public class ScheduledTask {
    @Autowired
    driversRepo driversRepo;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 86400000)
    public void reportCurrentTime() {

        Iterable<Drivers> drivers = driversRepo.findAll();
        LocalDate current_date = LocalDate.now();
        //System.out.println("task");
        for (Drivers driver : drivers) {

            driver.setLastWithdrawal(driver.getDateOfInsertion().plusMonths(1));
           // System.out.println("task"+ driver.getLastWithdrawal());
           // Period period = Period.between(current_date, driver.getDateOfInsertion());
            Period period2 = Period.between(current_date, driver.getLastWithdrawal());
            //System.out.println("task"+period2);
           if ((period2.getDays() == 0)){
               driver.setLastWithdrawal(LocalDate.now());
            //System.out.println("task"+driver.getLastWithdrawal());
               driversRepo.save(driver);
               if (driver.getLastWithdrawal().equals(current_date) && driver.getCardWithdrawn() == false ) {
                   System.out.println("task");
               }

           }
                  }
              }
        }



