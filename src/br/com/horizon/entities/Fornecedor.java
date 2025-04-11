package br.com.horizon.entities;

public class Fornecedor {

    private int id;
    private String name;
    private String description;
    private String cnpj;
    private String phone;
    private String email;

    public Fornecedor(int id, String name, String description, String cnpj, String phone, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cnpj = cnpj;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
