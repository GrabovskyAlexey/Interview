package ru.grabovsky.interview.database.models;

import java.time.LocalTime;

public class Session {
    private Long id;
    private LocalTime startTime;
    private int cost;
    private Film film;
    private int ticketCount;

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(final int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(final int cost) {
        this.cost = cost;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(final Film film) {
        this.film = film;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Session session;

        public Builder() {
            this.session = new Session();
        }

        public Builder id(final Long id) {
            session.id = id;
            return this;
        }

        public Builder startTime(final LocalTime startTime) {
            session.startTime = startTime;
            return this;
        }

        public Builder cost(final int cost) {
            session.cost = cost;
            return this;
        }

        public Builder film(final Film film) {
            session.film = film;
            return this;
        }

        public Builder ticketCount(final int ticketCount) {
            session.ticketCount = ticketCount;
            return this;
        }

        public Session build(){
            return session;
        }
    }
}
