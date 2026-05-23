export class IfaInputTextRequestModel {
  constructor(obj) {
    this.screenId = obj.screenId ?? ''
    this.fieldId = obj.fieldId ?? ''
    this.value = obj.value ?? ''
  }
}
