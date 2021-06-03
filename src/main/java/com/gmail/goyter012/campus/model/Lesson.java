package com.gmail.goyter012.campus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "lesson_order")
    private Integer lessonOrder;

    @Column(name = "description")
    private String description;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group lessonGroup;


}
