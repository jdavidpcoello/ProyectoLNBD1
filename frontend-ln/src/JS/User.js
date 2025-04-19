export default class User {
    constructor(email,password,firstName,lastName,country,city,job,typeJob,placeJob,schoolName,firstYear,lastYear,birthDay,birthMonth,birthYear,profilePhoto) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.job = job;
        this.typeJob = typeJob;
        this.placeJob = placeJob;
        this.schoolName = schoolName;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.profilePhoto = profilePhoto;
    }
}