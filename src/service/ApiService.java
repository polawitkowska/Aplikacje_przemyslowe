package service;

import model.Employee;
import exception.ApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    public List<Employee> fetchEmployeesFromApi(String apiUrl) throws ApiException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ApiException("Błąd przy pobieraniu odpowiedzi: " + e.getMessage());
        }

        System.out.println("Kod statusu: " + response.statusCode());



        return new ArrayList<>();
    }
}
