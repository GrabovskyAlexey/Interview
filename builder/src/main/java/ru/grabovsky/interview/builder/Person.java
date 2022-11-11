package ru.grabovsky.interview.builder;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public static Builder builder(){
        return new Builder();
    }

    static class Builder{
        private Person person;

        public Builder firstName(final String firstName) {
            person.firstName = firstName;
            return this;
        }

        public Builder lastName(final String lastName) {
            person.lastName = lastName;
            return this;
        }

        public Builder middleName(final String middleName) {
            person.middleName = middleName;
            return this;
        }

        public Builder country(final String country) {
            person.country = country;
            return this;
        }


        public Builder address(final String address) {
            person.address = address;
            return this;
        }


        public Builder phone(final String phone) {
            person.phone = phone;
            return this;
        }


        public Builder age(final int age) {
            person.age = age;
            return this;
        }

        public Builder gender(final String gender) {
            person.gender = gender;
            return this;
        }

        public Person build(){
            return person;
        }
    }
}
