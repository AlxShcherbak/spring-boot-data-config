package com.oshcherbak.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RestWrapper<T> {
    T ob;
    String message;
    String error;
}
