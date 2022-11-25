package com.karlaru.seccrm.contact;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String codeName;
    private String phone;

    public Contact(String name, String codeName, String phone) {
        this.name = name;
        this.codeName = codeName;
        this.phone = phone;
    }
}
