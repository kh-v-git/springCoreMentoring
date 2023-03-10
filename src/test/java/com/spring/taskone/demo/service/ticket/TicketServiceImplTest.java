package com.spring.taskone.demo.service.ticket;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.spring.taskone.demo.entities.Event;
import com.spring.taskone.demo.entities.Ticket;
import com.spring.taskone.demo.entities.User;
import com.spring.taskone.demo.repository.impl.TicketRepositoryImpl;
import com.spring.taskone.demo.utils.TicketCategoryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    private static final long EVENT_ID = 10L;
    private static final long USER_ID = 20L;
    private static final long TICKET_ID = 30L;
    private static final int PAGE_SIZE = 16;
    private static final int PAGE_NUMBER = 32;
    private static final int PLACE_NUMBER = 128;
    private static final TicketCategoryEnum TICKET_CATEGORY = TicketCategoryEnum.PREMIUM;

    @Mock
    private Event event;

    @Mock
    private Ticket ticket;

    @Mock
    private User user;

    @Mock
    private TicketRepositoryImpl ticketRepository;

    @InjectMocks
    private TicketServiceImpl testingInstance;

    @Test
    void shouldBookTicket() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticket.getUserId()).thenReturn(USER_ID);
        when(ticket.getEventId()).thenReturn(EVENT_ID);
        when(ticket.getPlaceNumber()).thenReturn(PLACE_NUMBER);
        when(ticket.getTicketCategory()).thenReturn(TICKET_CATEGORY);

        Ticket result = testingInstance.bookTicket(ticket);

        assertThat(result, is(notNullValue()));
        assertThat(result.getUserId(), is(USER_ID));
        assertThat(result.getEventId(), is(EVENT_ID));
        assertThat(result.getPlaceNumber(), is(PLACE_NUMBER));
        assertThat(result.getTicketCategory(), is(TICKET_CATEGORY));
    }

    @Test
    void shouldGetBookedTicketsForUser() {
        List<Ticket> ticketList = Lists.newArrayList(ticket);
        when(ticket.getUserId()).thenReturn(USER_ID);
        when(ticketRepository.getBookedTickets(user, PAGE_SIZE, PAGE_NUMBER)).thenReturn(ticketList);

        List<Ticket> result = testingInstance.getBookedTickets(user, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getUserId(), is(USER_ID));
    }

    @Test
    void shouldGetBookedTicketsForEvent() {
        List<Ticket> ticketList = Lists.newArrayList(ticket);
        when(ticket.getEventId()).thenReturn(EVENT_ID);
        when(ticketRepository.getBookedTickets(event, PAGE_SIZE, PAGE_NUMBER)).thenReturn(ticketList);

        List<Ticket> result = testingInstance.getBookedTickets(event, PAGE_SIZE, PAGE_NUMBER);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getEventId(), is(EVENT_ID));
    }

    @Test
    void shouldCancelTicket() {
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.of(ticket));

        boolean result = testingInstance.cancelTicket(TICKET_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    void shouldNotCancelTicket() {
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.empty());

        boolean result = testingInstance.cancelTicket(TICKET_ID);

        assertThat(result, is(notNullValue()));
        assertThat(result, is(Boolean.FALSE));
    }
}
