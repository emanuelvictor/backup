package com.emanuelvictor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class ApplicationTests {

<<<<<<< HEAD
//    @Test(expected = RuntimeException.class)

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        new Handler(receiver);

        for (int i = 0; i < 10; i++) {
            receiver.receive(i);
        }
    }

    public static class Handler {
        private final Receiver receiver;

        public Handler(Receiver receiver) {
            this.receiver = receiver;
            initHandler();
        }

        public void initHandler() {
            Flux.from
            Flux.from(receiver)
                    .flatMap(Mono::just)
                    .log()
                    .map(this::throwException)
                    .subscribe();
        }

        public Mono<Void> throwException(Integer i) {
            System.out.println(i);
            throw new RuntimeException("Exception");
        }
    }

    public static class Receiver implements Publisher<Integer> {
        private Subscriber<? super Integer> subscriber;

        public void receive(Integer input) {
            subscriber.onNext(input);
        }

        @Override
        public void subscribe(Subscriber<? super Integer> s) {
            this.subscriber = s;
        }
=======

    @Test
    public void main() {
>>>>>>> 57a6ca0d672d14727c2f03b2f8f2d1ac005a203d
    }
}