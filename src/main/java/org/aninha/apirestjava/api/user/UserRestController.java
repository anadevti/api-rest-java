package org.aninha.apirestjava.api.user;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.aninha.apirestjava.api.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserJpaRepository jpaRepository;

    public UserRestController(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

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
        return this.jpaRepository.findAll();
    }

    @GetMapping("/{uuid}")
    public User getForUuid(@PathVariable UUID uuid){
        return this.jpaRepository.findByUuid(uuid)
               .orElseThrow( () -> new NotFoundException("Nao foi possivel encontrar o Usuario"));
    }

    @PostMapping("/")
    public User createUser(@RequestBody @Valid User user){
        return this.jpaRepository.save(user);
    }

    @PostMapping("/create-dummy-2")
    public User createDummmy(){
        User dummy = new User(UUID.randomUUID(), "Dummy", "dummy@email", LocalDate.now());
        return this.createDummmy();
    }

    @PutMapping("/{uuid}")
    public User updateUser(@PathVariable UUID uuid, @RequestBody @Valid User newUser){
        Optional<User> user = this.jpaRepository.findByUuid(uuid);
        newUser.setId(user.get().getId());
        return this.jpaRepository.save(newUser);

    }

    @Transactional
    @PatchMapping("/{uuid}/update-name")
    public User patchNamer(@PathVariable UUID uuid, @RequestBody User newUser){
        this.jpaRepository.updateName(uuid, newUser.getName());
        return this.jpaRepository.save(newUser);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void deleteUser(@PathVariable UUID uuid){
        this.jpaRepository.deleteByUuid(uuid);
    }

}
