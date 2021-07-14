package pl.kul.todos.adapter.gui.mainwindow;

import pl.kul.todos.adapter.gui.editperson.UpdatePersonDto;

import java.util.*;
import java.util.stream.Collectors;

public class Logged {
    private final Map<UUID, PersonDto> persons;

    public Logged (List<PersonDto> persons) {
        this.persons = persons.stream()
                .collect(
                        () -> new LinkedHashMap<UUID, PersonDto>(),
                        (map, personDto) -> map.put(personDto.getId(), personDto),
                        (map1, map2) -> map1.putAll(map2)
                );
    }

    public List<PersonDto> getPersons() {
        return persons.values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public void updatePerson(UUID id, UpdatePersonDto updated) {
        PersonDto personToUpdate = Optional.ofNullable(persons.get(id))
                .orElseThrow(() -> new IllegalStateException(String.format("There is no Person with id of %s", id)));

        PersonDto updatedPerson = new PersonDto(personToUpdate.getId(), updated.getName(), updated.getLastName(), personToUpdate.isLogged());

        persons.put(id, updatedPerson);
    }

    public void updatePersonAndStatus(UUID id, UpdatePersonDto updated) {
        PersonDto personToUpdate = Optional.ofNullable(persons.get(id))
                .orElseThrow(() -> new IllegalStateException(String.format("There is no Person with id of %s", id)));

        PersonDto updatedPerson = new PersonDto(personToUpdate.getId(), updated.getName(), updated.getLastName(), updated.isLogged());

        persons.put(id, updatedPerson);
    }

    public void markAsOnline(UUID id) {
        PersonDto personToUpdate = Optional.ofNullable(persons.get(id))
                .orElseThrow(() -> new IllegalStateException(String.format("There is no Person with id of %s", id)));

        PersonDto newPerson = new PersonDto(personToUpdate.getId(), personToUpdate.getName(), personToUpdate.getLastName(), true);

        persons.put(id, newPerson);
    }

    public void markAsOffline(UUID id) {
        PersonDto personToUpdate = Optional.ofNullable(persons.get(id))
                .orElseThrow(() -> new IllegalStateException(String.format("There is no Person with id of %s", id)));

        PersonDto newPerson = new PersonDto(personToUpdate.getId(), personToUpdate.getName(), personToUpdate.getLastName(), false);

        persons.put(id, newPerson);
    }

    public PersonDto getPerson(UUID id) {
        return Optional.ofNullable(persons.get(id))
                .orElseThrow(() -> new IllegalStateException(String.format("There is no Person with id of %s", id)));
    }

}