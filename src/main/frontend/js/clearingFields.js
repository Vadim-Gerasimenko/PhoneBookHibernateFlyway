export function clearValidatingFields(component) {
    component.isSurnameInvalid = false;
    component.isNameInvalid = false;
    component.isPhoneInvalid = false;
}

export function clearFormInputFields(component) {
    component.surname = "";
    component.name = "";
    component.phone = "";
}