<template>
  <div ref="modal" class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false"
       tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5">Подтвердите удаление</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <div>
            <p>
              Вы уверены, что хотите удалить следующий контакт?
            </p>

            <div>
              <span class="fw-semibold me-2 mb-2">Данные контакта:</span>
              <span class="me-1">{{ surname }}</span>
              <span>{{ name }}</span>
            </div>

            <div>
              <span class="fw-semibold me-2">Номер телефона:</span>
              <span>{{ phone }}</span>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button @click="deleteContact"
                  type="button"
                  class="btn btn-danger btn-sm">
            Удалить
          </button>

          <button @click="hide"
                  type="button"
                  class="btn btn-secondary btn-sm">
            Отмена
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      instance: null,
      id: 0,
      surname: "",
      name: "",
      phone: ""
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
      this.instance.hide();
    },

    deleteContact() {
      const contact = {
        id: this.id,
        surname: this.surname,
        name: this.name,
        phone: this.phone
      }

      this.$emit("delete", contact);
      this.hide();
    }
  }
}
</script>