package map.project.CoffeeShop.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import map.project.CoffeeShop.data.model.Event;
import map.project.CoffeeShop.data.model.Location;
import map.project.CoffeeShop.data.repository.EventDBRepo;
import map.project.CoffeeShop.data.repository.LocationDBRepo;
import map.project.CoffeeShop.util.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class EventService {

    @Autowired
    private EventDBRepo eventRepo;

    @Autowired
    private LocationDBRepo locationRepo;

    public Event save(Event event) {

        if (!Validators.validateName(event.getName())) {
            log.error("Invalid name");
            throw new IllegalArgumentException("Invalid name");
        }

        return eventRepo.save(event);
    }

    public List<Event> getAll() {
        return eventRepo.findAll();
    }

    public Event findById(int id) {
        return eventRepo.findById((long) id).orElse(null);
    }

    public Event setLocation(int eventId, int locationId) {
        // find the location and the event
        Location location = locationRepo.findById((long) locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        Event event = eventRepo.findById((long) eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Assign the Employee to the Location
        event.setLocation(location);

        return eventRepo.save(event);
    }

    public void delete(int id) {
        if (eventRepo.existsById((long) id)) {
            Event event = eventRepo.findById((long) id)
                    .orElseThrow(() -> new IllegalArgumentException("Event not found"));

            eventRepo.delete(event);
        } else throw new IllegalArgumentException("Event doesnt exist");
    }
}
