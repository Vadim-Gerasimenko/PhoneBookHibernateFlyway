package ru.academits.gerasimenko.phonebookhibernate.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.academits.gerasimenko.phonebookhibernate.dao.ContactRepository;


public class ContactServiceImplTest {
    @Mock
    private ContactRepository mockContactRepository;
    @InjectMocks
    private ContactService contactService;

    @Test
    public void getContacts_PassNull_CallFindAll() {
        contactService.getContacts(null);
        Mockito.verify(mockContactRepository).findAll();
    }

    @Test
    public void getContacts_PassEmptyTerm_CallFindAll() {
        contactService.getContacts("");
        Mockito.verify(mockContactRepository).findAll();
    }

    @Test
    public void getContacts_PassCorrectTerm_CallGetByNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrPhoneContainsIgnoreCase() {
        String term = "term";

        contactService.getContacts(term);

        Mockito.verify(mockContactRepository).getByNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrPhoneContainsIgnoreCase(term, term, term);
    }
}