/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.currency;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/currency")
public class CurrencyConversionService {

    // Hardcoded exchange rates
    private static final double USD_TO_EUR = 0.85;
    private static final double EUR_TO_USD = 1.18;

    @GET
    @Path("/convert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertCurrency(
            @QueryParam("amount") double amount,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {

        double convertedAmount = 0;

        if ("USD".equalsIgnoreCase(from) && "EUR".equalsIgnoreCase(to)) {
            convertedAmount = amount * USD_TO_EUR;
        } else if ("EUR".equalsIgnoreCase(from) && "USD".equalsIgnoreCase(to)) {
            convertedAmount = amount * EUR_TO_USD;
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Unsupported currency conversion")
                    .build();
        }

        return Response.ok()
                .entity(new ConversionResult(amount, from, to, convertedAmount))
                .build();
    }

    // Inner class to represent the conversion result
    public static class ConversionResult {
        private double originalAmount;
        private String fromCurrency;
        private String toCurrency;
        private double convertedAmount;

        public ConversionResult(double originalAmount, String fromCurrency, String toCurrency, double convertedAmount) {
            this.originalAmount = originalAmount;
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.convertedAmount = convertedAmount;
        }

        // Getters and setters (required for JSON serialization)
        public double getOriginalAmount() {
            return originalAmount;
        }

        public void setOriginalAmount(double originalAmount) {
            this.originalAmount = originalAmount;
        }

        public String getFromCurrency() {
            return fromCurrency;
        }

        public void setFromCurrency(String fromCurrency) {
            this.fromCurrency = fromCurrency;
        }

        public String getToCurrency() {
            return toCurrency;
        }

        public void setToCurrency(String toCurrency) {
            this.toCurrency = toCurrency;
        }

        public double getConvertedAmount() {
            return convertedAmount;
        }

        public void setConvertedAmount(double convertedAmount) {
            this.convertedAmount = convertedAmount;
        }
    }
}