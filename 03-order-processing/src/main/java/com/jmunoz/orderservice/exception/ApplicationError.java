package com.jmunoz.orderservice.exception;

public sealed interface ApplicationError permits DomainError, SystemError {

}
