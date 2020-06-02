package com.rahaman.asif.support;

import com.rahaman.asif.model.Car;
import com.rahaman.asif.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DataInitiator implements CommandLineRunner {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    @Override
    public void run(String... args) throws Exception {
        Flux<Car> cars = Flux.just(
                new Car(null, "Model-1", "Category-1"),
                new Car(null, "Model-2", "Category-1"),
                new Car(null, "Model-3", "Category-2"),
                new Car(null, "Model-4", "Category-2"),
                new Car(null, "Model-5", "Category-3"),
                new Car(null, "Model-6", "Category-3"),
                new Car(null, "Model-7", "Category-4"),
                new Car(null, "Model-8", "Category-4"),
                new Car(null, "Model-9", "Category-5"),
                new Car(null, "Model-10", "Category-5"));

        reactiveMongoOperations.collectionExists(Car.class)
                .flatMap(it -> {
                    if (it) {
                        return reactiveMongoOperations.dropCollection(Car.class);
                    } else {
                        return Mono.empty();
                    }
                })
                .thenMany(reactiveMongoOperations.createCollection(Car.class))
                .thenMany(cars.flatMap(it -> carRepository.save(it)))
                .thenMany(carRepository.findAll())
                .log()
                .subscribe(
                        it -> System.out.println(it),
                        error -> System.out.println(error),
                        () -> System.out.println(" -- Database has been initialized"));

    }
}
