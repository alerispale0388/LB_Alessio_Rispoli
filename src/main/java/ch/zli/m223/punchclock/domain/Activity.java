package ch.zli.m223.punchclock.domain;

import javax.persistence.*;

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Project project_id;
}
