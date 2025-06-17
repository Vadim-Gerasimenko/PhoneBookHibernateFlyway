<template>
  <div ref="modal" class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false"
       tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5">Изменение контактных данных</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <form @submit.prevent="editContact">
          <div class="modal-body">
            <div class="input-block-min-height mb-2">
              <label for="name-edit-input-field">Фамилия</label>
              <input v-model.trim="surname"
                     :class="{'is-invalid': isSurnameInvalid}"
                     id="name-edit-input-field"
                     type="text"
                     class="form-control">
              <div class="invalid-feedback">Введите фамилию</div>
            </div>

            <div class="input-block-min-height mb-2">
              <label for="surname-edit-input-field">Имя</label>
              <input v-model.trim="name"
                     :class="{'is-invalid': isNameInvalid}"
                     id="surname-edit-input-field"
                     type="text"
                     class="form-control">
              <div class="invalid-feedback">Введите имя</div>
            </div>

            <div class="input-block-min-height mb-2">
              <label for="phone-edit-input-field">Номер телефона</label>
              <input v-model.trim="phone"
                     :class="{'is-invalid': isPhoneInvalid}"
                     id="phone-edit-input-field"
                     type="text"
                     class="form-control text-truncate">
              <div class="invalid-feedback">Введите номер телефона</div>
            </div>
          </div>

          <div class="modal-footer">
            <button type="submit" class="btn btn-primary btn-sm">
              Сохранить
            </button>

            <button @click="hide"
                    type="button"
                    class="btn btn-secondary btn-sm">
              Отмена
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import {isContactInvalid} from "../js/validation";
import {clearValidatingFields} from "../js/clearingFields";

export default {
  data() {
    return {
      instance: null,
      id: 0,
      surname: "",
      name: "",
      phone: "",
      isSurnameInvalid: false,
      isNameInvalid: false,
      isPhoneInvalid: false
    };
  },

  mounted() {
    this.instance = new bootstrap.Modal(this.$refs.modal);
  },

  methods: {
    show(contact) {
      this.id = contact.id;

      this.surname = contact.surname;
      this.name = contact.name;
      this.phone = contact.phone;

      this.instance.show();
    },

    hide() {
      clearValidatingFields(this);
      this.instance.hide();
    },

    editContact() {
      const contact = {
        id: this.id,
        surname: this.surname,
        name: this.name,
        phone: this.phone
      }

      if (isContactInvalid(this, contact)) {
        return;
      }

      this.$emit("save", contact);
    }
  }
}
</script>