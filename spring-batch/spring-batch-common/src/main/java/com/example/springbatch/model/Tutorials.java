package com.example.springbatch.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "tutorials")
@XStreamAlias("tutorials")
public class Tutorials implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @XStreamAlias("tutorial_id")
    private Integer tutorialId;

    @XStreamAlias("tutorial_author")
    private String tutorialAuthor;

    @XStreamAlias("tutorial_title")
    private String tutorialTitle;

    @XStreamAlias("submission_date")
    private String submissionDate;

    @XStreamAlias("tutorial_icon")
    private String tutorialIcon;

}
