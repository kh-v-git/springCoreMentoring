package com.spring.taskone.demo.facade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.spring.taskone.demo.entities.Event;
import com.spring.taskone.demo.entities.Ticket;
import com.spring.taskone.demo.entities.User;
import com.spring.taskone.demo.service.event.EventService;
import com.spring.taskone.demo.service.ticket.TicketService;
import com.spring.taskone.demo.service.user.UserService;
import com.spring.taskone.demo.utils.TicketCategoryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingFacadeImplTest {
    private static final long EVENT_ID = 10L;
    private static final long USER_ID = 20L;
    private static final long TICKET_ID = 30L;
    private static final int PAGE_SIZE = 16;
    private static final int PAGE_NUMBER = 32;
    private static final int PLACE_NUMBER = 128;
    private static final String EVENT_TITLE = "Test Title";
    private static final String USER_EMAIL = "jhon_macklein@die.hard";
    private static final String USER_NAME = "John Macklein";
    private static final LocalDate EVENT_DATE = LocalDate.of(2023, 10, 12);
    private static final TicketCategoryEnum TICKET_CATEGORY = TicketCategoryEnum.PREMIUM;

    @Mock
    private Event event;

    @Mock
    private Ticket ticket;

    @Mock
    private User user;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingFacadeImpl testingInstance;

    @Test
    void shouldGetEventById() {
        when(eventService.getEventById(EVENT_ID)).thenReturn(Optional.of(event));
        when(event.getId()).thenReturn(EVENT_ID);

        Event result = testingInstance.getEventById(EVENT_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(EVENT_ID));
    }

    @Test
    void shouldGetEventsByTitle() {
        List<Event> eventList = Lists.newArrayList(event);
        when(event.getTitle()).thenReturn(EVENT_TITLE);
        when(eventService.getEventsByTitle(EVENT_TITLE, PAGE_SIZE, PAGE_NUMBER)).thenReturn(eventList);

        List<Event> result = testingInstance.getEventsByTitle(EVENT_TITLE, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.stream().anyMatch(event -> event.getTitle().equalsIgnoreCase(EVENT_TITLE)), is(Boolean.TRUE));
        assertThat(result, contains(event));
    }

    @Test
    void shouldGetEventsForDay() {
        List<Event> eventList = Lists.newArrayList(event);
        when(event.getDate()).thenReturn(EVENT_DATE);
        when(eventService.getEventsForDay(EVENT_DATE, PAGE_SIZE, PAGE_NUMBER)).thenReturn(eventList);

        List<Event> result = testingInstance.getEventsForDay(EVENT_DATE, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.stream().anyMatch(event -> event.getDate().isEqual(EVENT_DATE)), is(Boolean.TRUE));
    }

    @Test
    void shouldCreateEvent() {
        when(eventService.createEvent(event)).thenReturn(event);
        when(event.getId()).thenReturn(EVENT_ID);
        when(event.getTitle()).thenReturn(EVENT_TITLE);
        when(event.getDate()).thenReturn(EVENT_DATE);

        Event result = testingInstance.createEvent(event);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(EVENT_ID));
        assertThat(result.getTitle(), is(EVENT_TITLE));
        assertThat(result.getDate(), is(EVENT_DATE));
    }

    @Test
    void shouldUpdateEvent() {
        when(eventService.updateEvent(event)).thenReturn(event);
        when(event.getId()).thenReturn(EVENT_ID);
        when(event.getDate()).thenReturn(EVENT_DATE);
        when(event.getTitle()).thenReturn(EVENT_TITLE);

        Event result = testingInstance.updateEvent(event);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(EVENT_ID));
        assertThat(result.getTitle(), is(EVENT_TITLE));
        assertThat(result.getDate(), is(EVENT_DATE));
    }

    @Test
    void shouldDeleteEvent() {
        when(eventService.deleteEvent(EVENT_ID)).thenReturn(Boolean.TRUE);

        boolean result = testingInstance.deleteEvent(EVENT_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void shouldGetUserById() {
        when(userService.getUserById(USER_ID)).thenReturn(Optional.of(user));
        when(user.getId()).thenReturn(USER_ID);

        User result = testingInstance.getUserById(USER_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(USER_ID));
    }

    @Test
    void shouldGetUserByEmail() {
        when(userService.getUserByEmail(USER_EMAIL)).thenReturn(Optional.of(user));
        when(user.getEmail()).thenReturn(USER_EMAIL);

        User result = testingInstance.getUserByEmail(USER_EMAIL);

        assertThat(result, is(notNullValue()));
        assertThat(result.getEmail(), is(USER_EMAIL));
    }

    @Test
    void shouldGetUsersByName() {
        List<User> userList = Lists.newArrayList(user);
        when(user.getName()).thenReturn(USER_NAME);
        when(userService.getUsersByName(USER_NAME, PAGE_SIZE, PAGE_NUMBER)).thenReturn(userList);

        List<User> result = testingInstance.getUsersByName(USER_NAME, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.stream().anyMatch(user -> user.getName().equalsIgnoreCase(USER_NAME)), is(Boolean.TRUE));
    }

    @Test
    void shouldCreateUser() {
        when(userService.createUser(user)).thenReturn(user);
        when(user.getName()).thenReturn(USER_NAME);
        when(user.getEmail()).thenReturn(USER_EMAIL);

        User result = testingInstance.createUser(user);

        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is(USER_NAME));
        assertThat(result.getEmail(), is(USER_EMAIL));
    }

    @Test
    void shouldUpdateUser() {
        when(userService.updateUser(user)).thenReturn(user);
        when(user.getId()).thenReturn(USER_ID);
        when(user.getName()).thenReturn(USER_NAME);
        when(user.getEmail()).thenReturn(USER_EMAIL);

        User result = testingInstance.updateUser(user);

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(USER_ID));
        assertThat(result.getName(), is(USER_NAME));
        assertThat(result.getEmail(), is(USER_EMAIL));
    }

    @Test
    void shouldDeleteUser() {
        when(userService.deleteUser(USER_ID)).thenReturn(Boolean.TRUE);

        boolean result = testingInstance.deleteUser(USER_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void shouldBookTicket() {
        when(ticketService.bookTicket(any(Ticket.class))).thenReturn(ticket);
        when(ticket.getUserId()).thenReturn(USER_ID);
        when(ticket.getEventId()).thenReturn(EVENT_ID);
        when(ticket.getPlaceNumber()).thenReturn(PLACE_NUMBER);
        when(ticket.getTicketCategory()).thenReturn(TICKET_CATEGORY);

        Ticket result = testingInstance.bookTicket(USER_ID, EVENT_ID, PLACE_NUMBER, TICKET_CATEGORY);

        assertThat(result, is(notNullValue()));
        assertThat(result.getUserId(), is(USER_ID));
        assertThat(result.getEventId(), is(EVENT_ID));
        assertThat(result.getPlaceNumber(), is(PLACE_NUMBER));
        assertThat(result.getTicketCategory(), is(TICKET_CATEGORY));
    }

    @Test
    void shouldGetBookedTicketsByUser() {
        List<Ticket> ticketList = Lists.newArrayList(ticket);
        when(ticket.getUserId()).thenReturn(USER_ID);
        when(ticketService.getBookedTickets(user, PAGE_SIZE, PAGE_NUMBER)).thenReturn(ticketList);

        List<Ticket> result = testingInstance.getBookedTickets(user, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.stream().anyMatch(ticket -> ticket.getUserId() == USER_ID), is(Boolean.TRUE));
    }

    @Test
    void shouldTestGetBookedTickets() {
        List<Ticket> ticketList = Lists.newArrayList(ticket);
        when(ticket.getEventId()).thenReturn(EVENT_ID);
        when(ticketService.getBookedTickets(event, PAGE_SIZE, PAGE_NUMBER)).thenReturn(ticketList);

        List<Ticket> result = testingInstance.getBookedTickets(event, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.stream().anyMatch(ticket -> ticket.getEventId() == EVENT_ID), is(Boolean.TRUE));
    }

    @Test
    void shouldCancelTicket() {
        when(ticketService.cancelTicket(TICKET_ID)).thenReturn(Boolean.TRUE);

        boolean result = testingInstance.cancelTicket(TICKET_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(Boolean.TRUE));
    }
}