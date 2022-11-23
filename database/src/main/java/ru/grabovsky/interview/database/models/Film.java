package ru.grabovsky.interview.database.models;

import java.util.Objects;

public class Film {
    private Long id;
    private String name;
    private int duration;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Film film = (Film) o;
        return id.equals(film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder{
        private Film film;

        public Builder() {
            this.film = new Film();
        }

        public Builder id(final Long id) {
            film.id = id;
            return this;
        }

        public Builder name(final String name) {
            film.name = name;
            return this;
        }

        public Builder duration(final int duration) {
            film.duration = duration;
            return this;
        }

        public Film build(){
            return film;
        }

    }
}
