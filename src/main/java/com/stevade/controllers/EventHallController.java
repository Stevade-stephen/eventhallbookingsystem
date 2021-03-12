package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.EventHall;
import com.stevade.services.EventHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@RequestMapping("/event-halls")
@Controller
public class EventHallController {

    private final EventHallService eventHallService;

    @Autowired
    public EventHallController(EventHallService eventHallService) {
        this.eventHallService = eventHallService;
    }

    @GetMapping("")
    public String viewAllEventHalls(Model model){
        List<EventHall> eventHalls = eventHallService.getAllEventHalls();
        model.addAttribute("eventHalls", eventHalls);
        return "view-event-halls";
    }

    @GetMapping("/view-hall/{id}")
    public String viewHall(Model model, @PathVariable("id") Long id) throws Exception {
        EventHall eventHall = eventHallService.getEventHall(id);
//        model.addAttribute("thisHall", eventHallService.getEventHall(id));
        model.addAttribute("hall", eventHall);
        model.addAttribute( "test", new EventHall());
//        return "view-hall";
        return "view-hall";
    }

    @GetMapping("/add-hall-form")
    public String addEventHall( Model model){
        EventHall eventHall = new EventHall();
        model.addAttribute("eventHall", eventHall);
        return "add-eventHall";
    }

    @PostMapping("/add-hall")
    public String addEventHall(@ModelAttribute("eventHall") EventHall eventHall, HttpSession session){
        AppUser appUser = (AppUser) session.getAttribute("adminAppUser");
        if(appUser.getRoles().equals(AppUserRole.ADMIN)){
            eventHallService.addEventHall(eventHall);
            return "redirect:/event-halls";
        }
        return "redirect:/login";
    }

    @PostMapping("/book-event-hall/{id}")
    public String bookEventHall(@PathVariable(value = "id")long id, HttpSession session, @ModelAttribute("test") EventHall eventHall)  {
       AppUser appUser = (AppUser) session.getAttribute("customerAppUser");
//       eventHall.setDate(date);
       eventHallService.bookEventHall(id,appUser, eventHall.getDate());

        return "redirect:/customer-dashboard";
    }

    @GetMapping("/delete-event-hall/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {
        eventHallService.deleteEventHall(id);
        return "redirect:/event-halls";
    }
}
