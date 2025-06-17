<template>
  <div class="container">
    <h1 class="mt-3">PhoneBook</h1>
    <form @submit.prevent="createContact">
      <div class="row">
        <div class="col-lg-5">
          <h2 class="h5">Создать контакт</h2>

          <div class="mb-3">
            <label for="name" class="form-label">Фамилия</label>
            <input v-model.trim="surname"
                   :class="{'is-invalid': isSurnameInvalid}"
                   type="text"
                   class="form-control"
                   id="surname">
            <div class="invalid-feedback">Введите фамилию</div>
          </div>

          <div class="mb-3">
            <label for="name" class="form-label">Имя</label>
            <input v-model.trim="name"
                   :class="{'is-invalid': isNameInvalid}"
                   type="text"
                   class="form-control"
                   id="name">
            <div class="invalid-feedback">Введите имя</div>
          </div>

          <div class="mb-3">
            <label for="phone" class="form-label">Телефон</label>
            <input v-model.trim="phone"
                   :class="{'is-invalid': isPhoneInvalid}"
                   type="text"
                   class="form-control"
                   id="phone">
            <div class="invalid-feedback">Введите номер телефона</div>
          </div>

          <button type="submit" class="btn btn-primary me-3">Создать</button>
          <button @click="clearForm"
                  :class="{'d-none': !isFormCompleted}"
                  v-cloak
                  type="button"
                  class="btn btn-close"></button>
        </div>
      </div>
    </form>

    <h2 class="h5 mt-4">Поиск</h2>

    <form @submit.prevent="getContacts" class="row row-cols-lg-auto g-3 align-items-center">
      <div class="col-lg-4">
        <input v-model="term" type="text" class="form-control">
      </div>
      <div class="col-auto">
        <button type="submit" class="btn btn-primary">Поиск</button>
      </div>
      <div v-cloak :class="{'d-none': isFilterClean}" class="col-auto">
        <button @click="clearFilter" type="button" class="btn btn-close"></button>
      </div>
    </form>

    <div class="mt-3">
      <div v-cloak class="mb-2">
        <button @click="showDeleteSelectedContactsModal"
                :class="{'invisible': isSelectedContactsMissing}"
                type="button"
                class="btn btn-outline-danger border-0">
          Удалить выбранное
        </button>
      </div>

      <div class="table-responsive">
        <table v-cloak class="table table-hover">
          <thead class="table-head table-light">
          <tr>
            <th class="header-detail"></th>
            <th class="header-detail">#</th>
            <th class="header-detail">Фамилия</th>
            <th class="header-detail">Имя</th>
            <th class="header-detail">Телефон</th>
            <th class="header-detail"></th>
          </tr>
          </thead>
          <tbody>
          <tr :class="{'d-none': isContactsExists}">
            <td colspan="6" class="text-center">Контакты отсутствуют.</td>
          </tr>

          <tr v-for="(contact, index) in contacts" :key="contact.id">
            <td>
              <input v-model="selectedContacts"
                     :value=contact
                     class="form-check-input checkbox"
                     type="checkbox">
            </td>
            <td>{{ index + 1 }}</td>
            <td>{{ contact.surname }}</td>
            <td>{{ contact.name }}</td>
            <td>{{ contact.phone }}</td>
            <td>
              <button @click="showEditContactModal(contact)"
                      class="btn btn-sm btn-outline-primary border-0"
                      type="button">
                Редактировать
              </button>

              <button @click="showDeleteContactModal(contact)"
                      class="btn btn-sm btn-outline-danger border-0"
                      type="button">
                Удалить
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <edit-contact-modal @save="editContact"
                        v-cloak
                        ref="editContactModal">
    </edit-contact-modal>

    <delete-contact-modal @delete="deleteContact"
                          v-cloak
                          ref="deleteContactModal">
    </delete-contact-modal>

    <delete-selected-contacts-modal @delete="deleteSelectedContacts"
                                    v-cloak
                                    ref="deleteSelectedContactsModal">
    </delete-selected-contacts-modal>
  </div>
</template>

<script>
import PhoneBookService from "../js/phoneBookService";

import {isContactInvalid} from "../js/validation";
import {clearFormInputFields, clearValidatingFields} from "../js/clearingFields";

export default {
  data() {
    return {
      contacts: [],
      selectedContacts: [],
      surname: "",
      name: "",
      phone: "",
      isSurnameInvalid: false,
      isNameInvalid: false,
      isPhoneInvalid: false,
      term: "",
      appliedTerm: "",
      service: new PhoneBookService()
    };
  },

  created() {
    this.getContacts();
  },

  computed: {
    isContactsExists() {
      return this.contacts.length !== 0;
    },

    isSelectedContactsMissing() {
      return this.selectedContacts.length === 0;
    },

    isFormCompleted() {
      return this.surname !== "" || this.name !== "" || this.phone !== ""
          || this.isSurnameInvalid || this.isNameInvalid || this.isPhoneInvalid;
    },

    isFilterClean() {
      return this.appliedTerm.length === 0 && this.term.length === 0;
    }
  },

  methods: {
    getContacts() {
      this.selectedContacts = [];

      this.service.getContacts(this.term)
          .then(contacts => this.contacts = contacts)
          .catch(() => alert("Не удалось загрузить контакты."));

      this.appliedTerm = this.term;
    },

    clearForm() {
      clearFormInputFields(this);
      clearValidatingFields(this);
    },

    clearFilter() {
      this.term = "";
      this.getContacts();
    },

    createContact() {
      const contactInfo = {
        surname: this.surname,
        name: this.name,
        phone: this.phone
      };

      if (isContactInvalid(this, contactInfo)) {
        return;
      }

      this.service.createContact(contactInfo)
          .then(response => {
            if (!response.success) {
              alert(response.message);
              return;
            }

            clearFormInputFields(this)
            this.getContacts();
          })
          .catch((err) => alert(`
            Не удалось создать контакт.
            Исходная причина: "${err.response.data.message}"
          `));
    },

    showEditContactModal(contact) {
      this.$refs.editContactModal.show(contact);
    },

    showDeleteContactModal(contact) {
      this.$refs.deleteContactModal.show(contact);
    },

    showDeleteSelectedContactsModal() {
      this.$refs.deleteSelectedContactsModal.show(this.selectedContacts.length);
    },

    editContact(contact) {
      this.service.editContact(contact)
          .then(response => {
            if (!response.success) {
              alert(response.message);
              return;
            }

            this.$refs.editContactModal.hide();
            this.getContacts();
          })
          .catch((err) => alert(`
              Не удалось обновить контакт с id = ${contact.id}.
              Исходная причина: "${err.response.data.message}"
            `)
          );
    },

    deleteContact(contact) {
      this.service.deleteContact(contact.id)
          .then(response => {
            if (!response.success) {
              alert(response.message);
              return;
            }

            this.getContacts();
          })
          .catch((err) => alert(`
              Не удалось удалить контакт: ${contact.surname} ${contact.name}
              Номер: ${contact.phone}

              Исходная причина: "${err.response.data.message}"
          `));
    },

    deleteSelectedContacts() {
      this.selectedContacts.forEach(contact => this.deleteContact(contact));
      this.selectedContacts = [];
    }
  }
}
</script>