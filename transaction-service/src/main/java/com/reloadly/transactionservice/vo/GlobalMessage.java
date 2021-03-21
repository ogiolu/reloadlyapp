package com.reloadly.transactionservice.vo;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalMessage implements Message {

    private String summary;

    private String detail;

    private Message.Severity severity;
}
