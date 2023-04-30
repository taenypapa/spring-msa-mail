package com.agile.demo.biz.mail;

import com.agile.demo.core.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailEntity extends BaseEntity {
    private String toMail;
    private String toName;
    private String title;
    private String content;

    private String fromMail;
    private String fromName;
}
