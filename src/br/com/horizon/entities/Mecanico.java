package br.com.horizon.entities;

public class Mecanico {

    private int id;
    private int idOficina;
    private String name;
    private String cpf;
    private int age;

    public Mecanico(int id, int idOficina, String name, String cpf, int age) {
        this.id = id;
        this.idOficina = idOficina;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
