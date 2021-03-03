package com.solidbeans.call4help.jms;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MessageObject {
    private UUID uuid = UUID.randomUUID();
    private Long helper_id;
    private int distance;
    private Long sender_id;
}
