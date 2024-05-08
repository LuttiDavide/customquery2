package co.develhope.customquery2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/flights")
    public List<Flight> provisionFlights(@RequestParam(required = false, defaultValue = "100") int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            Flight flight = new Flight();
            flight.setDescription("Flight " + i);
            flight.setFromAirport(generateRandomString());
            flight.setToAirport(generateRandomString());
            flight.setStatus(Status.values()[random.nextInt(Status.values().length)]);
            flightRepository.save(flight);
        }
        return flightRepository.findAll();
    }

    @GetMapping("/flights/pagination")
    public Page<Flight> getFlightsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fromAirport").ascending());
        return flightRepository.findAll(pageable);
    }

    @GetMapping("/flights/ontime")
    public List<Flight> getOnTimeFlights() {
        return flightRepository.findByStatus(Status.ON_TIME);
    }

    @GetMapping("/flights/status")
    public List<Flight> getFlightsByStatus(@RequestParam("status1") Status status1, @RequestParam("status2") Status status2) {
        List<Status> statusList = Arrays.asList(status1, status2);
        return flightRepository.findByStatusIn(statusList);
    }

    private String generateRandomString() {
        return "RandomString";
    }
}
