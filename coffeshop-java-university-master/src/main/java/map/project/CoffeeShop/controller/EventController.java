package map.project.CoffeeShop.controller;

import map.project.CoffeeShop.data.model.Event;
import map.project.CoffeeShop.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public Event create(@RequestBody Event event) {
        return eventService.save(event);
    }

    @PutMapping("/setLocation/{eventId}/{locationId}")
    public Event setLocation(@PathVariable("eventId") int eventId, @PathVariable("locationId") int locationId) {
        return eventService.setLocation(eventId, locationId);
    }

    @GetMapping("/all")
    public List<Event> getAll() {
        return eventService.getAll();
    }

    @DeleteMapping("delete/{eventId}")
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventService.delete(eventId);
    }

    @GetMapping("/{eventId}")
    public Event getEvent(@PathVariable("eventId") int eventId) {
        return eventService.findById(eventId);
    }
}