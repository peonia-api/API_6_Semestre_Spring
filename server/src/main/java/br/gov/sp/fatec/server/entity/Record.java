package br.gov.sp.fatec.server.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "record")
public class Record {

    public enum OccurrenceType {
        ENTRANCE, EXIT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long id;

    @Column(name = "rec_occurrence")
    @Enumerated(EnumType.STRING)
    private OccurrenceType occurrence;

    @Column(name = "rec_date")
    private LocalDate date;

    @Column(name = "rec_hour")
    private LocalTime hour;

    @Column(name = "rec_room")
    private String room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OccurrenceType getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(OccurrenceType occurrence) {
        this.occurrence = occurrence;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}