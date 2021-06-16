package io.github.agrawald.censys.exception;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CensysException extends Exception {
    private final JsonNode errorResponse;
}
