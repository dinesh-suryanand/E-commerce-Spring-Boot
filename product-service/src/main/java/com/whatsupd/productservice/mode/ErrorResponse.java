package com.whatsupd.productservice.mode;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
  private String errorMessage;
  private String errorCode;
}
