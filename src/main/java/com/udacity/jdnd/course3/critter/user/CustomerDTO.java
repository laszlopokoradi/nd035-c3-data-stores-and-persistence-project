package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.*;
import java.util.*;


/**
 * Represents the form that customer request and response data takes. Does not map to the database
 * directly.
 */
public class CustomerDTO {

    private UUID id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<UUID> petIds;

    public static CustomerDTO fromEntity(Customer createdCustomer) {
        CustomerDTO customerDTO =  new CustomerDTO()
                .setId(createdCustomer.getId())
                .setName(createdCustomer.getName())
                .setPhoneNumber(createdCustomer.getPhoneNumber())
                .setNotes(createdCustomer.getNotes());

        for (Pet pet : createdCustomer.getPets()) {
            customerDTO.getPetIds().add(pet.getId());
        }

        return customerDTO;
    }

    public UUID getId() {
        return id;
    }

    public CustomerDTO setId(UUID id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerDTO setName(String name) {
        this.name = name;

        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        return this;
    }

    public String getNotes() {
        return notes;
    }

    public CustomerDTO setNotes(String notes) {
        this.notes = notes;

        return this;
    }

    public List<UUID> getPetIds() {
        return petIds;
    }

    public CustomerDTO setPetIds(List<UUID> petIds) {
        this.petIds = petIds;

        return this;
    }

    protected Customer toEntity() {
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setName(this.name);
        customer.setPhoneNumber(this.phoneNumber);
        customer.setNotes(this.notes);

        for (UUID petId : this.petIds) {
            customer.getPets().add(new Pet().setId(petId));
        }

        return customer;
    }
}
