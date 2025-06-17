import axios from "axios";

export default class PhoneBookService {
    constructor() {
        this.baseUrl = "/api/contacts";
    }

    get(url, params) {
        return axios.get(url, {
            params
        }).then(response => response.data);
    }

    post(url, data) {
        return axios.post(url, data).then(response => response.data);
    }
    put(url, data) {
        return axios.put(url, data).then(response => response.data);
    }

    delete(url, data) {
        return axios.delete(url, data).then(response => response.data);
    }

    getContacts(term) {
        return this.get(this.baseUrl, {term});
    }

    createContact(contactInfo) {
        return this.post(this.baseUrl, contactInfo);
    }

    editContact(contact) {
        return this.put(`${this.baseUrl}/${contact.id}`, contact);
    }

    deleteContact(id) {
        return this.delete(`${this.baseUrl}/${id}`);
    }
}