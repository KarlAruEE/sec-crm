package com.karlaru.seccrm;

import com.karlaru.seccrm.contact.Contact;
import com.karlaru.seccrm.contact.ContactController;
import com.karlaru.seccrm.contact.ContactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ControllerLayerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactRepository contactRepository;

    @Test
    void shouldReturnContactsBySearch(){

        when(contactRepository.findAll()).thenReturn(List.of(new Contact(), new Contact(), new Contact()));

        assertThat(contactController.getContacts()).hasSize(3);
        verify(contactRepository, times(1)).findAll();
        verifyNoMoreInteractions(contactRepository);
    }

    @Test
    void shouldReturnContactsList(){

        final Contact searchedContact = new Contact("Mathew McConaughey", "mcm","665896");
        when(contactRepository.findAnyLikeSearch("mathew")).thenReturn(List.of(searchedContact));

        ResponseEntity<Iterable<Contact>> response = contactController.searchContacts("mathew");

        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(List.of(searchedContact));
        verify(contactRepository, times(1)).findAnyLikeSearch("mathew");
        verifyNoMoreInteractions(contactRepository);
    }

    @Test
    void shouldAddContact(){
        final Contact contactToSave = new Contact("Karl", "aru","5334123");
        when(contactRepository.save(any(Contact.class))).thenReturn(contactToSave);

        Contact response = contactController.postContact(new Contact());

        assertThat(response).usingRecursiveComparison().isEqualTo(contactToSave);
        verify(contactRepository, times(1)).save(any(Contact.class));
        verifyNoMoreInteractions(contactRepository);
    }

}
