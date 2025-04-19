export default class RegexValidator {
    static emailRegex = /^[\w\d.-]+@\w+(\.\w+)+$/;
    static valueRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñäëïöüÄËÏÖÜ ]+$/;
    static passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@$!%*?&]{6,}$/;
  
    static isEmail(email) {
      return this.emailRegex.test(email);
    }
  
    static isPassword(password) {
      return this.passwordRegex.test(password);
    }
  
    static isValidName(name) {
      return this.valueRegex.test(name);
    }
  
    static validLength(value) {
      return value.length >= 6;
    }
    
    static isEmpty(value) {
      return value.trim() === '';
    }

  }
  