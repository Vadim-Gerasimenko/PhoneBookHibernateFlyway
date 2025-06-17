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
            <span>Количество выбранных контактов: </span>
            <span class="fw-semibold">{{ selectedContactsCount }}</span>
            <p>
              Вы уверены, что хотите их удалить?
            </p>
          </div>

          <div class="modal-footer">
            <button @click="deleteSelectedContacts"
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      instance: null,
      selectedContactsCount: 0
    };
  },

  mounted() {
    this.instance = new bootstrap.Modal(this.$refs.modal);
  },

  methods: {
    show(selectedContactsCount) {
      this.selectedContactsCount = selectedContactsCount;
      this.instance.show();
    },

    hide() {
      this.instance.hide();
    },

    deleteSelectedContacts() {
      this.$emit("delete");
      this.hide();
    }
  }
}
</script>