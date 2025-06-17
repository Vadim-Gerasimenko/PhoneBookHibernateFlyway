import "bootstrap/dist/css/bootstrap.css"
import "bootstrap/dist/js/bootstrap.bundle";
import "../css/style.scss";

import {createApp} from "vue";

window.bootstrap = require("bootstrap");

import PhoneBook from "../components/PhoneBook.vue";

import EditContactModal from "../components/EditContactModal.vue";
import DeleteContactModal from "../components/DeleteContactModal.vue";
import DeleteSelectedContactsModal from "../components/DeleteSelectedContactsModal.vue";

const app = createApp(PhoneBook);

app.component("EditContactModal", EditContactModal);
app.component("DeleteContactModal", DeleteContactModal);
app.component("DeleteSelectedContactsModal", DeleteSelectedContactsModal);

app.mount("#app");