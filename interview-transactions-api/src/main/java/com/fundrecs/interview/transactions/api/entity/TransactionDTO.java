package com.fundrecs.interview.transactions.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO implements Serializable {

  private static final long serialVersionUID = -8966357555897328792L;
  private String date;
  private String type;
  private String amount;

}
