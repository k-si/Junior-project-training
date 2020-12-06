package com.ch.pojo;

public class People {
    private String name;
    private Cat cat;
    private Dog dog;

    public People() {
    }

    public People(String name, Cat cat, Dog dog) {
        this.name = name;
        this.cat = cat;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}
