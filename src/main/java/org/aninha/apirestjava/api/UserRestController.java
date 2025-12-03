package org.aninha.apirestjava.api;

import jakarta.validation.Valid;
import org.aninha.apirestjava.api.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final List<User> usersList = new ArrayList<>();

    @GetMapping("/dummy") // mapping especial
    public User  dummyUser(){
        return new User(UUID.randomUUID(), "Ana", "ana@email.com", LocalDate.now());
    }

    @PostMapping("/create-dummy") // mapping especial
    public User  createUser(){
        User dummy = new User(UUID.randomUUID(), "Rafa", "rafa@email", LocalDate.now());
        return this.createUser();
    }

    @GetMapping
    public List<User> getUsers(){
        return this.usersList;
    }

    @GetMapping("/{uuid}")
    public User getForUuid(@PathVariable String uuid){
        return usersList.stream().filter
                (user -> user.getUuid().equals(uuid)).
                findFirst().orElseThrow( () -> new NotFoundException("Nao foi possivel encontrar o Usuario"));
    }

    @PostMapping("/")
    public User createUser(@RequestBody @Valid User user){
        this.usersList.add(user);
        return user;
    }

    @PostMapping("/create-dummy-2")
    public User createDummmy(){
        User dummy = new User(UUID.randomUUID(), "Dummy", "dummy@email", LocalDate.now());
        return this.createDummmy();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody @Valid User newUser){
        User user = this.getForUuid(String.valueOf(id));
        this.usersList.set(this.usersList.indexOf(user), newUser);
        return newUser;
    }

    @PatchMapping("/{uuid}/update-name")
    public User patchNamer(@PathVariable UUID uuid, @RequestBody User newUser){
        User user = this.getForUuid(String.valueOf(uuid));
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        return newUser;
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable UUID id){
        this.usersList.removeIf(user -> user.getUuid().equals(id));
        return new User();
    }

}
