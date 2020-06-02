package com.rahaman.asif.controller;

import com.rahaman.asif.model.Car;
import com.rahaman.asif.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/cars")
public class CarController {

/*    private final WebClient carsClient = WebClient.create("http://localhost:8081");

    private final WebClient bookClient = WebClient.create("http://localhost:8082");*/

    @Autowired
    private CarRepository carRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Car> getCars(){
        return carRepository.findAll().publishOn(Schedulers.parallel());
    }

    @GetMapping("/{id}")
    public Mono<Car> getProduct(@PathVariable("id") String id) {
        return carRepository.findById(id);
    }

 /*   @PostMapping("/booking")
    public Mono<ResponseEntity<Void>> book() {
        logger.debug("Processing booking request..");
        return carsClient.get().uri("/cars")
                .retrieve()
                .bodyToFlux(Car.class)
                .doOnNext(car -> logger.debug("Trying to book " + car))
                .take(5)
                .flatMap(this::requestCar)
                .next()
                .doOnNext(car -> logger.debug("Booked car " + car));
    }

    private Mono<ResponseEntity<Void>> requestCar(Car car) {
        return bookClient.post()
                .uri("/cars/{id}/booking", car.getId())
                .exchange()
                .flatMap(response -> response.toEntity(Void.class));
    }*/

}