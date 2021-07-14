package pl.kul.todos.adapter.gui.persondetails;

class PersonDetailsDto {
    private final String name;
    private final String surname;
    private final String status;

    public PersonDetailsDto(String name, String surname, String status) {
        this.name = name;
        this.surname = surname;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getStatus() {
        return status;
    }
}