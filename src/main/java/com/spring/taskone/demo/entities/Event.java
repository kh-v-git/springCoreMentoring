package com.spring.taskone.demo.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Event extends AbstractEntity {
    /**
     * Event id. UNIQUE.
     *
     */
    private LocalDate date;
    private String title;

    public Event() {

    }

    public Event(final LocalDate date, final String title) {
        this.date = date;
        this.title = title;
    }

    public Event(final Long id, final LocalDate date, final String title) {
        this.id = id;
        this.date = date;
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        final Event event = (Event) o;
        return id == event.id && date.equals(event.date) && title.equals(event.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, title);
    }

    @Override
    public String toString() {
        return "Event{" + "date=" + date + ", title='" + title + '\'' + '}';
    }
}
