package com.karlaru.seccrm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
