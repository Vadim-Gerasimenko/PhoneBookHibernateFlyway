import {clearValidatingFields} from "./clearingFields";

export function isContactInvalid(component, contact) {
    clearValidatingFields(component);

    if (contact.surname.length === 0) {
        component.isSurnameInvalid = true;
    }

    if (contact.name.length === 0) {
        component.isNameInvalid = true;
    }

    if (contact.phone.length === 0) {
        component.isPhoneInvalid = true;
    }

    return component.isSurnameInvalid || component.isNameInvalid || component.isPhoneInvalid;
}