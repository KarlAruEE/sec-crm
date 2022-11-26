package com.karlaru.seccrm.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String codeName;
    private String phone;

    public Contact(String name, String codeName, String phone) {
        this(null, name, codeName, phone);
    }

}
